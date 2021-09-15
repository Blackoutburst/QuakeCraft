package blackout.quake.core;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import blackout.quake.main.Main;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Scoreboard;

public class QuakePlayer {
	protected Player player;
	protected float cooldown;
	protected Scoreboard board;
	
	public QuakePlayer(Player player) {
		this.player = player;
		this.cooldown = 0;
		this.board = null;
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
		
		String reload = cooldown == 0 ? "§a"+String.format("%.2f", (float)(RailGun.FIRE_DELAY / 20))+"s "+reloadString() : "§c"+String.format("%.2f", (float)(cooldown / 20))+"s "+reloadString();
		
		connection.sendPacket(new PacketPlayOutChat(new ChatComponentText(reload), (byte) 2));
	}
	
	private String reloadString() {
		if (cooldown <= 0) return "§8[§a▪▪▪▪▪▪▪▪▪▪§8]";
		if (cooldown <= RailGun.FIRE_DELAY * 10 / 100) return "§8[§a▪▪▪▪▪▪▪▪▪§c▪§8]";
		if (cooldown <= RailGun.FIRE_DELAY * 20 / 100) return "§8[§a▪▪▪▪▪▪▪▪§c▪▪§8]";
		if (cooldown <= RailGun.FIRE_DELAY * 30 / 100) return "§8[§a▪▪▪▪▪▪▪§c▪▪▪§8]";
		if (cooldown <= RailGun.FIRE_DELAY * 40 / 100) return "§8[§a▪▪▪▪▪▪§c▪▪▪▪§8]";
		if (cooldown <= RailGun.FIRE_DELAY * 50 / 100) return "§8[§a▪▪▪▪▪§c▪▪▪▪▪§8]";
		if (cooldown <= RailGun.FIRE_DELAY * 60 / 100) return "§8[§a▪▪▪▪§c▪▪▪▪▪▪§8]";
		if (cooldown <= RailGun.FIRE_DELAY * 70 / 100) return "§8[§a▪▪▪§c▪▪▪▪▪▪▪§8]";
		if (cooldown <= RailGun.FIRE_DELAY * 80 / 100) return "§8[§a▪▪§c▪▪▪▪▪▪▪▪§8]";
		if (cooldown <= RailGun.FIRE_DELAY * 90 / 100) return "§8[§a▪§c▪▪▪▪▪▪▪▪▪§8]";
		if (cooldown <= RailGun.FIRE_DELAY) return "§8[§c▪▪▪▪▪▪▪▪▪▪§8]";
		
		return "§8[§a▪▪▪▪▪▪▪▪▪▪§8]";
	}

	public float getCooldown() {
		return cooldown;
	}

	public void setCooldown(float cooldown) {
		this.cooldown = cooldown;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Scoreboard getBoard() {
		return board;
	}

	public void setBoard(Scoreboard board) {
		this.board = board;
	}
	
}
