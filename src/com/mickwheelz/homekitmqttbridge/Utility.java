package com.mickwheelz.homekitmqttbridge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mickwheelz.homekitmqttbridge.objects.HomeKitDevice;
import com.mickwheelz.homekitmqttbridge.objects.PreferenceStore;


public class Utility {

	public void getNetworkInterface() {

		Enumeration<NetworkInterface> nets;
		try {
			nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets)) {
				Enumeration<InetAddress> inetAddr = netint.getInetAddresses();
				for(InetAddress inetint : Collections.list(inetAddr)) {
					System.out.println(netint.getDisplayName() + " " + inetint.getHostAddress());
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	public File preferencesToFile(PreferenceStore preferences, String fileName) {
		
		File prefsFile = new File(fileName);
		
		//Write default preferences to a file
		try {
			Gson gson = new Gson();
			String jsonPrefs = gson.toJson(preferences);
			
			prefsFile.createNewFile();
			
			FileWriter writer = new FileWriter(prefsFile); 
			writer.write(jsonPrefs); 
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prefsFile;
	}

	public PreferenceStore readPreferences(String fileName) throws IOException{
		
		String json = "";
		Gson gson = new Gson();

		File prefsFile = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(prefsFile));

		String sCurrentLine;

		while ((sCurrentLine = br.readLine()) != null) {
			json += sCurrentLine;
		}
		br.close();

		PreferenceStore prefs = gson.fromJson(json, PreferenceStore.class);
				
		return prefs;

	}
	
	public ConcurrentMap<String, byte[]> readUsers(String fileName) {
		
		ConcurrentMap<String, byte[]> users = new ConcurrentHashMap<>();
		
		String json = "";
		Gson gson = new Gson();

		try {

			File prefsFile = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(prefsFile));

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				json += sCurrentLine;
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (json != "") {
			Type collectionType = new TypeToken<ConcurrentMap<String, byte[]>>(){}.getType();
			users = gson.fromJson(json, collectionType);
		}
		
		return users;

	}
		
	public void writeUsersToFile(ConcurrentMap<String, byte[]> users, String fileName) { 
		
		File usersFile = new File(fileName);
		
		try {
			Gson gson = new Gson();
			String jsonUsers = gson.toJson(users);
			
			usersFile.createNewFile();
			
			FileWriter writer = new FileWriter(usersFile); 
			writer.write(jsonUsers); 
			writer.flush();
			writer.close();

		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeAccessoriesToFile(ArrayList<HomeKitDevice> devs, String fileName) {
		
		File accessoriesFile = new File(fileName);
		
		try {
			Gson gson = new Gson();
			String jsonAccessories = gson.toJson(devs);
			
			accessoriesFile.createNewFile();
			
			FileWriter writer = new FileWriter(accessoriesFile); 
			writer.write(jsonAccessories); 
			writer.flush();
			writer.close();

		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<HomeKitDevice> readAccessories(String fileName) throws IOException{
		
		String json = "";
		Gson gson = new Gson();

		File accFile = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(accFile));

		String sCurrentLine;

		while ((sCurrentLine = br.readLine()) != null) {
			json += sCurrentLine;
		}
		br.close();
		Type collectionType = new TypeToken<ArrayList<HomeKitDevice>>(){}.getType();

		return gson.fromJson(json, collectionType);
				
	}
	
}
