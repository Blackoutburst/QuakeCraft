package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.core.GameOption;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDashDelay {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cSpecifie a value in seconds");
			return;
		}
		
		float delay = 1;
		
		try {
			delay = Float.parseFloat(args[0]);
		} catch (Exception e) {
			sender.sendMessage("§cThe value must be a valid number");
		}
		GameOption.DASH_DELAY = 20 * delay;
		
		Player p = (Player) sender;
		
		for (QuakePlayer qp : Main.players)
			qp.getPlayer().sendMessage(p.getDisplayName()+" §bhas set the dash delay to §6"+delay+"s");
	}
}
