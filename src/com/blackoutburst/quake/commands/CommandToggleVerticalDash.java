package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.core.GameOption;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandToggleVerticalDash {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		run(p);
	}
	
	public static void run(Player p) {
		GameOption.VERTICAL_DASH = !GameOption.VERTICAL_DASH;
		for (QuakePlayer qp : Main.players)
			qp.getPlayer().sendMessage(p.getDisplayName()+" §bhas "+(GameOption.VERTICAL_DASH ? "§aEnabled" : "§cDisabled")+" §6Vertical Dash");
	}
}
