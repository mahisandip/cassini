package com.gojek.solution.parkinglot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Router class implementation. This class accepts inputs from the main method 
 * and determine in which mode should the parking lot functions.
 * <p>
 * 1. File mode - Reads the input as strings from the file <br>
 * 2. Console mode - Reads the input from the console as a strings <br>
 * <p>
 * Initialize the parking lot and process the request accordingly
 * 
 * @author sandeep.b.nair
 *
 */
public class Router {

	/** mode */
	private int mode;

	/** file name */
	private String fileName;
	
	/** parking lot impl */
	private ParkingLotImpl parkingLotImpl;

	/** constants */
	private static final String FILE_PATH = "functional_spec/fixtures/";
	private static final String EXIT_CMD = "exit";

	/**
	 * Initialize the Router specifically for console mode
	 * 
	 * @param mode Integer mode 
	 */
	public Router(int mode) {
		super();
		this.mode = mode;
		this.fileName = "";
		parkingLotImpl  = ParkingLotImpl.getInstance();
	}

	/**
	 * Initialize the Router Specifically for file mode
	 * 
	 * @param mode Integer mode
	 * @param fileName String fileName
	 */
	public Router(int mode, String fileName) {
		super();
		this.mode = mode;
		this.fileName = FILE_PATH + fileName;
		parkingLotImpl  = ParkingLotImpl.getInstance();
	}

	/**
	 * Based on the mode of operation, reads the input and send the inputs to the 
	 * parking lot processor for processing 
	 */
	public void execute() {
		if (mode == 1) {
			// Using file to input data from user. Store the entire file in a
			// list
			System.out.println("Running in file mode");
			List<String> lines = null;
			try {
				lines = Files.readAllLines(Paths.get(fileName),
						StandardCharsets.UTF_8);
			} catch (IOException e) {
				System.err.println("Error reading file");
				return;
			}
			for (String line : lines) {
				parkingLotImpl.process(line);
			}
		} else if (mode == 2) {
			// Using Console to input data from user
			System.out.println("Running in console mode");
			String input = "";
			while (!(input = System.console().readLine())
					.equalsIgnoreCase(EXIT_CMD)) {
				parkingLotImpl.process(input);
			}
		}

	}

}
