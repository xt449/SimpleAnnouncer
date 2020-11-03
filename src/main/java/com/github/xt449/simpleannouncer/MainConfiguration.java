package com.github.xt449.simpleannouncer;

import com.github.xt449.spigotutilitylibrary.AbstractConfiguration;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xt449 / BinaryBanana
 */
final class MainConfiguration extends AbstractConfiguration {

	MainConfiguration(Plugin plugin) {
		super(plugin, "config.yml");
	}

	String automated_prefix;
	int automated_delay;
	boolean automated_sequential;
	List<String> automated_messages;
	String manual_prefix;

	@Override
	protected final void readValues() {
		automated_prefix = ChatColor.translateAlternateColorCodes('&', config.getString("automated.prefix"));
		automated_delay = config.getInt("automated.delay");
		automated_sequential = config.getBoolean("automated.sequential");
		automated_messages = config.getStringList("automated.messages").stream().map(message -> ChatColor.translateAlternateColorCodes('&', message)).collect(Collectors.toList());
		manual_prefix = ChatColor.translateAlternateColorCodes('&', config.getString("manual.prefix"));
	}
}
