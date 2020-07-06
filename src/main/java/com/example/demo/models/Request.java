package com.example.demo.models;

public class Request {
	private int id;
	private ChainAssembly chainAssembly;
	private Frames frames;
	private HandleBarBrake handleAndBrakes;
	private Seating seating;
	private Wheels wheels;
	private int year;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ChainAssembly getChainAssembly() {
		return chainAssembly;
	}

	public void setChainAssembly(ChainAssembly chainAssembly) {
		this.chainAssembly = chainAssembly;
	}

	public Frames getFrames() {
		return frames;
	}

	public void setFrames(Frames frames) {
		this.frames = frames;
	}

	public HandleBarBrake getHandleAndBrakes() {
		return handleAndBrakes;
	}

	public void setHandleAndBrakes(HandleBarBrake handleAndBrakes) {
		this.handleAndBrakes = handleAndBrakes;
	}

	public Seating getSeating() {
		return seating;
	}

	public void setSeating(Seating seating) {
		this.seating = seating;
	}

	public Wheels getWheels() {
		return wheels;
	}

	public void setWheels(Wheels wheels) {
		this.wheels = wheels;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Request(int id, ChainAssembly chainAssembly, Frames frames, HandleBarBrake handleAndBrakes, Seating seating,
			Wheels wheels, int year) {
		super();
		this.id = id;
		this.chainAssembly = chainAssembly;
		this.frames = frames;
		this.handleAndBrakes = handleAndBrakes;
		this.seating = seating;
		this.wheels = wheels;
		this.year = year;
	}

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

}
