package com.example.demo.models;

public class Wheels {
	
	private int spokes;
	private String rimMaterial;
	private boolean tube;
	public int getSpokes() {
		return spokes;
	}
	public void setSpokes(int spokes) {
		this.spokes = spokes;
	}
	
	public boolean isTube() {
		return tube;
	}
	public void setTube(boolean tube) {
		this.tube = tube;
	}
	public String getRimMaterial() {
		return rimMaterial;
	}
	public void setRimMaterial(String rimMaterial) {
		this.rimMaterial = rimMaterial;
	}
	
	public Wheels(int spokes, String rimMaterial, boolean tube) {
		super();
		this.spokes = spokes;
		this.rimMaterial = rimMaterial;
		this.tube = tube;
		
	}
	public Wheels() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
