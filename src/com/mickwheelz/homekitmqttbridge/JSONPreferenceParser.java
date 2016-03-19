package com.mickwheelz.homekitmqttbridge;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import com.beowulfe.hap.HomekitAccessory;
import com.beowulfe.hap.HomekitServer;
import com.mickwheelz.homekitmqttbridge.objects.HomeKitDevice;
import com.mickwheelz.homekitmqttbridge.objects.PreferenceStore;

public class JSONPreferenceParser {
	
	private PreferenceStore preferences = new PreferenceStore();
	private Utility util = new Utility();
	
	public void setDefaults() {
		
		// Setup Default Preferences
		preferences.setBridgeManu(Constants.HOMEKIT_BRIDGE_MANU);
		preferences.setBridgeName(Constants.HOMEKIT_BRIDGE_NAME);
		preferences.setBridgePort(Constants.HOMEKIT_BRIDGE_PORT);
		preferences.setBridgeSN(Constants.HOMEKIT_BRIDGE_SN);
		preferences.setBridgeVer(Constants.HOMEKIT_BRIDGE_VER);
		preferences.setBrokerAddress(Constants.MQTT_BROKER_ADDRESS);
		preferences.setHasUser(Constants.HOMEKIT_HAS_USER);
		preferences.setMac(HomekitServer.generateMac());
		preferences.setMqttClientName(Constants.MQTT_CLIENT_NAME);
		preferences.setPin(Constants.HOMEKIT_PIN);
		preferences.setSalt(HomekitServer.generateSalt());
		try {
			preferences.setPrivateKey(HomekitServer.generateKey());
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		try {
			preferences.setIface(InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		//Write to Preferences File
		util.preferencesToFile(preferences, Constants.PREFS_FILENAME);

	}
	
	public PreferenceStore loadPrefs() {
	
		PreferenceStore prefs = null;
		
		try {
			prefs = util.readPreferences(Constants.PREFS_FILENAME);
		} catch (IOException e) {
			setDefaults();
		}
		
		if(prefs == null) {
			setDefaults();
			try {
				prefs = util.readPreferences(Constants.PREFS_FILENAME);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return prefs;
		
	}
	
	public ArrayList<HomekitAccessory> parseAccessories() {
		
		ArrayList<HomekitAccessory> accs = new ArrayList<HomekitAccessory>();
		
		try {
			ArrayList<HomeKitDevice> devs = util.readAccessories(Constants.ACC_FILENAME);
			for(HomeKitDevice d :devs) {
				accs.add(d.getDevice());
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return accs;
	}

	public void createDefaultAccessory() {
				
		ArrayList<HomeKitDevice> devs = new ArrayList<HomeKitDevice>();
	
		devs.add(new HomeKitDevice("Switch 1", 2, "SinglePowerSwitch"));
		devs.add(new HomeKitDevice("Switch 2", 3, "SinglePowerSwitch"));
		
		util.writeAccessoriesToFile(devs, Constants.ACC_FILENAME);
		
		
		
	}
}
