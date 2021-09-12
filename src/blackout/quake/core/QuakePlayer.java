package blackout.quake.core;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import blackout.quake.main.Main;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class QuakePlayer {
	protected Player player;
	protected float cooldown;
	
	public QuakePlayer(Player player) {
		this.player = player;
		cooldown = 0;
	}
	
	public static QuakePlayer getFromPlayer(Player p) {
		for (QuakePlayer qp : Main.players) {
			if (qp.player.getUniqueId().equals(p.getUniqueId())) {
				return (qp);
			}
		}
		return (null);
	}
	
	public void displayCooldown() {
		PlayerConnection connection = ((CraftPlayer) this.player).getHandle().playerConnection;
		
		String reload = cooldown == 0 ? reloadString() : reloadString()+" §c"+String.valueOf((float)(1*cooldown/100))+"s";
		
		connection.sendPacket(new PacketPlayOutChat(new ChatComponentText(reload), (byte) 2));
	}
	
	private String reloadString() {
		if (cooldown <= 0) return "§8[§a▪▪▪▪▪▪▪▪▪▪§8]";
		if (cooldown <= 10) return "§8[§a▪▪▪▪▪▪▪▪▪§c▪§8]";
		if (cooldown <= 20) return "§8[§a▪▪▪▪▪▪▪▪§c▪▪§8]";
		if (cooldown <= 30) return "§8[§a▪▪▪▪▪▪▪§c▪▪▪§8]";
		if (cooldown <= 40) return "§8[§a▪▪▪▪▪▪§c▪▪▪▪§8]";
		if (cooldown <= 50) return "§8[§a▪▪▪▪▪§c▪▪▪▪▪§8]";
		if (cooldown <= 60) return "§8[§a▪▪▪▪§c▪▪▪▪▪▪§8]";
		if (cooldown <= 70) return "§8[§a▪▪▪§c▪▪▪▪▪▪▪§8]";
		if (cooldown <= 80) return "§8[§a▪▪§c▪▪▪▪▪▪▪▪§8]";
		if (cooldown <= 90) return "§8[§a▪§c▪▪▪▪▪▪▪▪▪§8]";
		if (cooldown <= 100) return "§8[§c▪▪▪▪▪▪▪▪▪▪§8]";
		
		return "§8[§a▪▪▪▪▪▪▪▪▪▪§8]";
	}

	public float getCooldown() {
		return cooldown;
	}

	public void setCooldown(float cooldown) {
		this.cooldown = cooldown;
	}
	
}
