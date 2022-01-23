package com.blackoutburst.quake.commands;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blackoutburst.quake.core.GunProfile;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.core.ScoreboardManager;
import com.blackoutburst.quake.main.Main;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class CommandQueue {

	public void execute(CommandSender sender) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (QuakePlayer.getFromPlayer(p) == null) {
				
				GunProfile gunProfile = new GunProfile("§bWooden Case", Material.WOOD_HOE, Type.BALL, Color.AQUA, false, Sound.BLAZE_DEATH, 2, ChatColor.WHITE, EnumParticle.FIREWORKS_SPARK);
				QuakePlayer qp = new QuakePlayer(p, gunProfile);
				ScoreboardManager.init(qp);
				
				for (QuakePlayer qps : Main.players)
					qps.getPlayer().sendMessage(p.getDisplayName()+" §6joined the queue!");
				
				
				Main.players.add(qp);
				qp.readPlayerData();
				
				
				ScoreboardManager.updatePlayers();
				sender.sendMessage("§aYou joined the queue !");
				
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
