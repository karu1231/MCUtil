package karu1231.mcutil.command;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractCommand implements TabExecutor {

	protected final String name;

	public AbstractCommand(String name){
		this.name = name;
	}

	public String getLabel(){
		return this.name;
	}

	public abstract boolean isCanExecute(CommandSender sender);

	public abstract boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);

	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
		return ImmutableList.of();
	}
}
