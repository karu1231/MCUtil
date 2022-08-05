package karu1231.mcutil;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCUtil extends JavaPlugin {

	public static Plugin plugin;

	@Override
	public void onEnable() {
		plugin = getPlugin(this.getClass());

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
