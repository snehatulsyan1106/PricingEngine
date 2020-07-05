package com.example.demo.models;

public class HandleBarBrake {
	private String handleMaterial;
	private String handleDesign;
	private String brakes;
	public String getHandleMaterial() {
		return handleMaterial;
	}
	public void setHandleMaterial(String handleMaterial) {
		this.handleMaterial = handleMaterial;
	}
	public String getHandleDesign() {
		return handleDesign;
	}
	public void setHandleDesign(String handleDesign) {
		this.handleDesign = handleDesign;
	}
	public String getBrakes() {
		return brakes;
	}
	public void setBrakes(String brakes) {
		this.brakes = brakes;
	}
	public HandleBarBrake() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HandleBarBrake(String handleMaterial, String handleDesign, String brakes) {
		super();
		this.handleMaterial = handleMaterial;
		this.handleDesign = handleDesign;
		this.brakes = brakes;
	}
	

}
