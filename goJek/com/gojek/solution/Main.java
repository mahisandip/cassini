package com.gojek.solution;

import com.gojek.solution.parkinglot.Router;

/**
 * Main class implementation for the parking lot. Input is read from either 
 * file or console. Based on the mode of input initialize the router and start 
 * processing the request 
 * 
 * @author sandeep.b.nair
 *
 */
public class Main {

	public static void main(String[] args) {
		
		System.out.println("*** Parking Lot ***");
		// if the input has to be read from the file
		if(args.length == 1) {
			new Router(1, args[0]).execute();
		}
		// else the input is read from the console
		else {
			new Router(2).execute();
		}

	}

}