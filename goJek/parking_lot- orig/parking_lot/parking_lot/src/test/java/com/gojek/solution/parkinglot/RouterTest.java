package com.gojek.solution.parkinglot;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RouterTest {

	@InjectMocks
	private Router router1;
	
	@InjectMocks
	private Router router2;
	
	@InjectMocks
	private Router router3;
	
	@Mock
	private ParkingLotImpl parkingLotImpl;
	
	private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
	
	@Before
	public void beforeStep() {
		testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        
		router1 = new Router(1, "file_input.txt");
		router2 = new Router(1, "file_nput.xt");
		router3 = new Router(2);
		parkingLotImpl = Mockito.spy(ParkingLotImpl.getInstance());
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
	public void testExecute1() throws Exception {
		doNothing().when(parkingLotImpl)
        .process(any(String.class));	
		router1.execute();
	}
	
	@Test
	public void testExecute2() throws Exception {
		doNothing().when(parkingLotImpl)
        .process(any(String.class));
		router2.execute();
		provideInput("Error reading file\n");
		assertEquals("Error reading file\n", getOutput());
	}
	
}
