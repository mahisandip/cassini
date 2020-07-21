package com.gojek.solution.parkinglot;

import java.util.HashMap;
import java.util.Map;

public abstract class ParkingLot {
	
	protected Integer totalSlots = -1;
	
	protected Integer slotsAvailable = -2;
	
	protected Map<Integer, Boolean> slotMap = new HashMap<>();
	
	protected Map<Integer, String> slotToRegMap = new HashMap<>();
	
	protected Map<String, String> regToClrMap = new HashMap<>();
	
	protected Map<String, StringBuilder> clrToRegNumMap= new HashMap<>();
	
	public abstract void process(String command);

	public Integer getTotalSlots() {
		return totalSlots;
	}

	public void setTotalSlots(Integer totalSlots) {
		this.totalSlots = totalSlots;
	}

	public Integer getSlotsAvailable() {
		return slotsAvailable;
	}

	public void setSlotsAvailable(Integer slotsAvailable) {
		this.slotsAvailable = slotsAvailable;
	}

	public Map<Integer, Boolean> getSlotMap() {
		return slotMap;
	}

	public void setSlotMap(Map<Integer, Boolean> slotMap) {
		this.slotMap = slotMap;
	}

	public Map<Integer, String> getSlotToRegMap() {
		return slotToRegMap;
	}

	public void setSlotToRegMap(Map<Integer, String> slotToRegMap) {
		this.slotToRegMap = slotToRegMap;
	}

	public Map<String, String> getRegToClrMap() {
		return regToClrMap;
	}

	public void setRegToClrMap(Map<String, String> regToClrMap) {
		this.regToClrMap = regToClrMap;
	}

	public Map<String, StringBuilder> getClrToRegNumMap() {
		return clrToRegNumMap;
	}

	public void setClrToRegNumMap(Map<String, StringBuilder> clrToRegNumMap) {
		this.clrToRegNumMap = clrToRegNumMap;
	}
	
}
