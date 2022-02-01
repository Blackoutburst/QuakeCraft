package com.blackoutburst.quake.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blackoutburst.quake.core.GameOption;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;

import java.util.ArrayList;

public class CommandMaxScore {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cSpecifie a score");
			return;
		}
		
		int score = 25;

		try {
			score = Integer.parseInt(args[0]);
		} catch (Exception e) {
			sender.sendMessage("§cThe value must be a valid number");
		}
		GameOption.MAX_SCORE = score;
		
		Player p = (Player) sender;
		
		for (QuakePlayer qp : new ArrayList<>(Main.players))
			qp.getPlayer().sendMessage(p.getDisplayName()+" §bhas set the max score to §6"+score);
	}
}
