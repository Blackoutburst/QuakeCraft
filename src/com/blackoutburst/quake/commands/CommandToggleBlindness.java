package com.blackoutburst.quake.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blackoutburst.quake.core.GameOption;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;

import java.util.ArrayList;

public class CommandToggleBlindness {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		run(p);
	}
	
	public static void run(Player p) {
		GameOption.BLINDNESS = !GameOption.BLINDNESS;
		for (QuakePlayer qp : Main.players)
			qp.getPlayer().sendMessage(p.getDisplayName()+" §bhas "+(GameOption.BLINDNESS ? "§aEnabled" : "§cDisabled")+" §6Blindness");
	}
}
