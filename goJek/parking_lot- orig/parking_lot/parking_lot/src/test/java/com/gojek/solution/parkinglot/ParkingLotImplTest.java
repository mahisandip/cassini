package com.gojek.solution.parkinglot;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ParkingLotImplTest {

	@InjectMocks
	private static ParkingLotImpl parkingLot = null;

	Map<Integer, Boolean> slotMap;
	Map<Integer, String> slotToRegMap;
	Map<String, String> regToClrMap;
	Map<String, StringBuilder> clrToRegNumMap;

	private final InputStream systemIn = System.in;
	private final PrintStream systemOut = System.out;

	private ByteArrayInputStream testIn;
	private ByteArrayOutputStream testOut;
	
	private StringBuilder sb;

	@Before
	public void setUp() throws Exception {

		slotMap = new HashMap<>();
		slotMap.put(1, true);
		slotToRegMap = new HashMap<>();
		slotToRegMap.put(1, "a");
		regToClrMap = new HashMap<>();
		regToClrMap.put("a", "b");
		clrToRegNumMap = new HashMap<>();
		clrToRegNumMap.put("a", new StringBuilder());

		parkingLot.setClrToRegNumMap(clrToRegNumMap);
		parkingLot.setRegToClrMap(regToClrMap);
		parkingLot.setSlotToRegMap(slotToRegMap);
		parkingLot.setSlotMap(slotMap);
		parkingLot.setTotalSlots(-1);
		parkingLot.setSlotsAvailable(-2);

		testOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testOut));
		
		sb = new StringBuilder();
		sb.append("Created a parking lot with 6 slots\n");
		sb.append("Allocated slot number: 1\n");
		sb.append("Allocated slot number: 2\n");
		sb.append("Allocated slot number: 3\n");
		sb.append("Allocated slot number: 4\n");
		sb.append("Allocated slot number: 5\n");
		sb.append("Allocated slot number: 6\n");
		sb.append("Slot number 4 is free\n");
		sb.append("Slot No.   Registration No    Colour\n");
		sb.append("1   KA-01-HH-1234   White\n");
		sb.append("2   KA-01-HH-9999   White\n");
		sb.append("3   KA-01-BB-0001   Black\n");
		sb.append("5   KA-01-HH-2701   Blue\n");
		sb.append("6   KA-01-HH-3141   Black\n");
		sb.append("Allocated slot number: 4\n");
		sb.append("KA-01-HH-1234 KA-01-HH-9999 KA-01-P-333\n");
		sb.append("1,2,4\n");
		sb.append("6\n");
		sb.append("Not found\n");
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		parkingLot = ParkingLotImpl.getInstance();
	}

	private void provideInput(String data) {
		testIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(testIn);
	}

	private String getOutput() {
		return testOut.toString();
	}

	@After
	public void restoreSystemInputOutput() {
		System.setIn(systemIn);
		System.setOut(systemOut);
	}

	@Test
	public void testGetSlotsAvailable() {
		assertEquals(new Integer(-2), parkingLot.getSlotsAvailable());
	}

	@Test
	public void testSetSlotsAvailable() {
		parkingLot.setSlotsAvailable(-2);
		assertEquals(new Integer(-2), parkingLot.getSlotsAvailable());
	}

	@Test
	public void testGetTotalSlots() {
		assertEquals(new Integer(-1), parkingLot.getTotalSlots());
	}

	@Test
	public void testSetTotalSlots() {
		parkingLot.setTotalSlots(-1);
		assertEquals(new Integer(-1), parkingLot.getTotalSlots());
	}

	@Test
	public void testGetClrToRegNumMap() {
		assertEquals(clrToRegNumMap, parkingLot.getClrToRegNumMap());
	}

	@Test
	public void testSetClrToRegNumMap() {
		parkingLot.setClrToRegNumMap(clrToRegNumMap);
		assertEquals(clrToRegNumMap, parkingLot.getClrToRegNumMap());
	}

	@Test
	public void testGetRegToClrMap() {
		assertEquals(regToClrMap, parkingLot.getRegToClrMap());
	}

	@Test
	public void testSetRegToClrMap() {
		parkingLot.setRegToClrMap(regToClrMap);
		assertEquals(regToClrMap, parkingLot.getRegToClrMap());
	}

	@Test
	public void testGetSlotToRegMap() {
		assertEquals(slotToRegMap, parkingLot.getSlotToRegMap());
	}

	@Test
	public void testSetSlotToRegMap() {
		parkingLot.setSlotToRegMap(slotToRegMap);
		assertEquals(slotToRegMap, parkingLot.getSlotToRegMap());
	}

	@Test
	public void testGetSlotMap() {
		assertEquals(slotMap, parkingLot.getSlotMap());
	}

	@Test
	public void testSetSlotMap() {
		parkingLot.setSlotMap(slotMap);
		assertEquals(slotMap, parkingLot.getSlotMap());
	}

	@Test
	public void testProcess1() {

		slotMap = new HashMap<>();
		slotToRegMap = new HashMap<>();
		regToClrMap = new HashMap<>();
		clrToRegNumMap = new HashMap<>();

		parkingLot.setSlotMap(slotMap);
		parkingLot.setSlotToRegMap(slotToRegMap);
		parkingLot.setClrToRegNumMap(clrToRegNumMap);
		parkingLot.setRegToClrMap(regToClrMap);

		List<String> lines = new ArrayList<>();
		lines.add("create_parking_lot 6");
		lines.add("park KA-01-HH-1234 White");
		lines.add("park KA-01-HH-9999 White");
		lines.add("park KA-01-BB-0001 Black");
		lines.add("park KA-01-HH-7777 Red");
		lines.add("park KA-01-HH-2701 Blue");
		lines.add("park KA-01-HH-3141 Black");
		lines.add("leave 4");
		lines.add("status");
		lines.add("park KA-01-P-333 White");
		lines.add("registration_numbers_for_cars_with_colour White");
		lines.add("slot_numbers_for_cars_with_colour White");
		lines.add("slot_number_for_registration_number KA-01-HH-3141");
		lines.add("slot_number_for_registration_number MH-04-AY-1111");

		for (String string : lines) {
			parkingLot.process(string);
		}

		provideInput(sb.toString());
//		assertEquals(sb.toString(), getOutput());
	}

	@Test
	public void testProcess2() {

		List<String> lines = new ArrayList<>();
		lines.add("create_parking_lot 6");

		for (String string : lines) {
			parkingLot.process(string);
		}

		provideInput("Created a parking lot with 6 slots\n");
//		assertEquals("Created a parking lot with 6 slots\n", getOutput());
	}

	@Test
	public void testProcess3() {

		List<String> lines = new ArrayList<>();
		lines.add("create_parking_lot 6");

		for (String string : lines) {
			parkingLot.process(string);
		}

		provideInput("Created a parking lot with 6 slots\n");
//		assertEquals("Created a parking lot with 6 slots\n", getOutput());
	}
}
