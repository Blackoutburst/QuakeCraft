package blackout.commands;

import org.bukkit.command.CommandSender;

import blackout.quake.core.Core;
import blackout.quake.main.Main;

public class CommandEnd {

	public void execute(CommandSender sender) {
		if (Main.gameRunning)
			Core.endGame();
		else
			sender.sendMessage("§cNo game are running right now !");
	}
	
}
