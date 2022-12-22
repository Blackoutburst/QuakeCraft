package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.core.GameOption;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGravity {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cSpecifie a score");
			return;
		}
		
		int score = 0;

		try {
			score = Integer.parseInt(args[0]);
		} catch (Exception e) {
			sender.sendMessage("§cThe value must be a valid number");
		}
		GameOption.GRAVITY = score;
		
		Player p = (Player) sender;
		
		for (QuakePlayer qp : Main.players)
			qp.getPlayer().sendMessage(p.getDisplayName()+" §bhas set the gravity strength to §6"+score);
	}
}
