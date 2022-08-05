package karu1231.mcutil.command;

import com.google.common.collect.ImmutableList;
import karu1231.mcutil.message.ErrorMessage;
import karu1231.mcutil.message.Message;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand implements TabExecutor {

	protected final String name;

	public AbstractCommand(JavaPlugin plugin, String name){
		this(name);
		//register
		plugin.getCommand(name).setExecutor(this);
		plugin.getCommand(name).setTabCompleter(this);
	}

	public AbstractCommand(String name){
		this.name = name;
	}

	public String getLabel(){
		return this.name;
	}

	public abstract boolean isCanExecute(CommandSender sender);

	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
		if(isCanExecute(sender))
			return onCommand_execute(sender, command, label, args);
		else{
			Message.Error(sender, ErrorMessage.noperm);
			return true;
		}
	};

	protected abstract boolean onCommand_execute(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);

	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
		return new ArrayList<>();
	}
}
