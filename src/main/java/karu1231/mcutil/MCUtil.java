package karu1231.mcutil;

import karu1231.mcutil.noGit.commands.katanasword.katanaswordParentCommand;
import karu1231.mcutil.noGit.commands.samurai.samuraiParentCommand;
import karu1231.mcutil.noGit.commands.testmes.testmesCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCUtil extends JavaPlugin {

	public static Plugin plugin;

	@Override
	public void onEnable() {
		plugin = getPlugin(this.getClass());

		new katanaswordParentCommand(this);
		new samuraiParentCommand(this);
		new testmesCommand(this);

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
