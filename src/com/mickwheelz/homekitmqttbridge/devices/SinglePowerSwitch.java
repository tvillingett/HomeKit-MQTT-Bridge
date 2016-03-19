package com.mickwheelz.homekitmqttbridge.devices;

import java.util.concurrent.CompletableFuture;
import com.beowulfe.hap.HomekitCharacteristicChangeCallback;
import com.beowulfe.hap.accessories.Lightbulb;
import com.mickwheelz.homekitmqttbridge.MQTTPublish;

public class SinglePowerSwitch implements Lightbulb {
	
	private boolean powerState = false;
	private HomekitCharacteristicChangeCallback subscribeCallback = null;
	private int devId = 2;
	private String devLabel = null;
	private MQTTPublish publisher = new MQTTPublish();
	private Boolean mqttUp = false; //Main.client.equals(null);
	
	
	public SinglePowerSwitch(int devId, String devLabel){
		this.devId = devId;
		this.devLabel = devLabel;
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
	public void identify() {
		System.out.println("Identifying light");
	}

	@Override
	public String getSerialNumber() {
		return "SW" + getId();
	}

	@Override
	public String getModel() {
		return "Generic Switch";
	}

	@Override
	public String getManufacturer() {
		return "mickwheelz";
	}

	@Override
	public CompletableFuture<Boolean> getLightbulbPowerState() {
		return CompletableFuture.completedFuture(powerState);
	}

	@Override
	public CompletableFuture<Void> setLightbulbPowerState(boolean powerState)
			throws Exception {
		this.powerState = powerState;
		if (subscribeCallback != null) {
			subscribeCallback.changed();
		}
		
		// topic = dev serial, msg = 1 for on, 0 for off
		if(mqttUp) {
			String pubMsg = (powerState ? "1" : "0");
			publisher.publishTopic(getSerialNumber(), pubMsg);
		}
		

		return CompletableFuture.completedFuture(null);
	}

	@Override
	public void subscribeLightbulbPowerState(
			HomekitCharacteristicChangeCallback callback) {
		this.subscribeCallback = callback;
	}

	@Override
	public void unsubscribeLightbulbPowerState() {
		this.subscribeCallback = null;
	}

}
