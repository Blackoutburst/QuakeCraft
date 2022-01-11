package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.quake.core.GameOption;

public class CommandResetConfig {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas restaured the default configuration");
		GameOption.restoreConfiguration();
	}
}
