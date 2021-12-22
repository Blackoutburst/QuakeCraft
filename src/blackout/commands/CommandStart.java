package blackout.commands;

import java.io.File;

import org.bukkit.command.CommandSender;

import blackout.quake.core.Core;

public class CommandStart {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cYou must specifie the world name!");
			return;
			
		}
		
		String worldName = "";
		
		for (String s : args) {
			worldName += s+" ";
		}
		
		worldName = worldName.substring(0, worldName.length() - 1);
		
		if (!new File("./plugins/Quake/"+worldName+".yml").exists()) {
			sender.sendMessage("§cThe world §f"+worldName+"§c doesn't exist!");
			return;
		}
		
		Core.startGame(worldName);

	}
	
}
