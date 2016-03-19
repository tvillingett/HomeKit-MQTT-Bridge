package com.mickwheelz.homekitmqttbridge.objects;

import java.math.BigInteger;
import java.net.InetAddress;

public class PreferenceStore {
	
	private String pin;
	private String mac;
	private BigInteger salt;
	private byte[] privateKey;
	private Boolean hasUser;
	
	private String brokerAddress;
	private String mqttClientName;
	
	private String bridgeName;
	private String bridgeManu;
	private String bridgeVer;
	private String bridgeSN;
	private Integer bridgePort;
	private InetAddress iface;
	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public BigInteger getSalt() {
		return salt;
	}
	public void setSalt(BigInteger salt) {
		this.salt = salt;
	}
	public byte[] getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(byte[] bs) {
		this.privateKey = bs;
	}
	public Boolean getHasUser() {
		return hasUser;
	}
	public void setHasUser(Boolean hasUser) {
		this.hasUser = hasUser;
	}
	public String getBrokerAddress() {
		return brokerAddress;
	}
	public void setBrokerAddress(String brokerAddress) {
		this.brokerAddress = brokerAddress;
	}
	public String getMqttClientName() {
		return mqttClientName;
	}
	public void setMqttClientName(String mqttClientName) {
		this.mqttClientName = mqttClientName;
	}
	public String getBridgeName() {
		return bridgeName;
	}
	public void setBridgeName(String bridgeName) {
		this.bridgeName = bridgeName;
	}
	public String getBridgeManu() {
		return bridgeManu;
	}
	public void setBridgeManu(String bridgeManu) {
		this.bridgeManu = bridgeManu;
	}
	public String getBridgeVer() {
		return bridgeVer;
	}
	public void setBridgeVer(String bridgeVer) {
		this.bridgeVer = bridgeVer;
	}
	public String getBridgeSN() {
		return bridgeSN;
	}
	public void setBridgeSN(String bridgeSN) {
		this.bridgeSN = bridgeSN;
	}
	public Integer getBridgePort() {
		return bridgePort;
	}
	public void setBridgePort(Integer bridgePort) {
		this.bridgePort = bridgePort;
	}
	public InetAddress getIface() {
		return iface;
	}
	public void setIface(InetAddress iface) {
		this.iface = iface;
	}
 
	
}
