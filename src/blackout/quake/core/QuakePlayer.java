package blackout.quake.core;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import blackout.menu.BeamMenu;
import blackout.menu.ColorMenu;
import blackout.menu.GunMenu;
import blackout.menu.NameColorMenu;
import blackout.menu.ShapeMenu;
import blackout.menu.SoundsMenu;
import blackout.quake.main.Main;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class QuakePlayer {
	protected Player player;
	protected float cooldown;
	protected float dashCooldown;
	protected Board board;
	protected int score;
	protected GunProfile gunProfile;
	protected int jumpPadCooldown;
	
	public QuakePlayer(Player player, GunProfile gunProfile) {
		this.player = player;
		this.cooldown = 0;
		this.dashCooldown = 0;
		this.board = null;
		this.score = 0;
		this.gunProfile = gunProfile;
		this.jumpPadCooldown = 0;
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
		
		String reload = cooldown == 0 ? "§a"+String.format("%.2f", GameOption.FIRE_DELAY / 20)+"s "+reloadString() : "§c"+String.format("%.2f", cooldown / 20)+"s "+reloadString();
		
		connection.sendPacket(new PacketPlayOutChat(new ChatComponentText(reload), (byte) 2));
	}
	
	private String reloadString() {
		if (cooldown <= 0) return "§8[§a▪▪▪▪▪▪▪▪▪▪§8]";
		if (cooldown <= GameOption.FIRE_DELAY * 10 / 100) return "§8[§a▪▪▪▪▪▪▪▪▪§c▪§8]";
		if (cooldown <= GameOption.FIRE_DELAY * 20 / 100) return "§8[§a▪▪▪▪▪▪▪▪§c▪▪§8]";
		if (cooldown <= GameOption.FIRE_DELAY * 30 / 100) return "§8[§a▪▪▪▪▪▪▪§c▪▪▪§8]";
		if (cooldown <= GameOption.FIRE_DELAY * 40 / 100) return "§8[§a▪▪▪▪▪▪§c▪▪▪▪§8]";
		if (cooldown <= GameOption.FIRE_DELAY * 50 / 100) return "§8[§a▪▪▪▪▪§c▪▪▪▪▪§8]";
		if (cooldown <= GameOption.FIRE_DELAY * 60 / 100) return "§8[§a▪▪▪▪§c▪▪▪▪▪▪§8]";
		if (cooldown <= GameOption.FIRE_DELAY * 70 / 100) return "§8[§a▪▪▪§c▪▪▪▪▪▪▪§8]";
		if (cooldown <= GameOption.FIRE_DELAY * 80 / 100) return "§8[§a▪▪§c▪▪▪▪▪▪▪▪§8]";
		if (cooldown <= GameOption.FIRE_DELAY * 90 / 100) return "§8[§a▪§c▪▪▪▪▪▪▪▪▪§8]";
		if (cooldown <= GameOption.FIRE_DELAY) return "§8[§c▪▪▪▪▪▪▪▪▪▪§8]";
		
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

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public float getDashCooldown() {
		return dashCooldown;
	}

	public void setDashCooldown(float dashCooldown) {
		this.dashCooldown = dashCooldown;
	}

	public GunProfile getGunProfile() {
		return gunProfile;
	}
	
	public int getJumpPadCooldown() {
		return jumpPadCooldown;
	}

	public void setJumpPadCooldown(int jumpPadCooldown) {
		this.jumpPadCooldown = jumpPadCooldown;
	}

	public void savePlayerData(String field, int data) {
		try {
			File f = new File("./plugins/Quake/player data/"+this.player.getUniqueId().toString().replace("-", "")+".yml");
			
			if (!f.exists()) f.createNewFile();
			
			YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
			config.set(field, data);
			config.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readPlayerData() {
		YamlConfiguration playerData = YamlConfiguration.loadConfiguration(new File("./plugins/Quake/player data/"+this.player.getUniqueId().toString().replace("-", "")+".yml"));

		GunMenu.getValue(playerData.getInt("gun"), this.player, false);
		ShapeMenu.getValue(playerData.getInt("shape"), this.player, false);
		ColorMenu.getValue(playerData.getInt("color"), this.player, false);
		SoundsMenu.getValue(playerData.getInt("sound"), this.player, false);
		NameColorMenu.getValue(playerData.getInt("nameColor"), this.player, false);
		BeamMenu.getValue(playerData.getInt("trail"), this.player, false);
	}
}
