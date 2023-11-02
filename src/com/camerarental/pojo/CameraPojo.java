package com.camerarental.pojo;

public class CameraPojo {

	private int cameraId;
	private String cameraBrand;
	private String cameraModel;
	private double pricePerDay;

	public CameraPojo(String cameraBrand, String cameraModel, double pricePerDay) {
		this.cameraBrand = cameraBrand;
		this.cameraModel = cameraModel;
		this.pricePerDay = pricePerDay;

	}

	public int getCameraId() {
		return cameraId; 
	}

	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}

	public void setCameraBrand(String cameraBrand) {
		this.cameraBrand = cameraBrand;
	}

	public void setCameraModel(String cameraModel) {
		this.cameraModel = cameraModel;
	}
	
	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
	public String getCameraBrand() {
		return cameraBrand;
	}

	public String getCameraModel() {
		return cameraModel;
	}
	
	public double getPricePerDay() {
		return pricePerDay;
	}

	@Override
	public String toString() {
		return "CameraPojo [cameraBrand=" + cameraBrand + ", cameraModel=" + cameraModel + ", pricePerDay=" + pricePerDay + "]";
	}



}
