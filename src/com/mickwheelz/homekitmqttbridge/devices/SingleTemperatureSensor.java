package com.mickwheelz.homekitmqttbridge.devices;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import com.beowulfe.hap.HomekitCharacteristicChangeCallback;
import com.beowulfe.hap.Service;
import com.beowulfe.hap.accessories.TemperatureSensor;

public class SingleTemperatureSensor implements TemperatureSensor{

	private Double maxTemp = 0.0;
	private Double minTemp = 0.0;
	private CompletableFuture<Double> currentTemp = new CompletableFuture<Double>();


	private int devId = 2;
	private MqttClient mqttClient = null;
	private String devLabel = null;
	private MqttTopic mqttTopic = null;
	private Integer pubQoS = 0;
	private String mTopic = null;
	
	
	public SingleTemperatureSensor(MqttClient client, int deviceId, String label, String topic){
		devId = deviceId;
		mqttClient = client;
		devLabel = label;
		mqttTopic = mqttClient.getTopic(topic);
		mTopic = topic;
	}

	@Override
	public int getId() {
		return devId;
	}  

	@Override
	public String getLabel() {
		return devLabel;
	}

	@Override
	public String getManufacturer() {
		return "mickwheelz";
	}

	@Override
	public String getModel() {
		return "Generic Temerpature Sensor";
	}

	@Override
	public String getSerialNumber() {
		return "MWHKMQTT" + getId();
	}


	@Override
	public void identify() {
		System.out.println("Identifying Temperature Sensor");
	}

	@Override
	public CompletableFuture<Double> getCurrentTemperature() {
		//TODO: get temp via MQTT topic
		//TODO: set max/min temp
		return currentTemp;
	}

	@Override
	public double getMaximumTemperature() {
		return maxTemp;
	}

	@Override
	public double getMinimumTemperature() {
		return minTemp;
	}

	@Override
	public void subscribeCurrentTemperature(HomekitCharacteristicChangeCallback arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unsubscribeCurrentTemperature() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Collection<Service> getServices() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void setMinMaxTemp(Double temp) {
		if(temp > maxTemp) {
			maxTemp = temp;
		}
		if(temp < minTemp) {
			minTemp = temp;
		}
	}

}
