package com.mickwheelz.homekitmqttbridge;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.beowulfe.hap.HomekitAuthInfo;

public class HomeKitAuth implements HomekitAuthInfo {
	
	private ConcurrentMap<String, byte[]> users = new ConcurrentHashMap<>();
	private Utility util = new Utility();

	public HomeKitAuth() throws InvalidAlgorithmParameterException {
		users = util.readUsers(Constants.USERS_FILENAME);
		System.out.println(Constants.HOMEKIT_PIN_MSG + Main.prefs.getPin());
	}

	@Override
	public String getPin() {
		return Main.prefs.getPin();
	}

	@Override
	public String getMac() {
		return Main.prefs.getMac();
	}

	@Override
	public BigInteger getSalt() {
		return Main.prefs.getSalt();
	}

	@Override
	public byte[] getPrivateKey() {
		return Main.prefs.getPrivateKey();
	}

	@Override
	public void createUser(String username, byte[] publicKey) {	
		users.putIfAbsent(username, publicKey);
		util.writeUsersToFile(users, Constants.USERS_FILENAME);
		System.out.println(Constants.ADD_PAIRING + username);
	}

	@Override
	public void removeUser(String username) {
		users.remove(username);
		util.writeUsersToFile(users, Constants.USERS_FILENAME);
		System.out.println(Constants.REMOVE_PAIRING + username);
	}

	@Override
	public byte[] getUserPublicKey(String username) {		
		return users.get(username);
	}
	
	@Override
	public boolean hasUser() {
		return users.size() > 0;
	}
}