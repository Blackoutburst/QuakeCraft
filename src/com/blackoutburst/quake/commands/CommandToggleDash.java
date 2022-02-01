package com.blackoutburst.quake.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blackoutburst.quake.core.GameOption;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;

import java.util.ArrayList;

public class CommandToggleDash {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		run(p);
	}
	
	public static void run(Player p) {
		GameOption.DASH = GameOption.DASH ? false : true;
		for (QuakePlayer qp : new ArrayList<>(Main.players))
			qp.getPlayer().sendMessage(p.getDisplayName()+" §bhas "+(GameOption.DASH ? "§aEnabled" : "§cDisabled")+" §6Dash");
	}
}
