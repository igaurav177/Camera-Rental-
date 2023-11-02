package com.camerarental.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.camerarental.details.CameraDetails;
import com.camerarental.pojo.*;
import com.camerarental.service.*;

public class RentalServices {
	CameraDetails cameraDetails = new CameraDetails();
	WalletServices wallet = new WalletServices();
	Scanner sc = new Scanner(System.in);
	int input;
	
	public RentalServices() {
		cameraDetails.addCameraIntoList(new CameraPojo("Canon", "Canon EOS 6D", 1500.0));
	    cameraDetails.addCameraIntoList(new CameraPojo("Nikon", "Nikon D750", 2000.0));
	    cameraDetails.addCameraIntoList(new CameraPojo("Sony", "Sony Mavica", 3000.0));
	    cameraDetails.addCameraIntoList(new CameraPojo("Canon", "Canon EOS 800D", 1000.0));
	    cameraDetails.addCameraIntoList(new CameraPojo("Sony", "Sony ILCE-7M4", 5000.0));
	    cameraDetails.addCameraIntoList(new CameraPojo("Nikon", "Nikon D5", 3000.0));
	    
		}

	public void show() {

		while(true) {
			System.out.println("Choose an option:");
			System.out.println("1. My Camera");
			System.out.println("2. Rent a Camera");
			System.out.println("3. View All Camera");
			System.out.println("4. My Wallet");
			System.out.println("5. Exit");
			input = sc.nextInt();

			switch(input) {
			case 1: myCamera();
			break;
			case 2: rentCamera();
			break;
			case 3: viewMyCameras();
			break;
			case 4: myWallet();
			break;
			case 5: 
				return;
			default: 
				System.out.println("Enter valid input.");
			}
		}
	}

	public void myCamera() {
		while(true) {
			System.out.println("Choose an option:");
			System.out.println("1. Add");
			System.out.println("2. Remove");
			System.out.println("3. View my Camera");
			System.out.println("4. Go to previous menu");
			input =sc.nextInt();
			switch(input) {
			case 1: addCamera();
			break;
			case 2: removeCamera();
			break;
			case 3: viewMyCameras();
			break;
			case 4:	return;
			default : 
				System.out.println("Enter valid input.");
			}		
		}

	}

	public void addCamera() {

		System.out.println("Enter the camera brand name : ");
		String cameraBrandName=sc.next()+sc.nextLine();
		System.out.println("Enter the model : ");
		String cameraModel =sc.next()+sc.nextLine();
		System.out.println("Enter the per day price(INR) : ");
		double rentPerDay = sc.nextDouble();
		CameraPojo camera;
		cameraDetails.addCameraIntoList(new CameraPojo(cameraBrandName, cameraModel, rentPerDay));
		System.out.println("Your camera has been successfully added to the list.");

	}

	public void removeCamera() {
		viewMyCameras();
		System.out.println("Enter the camera Id remove : ");
		int cameraId = sc.nextInt();
		cameraDetails.removeCameraFromList(cameraId-1);
		System.out.println("Camera remove Successfully..");

	}

	public void viewMyCameras() {
		
		ArrayList<CameraPojo> cameras = cameraDetails.getCamera();
		
		if(cameras.isEmpty()) {
			System.out.println("No Data...");
		}else {
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.printf("%-10s %-20s %-20s %-20s %-10s%n","Camera ID", "Brand", "Model", "Rent per Day (Rs.)", "Status");
			System.out.println("-------------------------------------------------------------------------------------");
			for(int index=0;index<cameras.size();index++) {
				CameraPojo camera = cameras.get(index);
				String status = cameraDetails.isRented(camera) ? "Rented" : "Available";
				System.out.printf("%-10s %-20s %-20s %-20s %-10s%n",index+1,camera.getCameraBrand(), camera.getCameraModel(), camera.getPricePerDay(), status);
			}
		}

	}

	public void viewAvailableCamera() {
		ArrayList<CameraPojo> cameras = cameraDetails.getCamera();
		if(cameras.isEmpty()) {
			System.out.println("No Data...");
		}
		System.out.println("--------------------------------------------------------------------------");
		System.out.printf("%-10s %-20s %-20s %-20s %-10s%n","Camera ID", "Brand", "Model", "Rent per Day (Rs.)", "Status");
		System.out.println("--------------------------------------------------------------------------");
		for(int index=0;index<cameras.size();index++) {
			CameraPojo camera = cameras.get(index);
			String status = cameraDetails.isRented(camera) ? "Rented" : "Available";
			if (!cameraDetails.isRented(camera)) {
				System.out.printf("%-10s %-20s %-20s %-20s %-10s%n",+index+1,camera.getCameraBrand(),camera.getCameraModel(),camera.getPricePerDay(),status);
			}
		}
		System.out.println("--------------------------------------------------------------------------");
	}
	public void rentCamera() {	
		
		ArrayList<CameraPojo> cameras = cameraDetails.getCamera();
		
		if (cameras.isEmpty()) {
			System.out.println("No cameras available for rent.");
			return;
		}
		viewAvailableCamera() ;		

		System.out.println("Enter the camera id of the camera you want to rent:");
		int cameraId = sc.nextInt();

		if (cameraId < 1 || cameraId > cameras.size()) {
			System.out.println("Invalid id. Please enter a valid camera index.");
			return;
		}

		CameraPojo selectedCamera = cameras.get(cameraId-1);
		if (wallet.rent(selectedCamera)) {
			System.out.println("You have rented the following camera: " + selectedCamera);
			cameraDetails.markAsRented(selectedCamera);
		} else {
			System.out.println("Insufficient wallet balance or the camera is already rented. Please deposit funds or choose another camera.");
		}

	}

	public void myWallet() {
		System.out.println("My current wallet is: "+wallet.getBalance());
		System.out.println("Do you want to deposit more money (Y/N):");
		char choice= sc.next().charAt(0);
		if(choice == 'Y' || choice == 'y') {
			System.out.println("Enter the amount : ");
			int depositAmount = sc.nextInt();
			wallet.setBalance(depositAmount);
			System.out.println("Your wallet balance is updated successfully. Current wallet balance - "+wallet.getBalance());

		}else if(choice =='N' || choice=='n') {
			return;
		}

	}

}
