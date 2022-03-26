package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.core.GunProfile;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.core.ScoreboardManager;
import com.blackoutburst.quake.main.Main;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandJoinQueue {

	public void execute(CommandSender sender) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (QuakePlayer.getFromPlayer(p) == null) {
				
				GunProfile gunProfile = new GunProfile("§bWooden Case", Material.WOODEN_HOE, Type.BALL, Color.AQUA, false, Sound.ENTITY_BLAZE_DEATH, 2, ChatColor.WHITE, Particle.FIREWORKS_SPARK);
				QuakePlayer qp = new QuakePlayer(p, gunProfile);
				ScoreboardManager.init(qp);
				
				for (QuakePlayer qps : Main.players)
					qps.getPlayer().sendMessage(p.getDisplayName()+" §6joined the queue!");
				
				
				Main.players.add(qp);
				qp.readPlayerData();
				
				
				ScoreboardManager.updatePlayers();
				sender.sendMessage("§aYou joined the queue !");
				
			} else {
				sender.sendMessage("§cYou are already in the queue !");
			}
		}
	}
}
