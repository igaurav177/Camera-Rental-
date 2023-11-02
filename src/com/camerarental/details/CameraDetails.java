package com.camerarental.details;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.camerarental.pojo.CameraPojo;

public class CameraDetails {
	ArrayList<CameraPojo> cameras = new ArrayList();
	Map<CameraPojo, Boolean> rentalStatus = new HashMap();
	

	public ArrayList<CameraPojo> getCamera(){
		return cameras;
	}

	public void addCameraIntoList(CameraPojo camera) {
		cameras.add(camera);

	}

	public void removeCameraFromList(int cameraId) {
		
		if (cameraId >= 0 && cameraId < cameras.size()) {
			CameraPojo removedCamera = cameras.get(cameraId);
			cameras.remove(cameraId);
			rentalStatus.remove(removedCamera);
			
		}		

	}

	public void markAsRented(CameraPojo camera) {
		rentalStatus.put(camera, true);
	}

	public void markAsAvailable(CameraPojo camera) {
		rentalStatus.put(camera, false);
	}

	public boolean isRented(CameraPojo camera) {
		Boolean status = rentalStatus.get(camera);
		return status != null && status; 
	}
}





