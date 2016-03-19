package com.mickwheelz.homekitmqttbridge;

public class Constants {
	
	//MESSAGE STRINGS
	public static final String MQTT_CONNECTION_FAILURE = "WARNING: Failed to connect to MQTT Broker";
	public static final String HOMEKIT_PIN_MSG = "The PIN for Pairing is: ";
	public static final String ADD_PAIRING = "Added pairing for ";
	public static final String REMOVE_PAIRING = "Removed pairing for ";
	
	//FILENAME STRINGS
	public static final String PREFS_FILENAME = "preferences.json";
	public static final String ACC_FILENAME = "accessories.json";
	public static final String USERS_FILENAME = "users.json";
	
	//DEFAULT PREFERENCES
	public static final String HOMEKIT_PIN = "031-45-154";
	public static final Boolean HOMEKIT_HAS_USER = false;
	public static final String HOMEKIT_BRIDGE_NAME = "mickwheelz bridge";
	public static final String HOMEKIT_BRIDGE_MANU = "mickwheelz";
	public static final String HOMEKIT_BRIDGE_VER = "a 0.1";
	public static final String HOMEKIT_BRIDGE_SN = "123456";
	public static final Integer HOMEKIT_BRIDGE_PORT = 9123;

	public static final String MQTT_BROKER_ADDRESS = "tcp://127.0.0.1";
	public static final String MQTT_CLIENT_NAME = "homekit-mqtt";
	
	public static final String PACKAGE_DEVICES = "com.mickwheelz.homekitmqttbridge.devices";
	public static final String PACKAGE_OBJECTS = "com.mickwheelz.homekitmqttbridge.objects";

	
	
}
