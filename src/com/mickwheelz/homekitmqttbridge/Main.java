package com.mickwheelz.homekitmqttbridge;

import java.util.ArrayList;
import com.beowulfe.hap.HomekitAccessory;
import com.beowulfe.hap.HomekitRoot;
import com.beowulfe.hap.HomekitServer;
import com.mickwheelz.homekitmqttbridge.devices.SinglePowerSwitch;
import com.mickwheelz.homekitmqttbridge.objects.PreferenceStore;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
	    
    public static MqttClient client = null;
    public static PreferenceStore prefs = null;

    private static HomekitServer homekit = null;
    private static HomekitRoot bridge = null;
    private static ArrayList<HomekitAccessory> accessories = new ArrayList<HomekitAccessory>();

	public static void main(String[] args) {
		
		//Load Preferences & Accessories
		JSONPreferenceParser parser = new JSONPreferenceParser();
		Utility util = new Utility();
		prefs = parser.loadPrefs();
		accessories = parser.parseAccessories();
		
		//MQTT Client setup and connection
		try {
			client = new MqttClient(prefs.getBrokerAddress(), prefs.getMqttClientName());
			client.connect();
		} catch (MqttException e){
			System.out.println(Constants.MQTT_CONNECTION_FAILURE);
		}
		
		//HomeKit Setup and Connection
		try {
			homekit = new HomekitServer( prefs.getBridgePort());
			bridge = homekit.createBridge(new HomeKitAuth(), prefs.getBridgeName(), prefs.getBridgeManu(), prefs.getBridgeVer(), prefs.getBridgeSN());
					
			for(HomekitAccessory acc :accessories) {
				bridge.addAccessory(acc);
			}
					
			bridge.start();	
		
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			util.preferencesToFile(prefs, Constants.PREFS_FILENAME);
		}
	}
}