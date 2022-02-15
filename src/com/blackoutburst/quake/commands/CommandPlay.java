package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.main.Main;
import com.blackoutburst.quake.menu.MapMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPlay {

	public void execute(CommandSender sender) {
		if (sender instanceof Player) {
			if (Main.allowMapSelector) {
				MapMenu.open((Player) sender);
			} else {
				sender.sendMessage("§cI'm sorry, this command is unavailable!");
			}
		}
	}
}
