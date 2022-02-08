package com.blackoutburst.quake.main;

import com.blackoutburst.quake.commands.CommandManager;
import com.blackoutburst.quake.core.Core;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.core.SkullLoader;
import com.blackoutburst.quake.core.Utils;
import com.blackoutburst.quake.event.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main extends JavaPlugin {

	public static List<QuakePlayer> players = new ArrayList<>();
	public static boolean gameRunning = false;
	public static int gameTime = 0;
	public static List<Location> respawns = new ArrayList<>();
	
	public static World gameWorld;
	
	public static Location spawn;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		new Core().cooldownTimer();
		new Core().gameTimer();
		new File("./plugins/Quake/player data/").mkdirs();
		SkullLoader.load();
		Utils.spawnParticlesScheduler();

		gameWorld = Bukkit.getWorld("world");
		spawn = new Location(Bukkit.getWorld("world"), 8.5f, 5.0f, 8.5f, 0, 0);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CommandManager.execute(sender, command, args);
		return true;
	}
}
