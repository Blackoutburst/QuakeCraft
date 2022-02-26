package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.main.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn {

	public void execute(CommandSender sender) {
		if (sender instanceof Player) {
			((Player) sender).teleport(Main.spawn);
		}
	}
}
