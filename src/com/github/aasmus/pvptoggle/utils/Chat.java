package com.github.aasmus.pvptoggle.utils;

import org.bukkit.entity.Player;

import com.github.aasmus.pvptoggle.PvPToggle;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class Chat {

	// sends message without a parameter to player
	public static void send(Player player, String message) {
		String msg = PvPToggle.instance.getConfig().getString("MESSAGES." + message);
		if(msg.equals(""))
			return;
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));	
	}

	// sends message with a parameter to player
	public static void send(Player player, String message, String parameter) {
		String msg = PvPToggle.instance.getConfig().getString("MESSAGES." + message);
		if(msg.equals(""))
			return;
		String output = msg.replaceAll("<parameter>", parameter);
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', output));
	}
	
	// sends message with a parameter to player and pvp state
	public static void send(Player player, String message, String parameter, Boolean pvpState) {
		String msg = PvPToggle.instance.getConfig().getString("MESSAGES." + message);
		if(msg.equals(""))
			return;
		String output = msg.replaceAll("<parameter>", parameter);
		if(pvpState == true) {
			output = output.replaceAll("<pvpstate>", "off");
		} else {
			output = output.replaceAll("<pvpstate>", "on");
		}
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', output));
	}
	
	// sends message without a parameter to console
	public static void send(ConsoleCommandSender console, String message) {
		String msg = PvPToggle.instance.getConfig().getString("MESSAGES." + message);
		if(msg.equals(""))
			return;
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}

	// sends message with a parameter to console
	public static void send(ConsoleCommandSender console, String message, String parameter) {
		String msg = PvPToggle.instance.getConfig().getString("MESSAGES." + message);
		if(msg.equals(""))
			return;
		String output = msg.replaceAll("<parameter>", parameter);
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', output));
	}
	
	// sends message with a parameter and pvp state to console
	public static void send(ConsoleCommandSender console, String message, String parameter, Boolean pvpState) {
		String msg = PvPToggle.instance.getConfig().getString("MESSAGES." + message);
		if(msg.equals(""))
			return;
		String output = msg.replaceAll("<parameter>", parameter);
		if(pvpState == true) {
			output = output.replaceAll("<pvpstate>", "off");
		} else {
			output = output.replaceAll("<pvpstate>", "on");
		}
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', output));
	}

}
