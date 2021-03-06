package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.core.ScoreboardManager;
import com.blackoutburst.quake.main.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandLeaveQueue {

	public void execute(CommandSender sender) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (QuakePlayer.getFromPlayer(p) == null) {
				sender.sendMessage("§cYou are not in the queue !");
			} else {
				
				final QuakePlayer qp = QuakePlayer.getFromPlayer(p);
				ScoreboardManager.clear(qp);
				
				Main.players.remove(qp);
				
				for (QuakePlayer qps : Main.players)
					qps.getPlayer().sendMessage(p.getDisplayName()+" §6left the queue!");
				
				sender.sendMessage("§aYou left the queue !");
				ScoreboardManager.updatePlayers();
			}
		}
	}
}
