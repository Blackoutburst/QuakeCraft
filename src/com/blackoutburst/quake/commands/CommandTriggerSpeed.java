package com.blackoutburst.quake.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blackoutburst.quake.core.GameOption;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;

import java.util.ArrayList;

public class CommandTriggerSpeed {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cSpecifie a value in seconds");
			return;
		}
		
		float delay = 0.85f;
		
		try {
			delay = Float.parseFloat(args[0]);
		} catch (Exception e) {
			sender.sendMessage("§cThe value must be a valid number");
		}
		GameOption.FIRE_DELAY = 20 * delay;
		
		Player p = (Player) sender;
		
		for (QuakePlayer qp : new ArrayList<> (Main.players))
			qp.getPlayer().sendMessage(p.getDisplayName()+" §bhas set the trigger speed to §6"+delay+"s");
	}
}
