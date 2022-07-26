package karu1231.mcutil.command;

import com.google.common.collect.ImmutableList;
import karu1231.mcutil.message.ErrorMessage;
import karu1231.mcutil.message.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParentCommand extends AbstractCommand {
	
	public final ImmutableList<AbstractCommand> subcommands;

	public final int parent_start;

	public AbstractParentCommand(JavaPlugin plugin, String name, ImmutableList<AbstractCommand> subcommands) {
		this(plugin,name,subcommands,0);
	}
	
	public AbstractParentCommand(JavaPlugin plugin,String name, ImmutableList<AbstractCommand> subcommands, int parent_start) {
		super(plugin,name);
		this.subcommands = subcommands;
		this.parent_start = parent_start;
	}

	public AbstractParentCommand(String name, ImmutableList<AbstractCommand> subcommands) {
		this(name,subcommands,0);
	}
	
	public AbstractParentCommand(String name, ImmutableList<AbstractCommand> subcommands, int parent_start) {
		super(name);
		this.subcommands = subcommands;
		this.parent_start = parent_start;
	}

	protected boolean onCommand_execute(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args){
		if(args.length < parent_start)
			throw new RuntimeException(String.format("Too short args given to parentcommand(/%s).",label));

		if(args.length == parent_start) {
			if(!onCommand_simple(sender, cmd, label, args))
				Message.Error(sender, ErrorMessage.args);
			return true;
		}

		for(AbstractCommand command:subcommands) {
			if(command.getLabel().equalsIgnoreCase(args[parent_start])) {

				command.onCommand(sender, cmd, label, args);
				return true;
			}

		}

		Message.Error(sender, ErrorMessage.args);
		return true;
	}

	protected boolean onCommand_simple(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args){
		return false;
	}

	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
		List<String> result = new ArrayList<>();
		if(args.length == (parent_start+1)){
			for(AbstractCommand executor:subcommands)
				result.add(executor.getLabel().toLowerCase());
			return TabCompleter.Complete(args[parent_start],result);
		}else if(args.length > (parent_start+1)){
			for(AbstractCommand executor:subcommands)
				if(args[parent_start].equalsIgnoreCase(executor.getLabel()))
					return executor.onTabComplete(sender, command, label, args);
		}
		return result;
	}
}
