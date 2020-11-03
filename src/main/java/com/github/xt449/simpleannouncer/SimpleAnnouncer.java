package com.github.xt449.simpleannouncer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * @author xt449 / BinaryBanana
 */
public final class SimpleAnnouncer extends JavaPlugin {

	private MainConfiguration mainConfiguration;

	private int index = 0;
	private final Random random = new Random();

	@Override
	public final void onEnable() {
		mainConfiguration = new MainConfiguration(this);
		mainConfiguration.initialize();

		if(mainConfiguration.automated_sequential) {
			Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
				Bukkit.broadcastMessage(mainConfiguration.automated_prefix + mainConfiguration.automated_messages.get(index));
				index++;
				if(index >= mainConfiguration.automated_messages.size()) {
					index = 0;
				}
			}, 0, mainConfiguration.automated_delay * 20);
		} else {
			Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
				Bukkit.broadcastMessage(mainConfiguration.automated_prefix + mainConfiguration.automated_messages.get(random.nextInt(mainConfiguration.automated_messages.size())));
			}, 0, mainConfiguration.automated_delay * 20);
		}
	}

	@Override
	public final void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if(args.length > 0) {
			Bukkit.broadcastMessage(mainConfiguration.manual_prefix + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
			return true;
		}
		return false;
	}
}
