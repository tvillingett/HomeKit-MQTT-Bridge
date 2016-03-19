package com.mickwheelz.homekitmqttbridge.objects;

import java.lang.reflect.InvocationTargetException;

import com.beowulfe.hap.HomekitAccessory;
import com.mickwheelz.homekitmqttbridge.Constants;

public class HomeKitDevice {
	
	public String deviceLabel;
	public int devicePin;
	public String deviceClass;
	public HomekitAccessory device;
	
	public HomeKitDevice(String deviceLabel, int devicePin, String deviceClass) {
		this.deviceLabel = deviceLabel;
		this.devicePin = devicePin;
		this.deviceClass = deviceClass;
		setDevice(deviceClass);
	}
	
	public String getDeviceLabel() {
		return deviceLabel;
	}
	public void setDeviceLabel(String deviceLabel) {
		this.deviceLabel = deviceLabel;
	}
	public int getDevicePin() {
		return devicePin;
	}
	public void setDevicePin(int devicePin) {
		this.devicePin = devicePin;
	}
	public String getDeviceClass() {
		return deviceClass;
	}
	public void setDeviceClass(String deviceClass) {
		this.deviceClass = deviceClass;
	}
	public void setDevice(String deviceClass) {
		try {
			Class<?> c = Class.forName(Constants.PACKAGE_DEVICES + "." + deviceClass);
			device = (HomekitAccessory) c.getDeclaredConstructor(int.class, String.class).newInstance(devicePin,deviceLabel);
		} 
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				 | SecurityException | ClassNotFoundException | InvocationTargetException |
				 NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} 
	}
	public HomekitAccessory getDevice() {
		System.out.println("setdev");
		setDevice(deviceClass);
		return device;
	}
		

}
