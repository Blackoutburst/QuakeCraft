package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.core.GameOption;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandToggleJump {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		run(p);
	}
	
	public static void run(Player p) {
		GameOption.JUMP = !GameOption.JUMP;
		for (QuakePlayer qp : Main.players)
			qp.getPlayer().sendMessage(p.getDisplayName()+" §bhas "+(GameOption.JUMP ? "§aEnabled" : "§cDisabled")+" §6Jump");
	}
}
