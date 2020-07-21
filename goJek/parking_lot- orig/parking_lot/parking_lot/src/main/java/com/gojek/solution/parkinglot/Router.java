package com.gojek.solution.parkinglot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Router {

	private int mode;

	private String fileName;
	
	private ParkingLotImpl parkingLotImpl;

	private static final String FILE_PATH = "../functional_spec/fixtures/";
	private static final String EXIT_CMD = "exit";

	public Router (){}
	
	public Router(int mode) {
		super();
		this.mode = mode;
		this.fileName = "";
		parkingLotImpl  = ParkingLotImpl.getInstance();
	}

	public Router(int mode, String fileName) {
		super();
		this.mode = mode;
		this.fileName = FILE_PATH + fileName;
		parkingLotImpl  = ParkingLotImpl.getInstance();
	}

	public void execute() {
		if (mode == 1) {
			// Using file to input data from user. Store the entire file in a
			// list
			List<String> lines = null;
			try {
				lines = Files.readAllLines(Paths.get(fileName),
						StandardCharsets.UTF_8);
			} catch (IOException e) {
				System.out.print("Error reading file\n");
				return;
			}
			for (String line : lines) {
				parkingLotImpl.process(line);
			}
		} else if (mode == 2) {
			// Using Console to input data from user
			String input = "";
			while (!(input = System.console().readLine())
					.equalsIgnoreCase(EXIT_CMD)) {
				parkingLotImpl.process(input);
			}
		}

	}

}
