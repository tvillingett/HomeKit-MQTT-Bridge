package com.mickwheelz.homekitmqttbridge;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class MQTTPublish {
	
	private MqttClient mqttClient = Main.client;
	private Integer pubQoS = 0; //get from preferences
	
	public void publishTopic(String topic, String msg) {
		
		MqttTopic mqttTopic = mqttClient.getTopic(topic);
		MqttMessage message = new MqttMessage(msg.getBytes());
		
		message.setQos(pubQoS);
		message.setRetained(false);

		// Publish the message
		try {
			if(!mqttClient.isConnected()) {
				mqttClient.connect();
			}
			MqttDeliveryToken token = null;
	    	System.out.println("Publishing to topic: \"" + topic + "\" msg: " + "\"" + msg + "\"");
			token = mqttTopic.publish(message);
			token.waitForCompletion();
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
