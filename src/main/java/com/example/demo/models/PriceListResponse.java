package com.example.demo.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PriceListResponse {

	private int chainAssembly;
	private int frames;
	private int handleAndBrakes;
	private int seating;
	private int wheels;

	public int getChainAssembly() {
		return chainAssembly;
	}

	public void setChainAssembly(int chainAssembly) {
		this.chainAssembly = chainAssembly;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public int getHandleAndBrakes() {
		return handleAndBrakes;
	}

	public void setHandleAndBrakes(int handleAndBrakes) {
		this.handleAndBrakes = handleAndBrakes;
	}

	public int getSeating() {
		return seating;
	}

	public void setSeating(int seating) {
		this.seating = seating;
	}

	public int getWheels() {
		return wheels;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

	public PriceListResponse(int chainAssembly, int frames, int handleAndBrakes, int seating, int wheels) {
		super();
		this.chainAssembly = chainAssembly;
		this.frames = frames;
		this.handleAndBrakes = handleAndBrakes;
		this.seating = seating;
		this.wheels = wheels;
	}

	public PriceListResponse() {
		super();
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		String jsonString = "";
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;
	}

}
