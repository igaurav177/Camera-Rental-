package com.camerarental.service;

import com.camerarental.details.CameraDetails;
import com.camerarental.pojo.CameraPojo;

public class WalletServices {
	   CameraDetails cameraList=new CameraDetails();
	    private double balance = 1000.0d;

	    public double getBalance() {
	        return balance;
	    }

	    public void setBalance(double balance) {
	        this.balance += balance;
	    }

	    public boolean canRent(CameraPojo camera) {
	        return balance >= camera.getPricePerDay();
	    }

	    public boolean rent(CameraPojo camera) {
	        if (balance >= camera.getPricePerDay() && !cameraList.isRented(camera)) {
	            balance -= camera.getPricePerDay();
	            cameraList.markAsRented(camera);
	            return true;
	        }
	        return false;
	    }

}
