package com.gojek.solution.parkinglot;

import java.util.Map;

public class ParkingLotImpl extends ParkingLot {

	private static ParkingLotImpl parkingLotImpl = null;

	private ParkingLotImpl() {
	}

	public static ParkingLotImpl getInstance() {
		if (parkingLotImpl == null) {
			parkingLotImpl = new ParkingLotImpl();
		}
		return parkingLotImpl;
	}

	private void initializeParkingLot() {
		for (int i = 0; i < totalSlots; i++) {
			slotMap.put(i, true);
		}
	}

	@Override
	public void process(String command) {

		String[] cmds = command.split(" ");
		String cmd = cmds[0].trim();
		if ("create_parking_lot".equalsIgnoreCase(cmd)) {
			createParkingLot(cmds);
		} else if ("park".equalsIgnoreCase(cmd)) {
			park(cmds);
		} else if ("leave".equalsIgnoreCase(cmd)) {
			leaveCarPark(cmds);
		} else if ("status".equalsIgnoreCase(cmd)) {
			getStatus();
		} else if ("registration_numbers_for_cars_with_colour"
				.equalsIgnoreCase(cmd)) {
			getRegNumOfCarsWithColour(cmds);
		} else if ("slot_numbers_for_cars_with_colour".equalsIgnoreCase(cmd)) {
			getSlotNumOfCarsWithColour(cmds);
		} else if ("slot_number_for_registration_number".equalsIgnoreCase(cmd)) {
			getSlotNumForRegNum(cmds);
		} else {
			System.err.println("Bad command");
		}
	}

	private void createParkingLot(String... cmds) {
		if (totalSlots < 0 ) {
			slotsAvailable = Integer.parseInt(cmds[1]);
			totalSlots = slotsAvailable;
			initializeParkingLot();
			System.out.print(String.format(
					"Created a parking lot with %s slots\n", slotsAvailable));
		} else {
			System.err.println("Not allowed to create new slots");
		}
	}

	private void park(String... cmds) {

		if (slotsAvailable == 0) {
			System.out.println("Sorry, parking lot is full");
			return;
		} else {

			int slot = findFirstAvailableSlot();

			if (!slotMap.get(slot)) {
				System.out.println("Slot is occupied");
				return;
			}

			if (!slotToRegMap.containsKey(slot)) {
				slotToRegMap.put(slot, cmds[1].trim());
			} else {
				System.err
						.println("Slot already occupied by " + cmds[1].trim());
				return;
			}

			if (!regToClrMap.containsKey(cmds[1].trim())) {
				regToClrMap.put(cmds[1].trim(), cmds[2].trim());
			} else {
				System.err.println("Registration number already exists");
				return;
			}

			if (clrToRegNumMap.containsKey(cmds[2].trim())) {
				StringBuilder builder = clrToRegNumMap.get(cmds[2].trim());
				builder.append(" ");
				builder.append(cmds[1].trim());
			} else {
				StringBuilder builder = new StringBuilder(cmds[1].trim());
				clrToRegNumMap.put(cmds[2].trim(), builder);
			}

			slotMap.replace(slot, false);
			System.out.println("Allocated slot number: " + (slot + 1));
			slotsAvailable--;
		}
	}

	private void leaveCarPark(String... cmds) {

		int slot = Integer.parseInt(cmds[1].trim()) - 1;

		if (slotMap.get(slot)) {
			System.err.println("Slot was already free");
		} else {
			String regNum = slotToRegMap.remove(slot);
			String colour = regToClrMap.remove(regNum);
			StringBuilder builder = clrToRegNumMap.get(colour);
			clrToRegNumMap.put(colour,
					new StringBuilder(removeWord(builder.toString(), regNum)));
			slotMap.replace(slot, true);
			System.out.println("Slot number " + (slot + 1) + " is free");
			slotsAvailable++;
		}
	}

	private void getStatus() {
		System.out.println("Slot No.   Registration No    Colour");
		for (int i = 0; i < totalSlots; i++) {
			if (slotMap.get(i) == false) {
				String regNum = slotToRegMap.get(i).trim();
				String colour = regToClrMap.get(regNum).trim();
				int s = i + 1;
				System.out.print(s + "   " + regNum + "   " + colour + "\n");
			}
		}
	}

	private void getRegNumOfCarsWithColour(String... cmds) {
		if (clrToRegNumMap.containsKey(cmds[1].trim())) {
			String regNums = clrToRegNumMap.get(cmds[1].trim()).toString();
			System.out.println(regNums);
		} else {
			System.err.println("No cars with colour " + cmds[1]);
		}
	}

	private void getSlotNumOfCarsWithColour(String... cmds) {
		if (clrToRegNumMap.containsKey(cmds[1].trim())) {
			String[] regNums = clrToRegNumMap.get(cmds[1].trim()).toString()
					.split(" ");
			StringBuilder sb = new StringBuilder();
			for (String regNum : regNums) {
				int slot = slotToRegMap.entrySet().stream()
						.filter(entry -> regNum.equals(entry.getValue()))
						.map(Map.Entry::getKey).findFirst().get() + 1;
				sb.append(slot);
				sb.append(",");
			}
			System.out.println(sb.deleteCharAt(sb.toString().length() - 1));
		} else {
			System.err.println("No cars with colour " + cmds[1]);
		}
	}

	private void getSlotNumForRegNum(String... cmds) {
		if (regToClrMap.containsKey(cmds[1].trim())) {

			int slot = slotToRegMap.entrySet().stream()
					.filter(entry -> cmds[1].trim().equals(entry.getValue()))
					.map(Map.Entry::getKey).findFirst().get();

			System.out.println(slot + 1);
		} else {
			System.out.print("Not found\n");
		}
	}

	private String removeWord(String string, String word) {

		// Check if the word is present in string
		// If found, remove it using removeAll()
		if (string.contains(word)) {

			// To cover the case
			// if the word is at the
			// beginning of the string
			// or anywhere in the middle
			String tempWord = word + " ";
			string = string.replaceAll(tempWord, "");

			// To cover the edge case
			// if the word is at the
			// end of the string
			tempWord = " " + word;
			string = string.replaceAll(tempWord, "");
		}

		// Return the resultant string
		return string;
	}

	private Integer findFirstAvailableSlot() {
		for (int i = 0; i < totalSlots; i++) {
			if (slotMap.get(i)) {
				return i;
			}
		}
		return null;
	}
}
