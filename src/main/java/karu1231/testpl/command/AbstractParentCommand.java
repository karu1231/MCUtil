package karu1231.testpl.command;

import com.google.common.collect.ImmutableList;
import karu1231.testpl.message.ErrorMessage;
import karu1231.testpl.message.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParentCommand extends AbstractCommand {
	
	public final ImmutableList<AbstractCommand> subcommands;

	public final int parent_start;

	public AbstractParentCommand(String name, ImmutableList<AbstractCommand> subcommands) {
		this(name,subcommands,0);
	}
	
	public AbstractParentCommand(String name, ImmutableList<AbstractCommand> subcommands, int parent_start) {
		super(name);
		this.subcommands = subcommands;
		this.parent_start = parent_start;
	}

	/**
	 * from {@link AbstractCommand}
	 */
	public abstract boolean isCanExecute(CommandSender sender);

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String commandLabel, String[] args) {

		if(args.length == parent_start) {

			Message.Error(sender, ErrorMessage.args);
			return true;
		}

		for(AbstractCommand command:subcommands) {
			if(command.getLabel().equalsIgnoreCase(args[parent_start])) {

				if(!command.isCanExecute(sender)) {

					Message.Error(sender, ErrorMessage.noperm);
					return true;
				}

				command.onCommand(sender, cmd, commandLabel, args);
				return true;
			}

		}

		Message.Error(sender, ErrorMessage.args);
		return true;
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
