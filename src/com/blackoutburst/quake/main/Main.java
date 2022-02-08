package com.blackoutburst.quake.main;

import com.blackoutburst.quake.commands.*;
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
		switch(command.getName().toLowerCase()) {
			case "spawn":
			case "lobby":
			case "l":	
				new CommandSpawn().execute(sender); break;
			case "maxscore": new CommandMaxScore().execute(sender, args); break;
			case "bouncecount": new CommandBounceCount().execute(sender, args); break;
			case "raylength": new CommandRayLength().execute(sender, args); break;
			case "shattercount": new CommandShatterCount().execute(sender, args); break;
			case "shatterlength": new CommandShatterLength().execute(sender, args); break;
			case "listmap": new CommandListMap().execute(sender); break;
			case "start": new CommandStart().execute(sender, args); break;
			case "boop": new CommandBoop().execute(sender, args); break;
			case "end": new CommandEnd().execute(sender); break;
			case "scan": new CommandScan().execute(sender, args); break;
			case "clean": new CommandClean().execute(sender, args); break;
			case "showspawn": new CommandShowSpawn().execute(sender, args); break;
			case "loadspawn": new CommandLoadSpawn().execute(sender, args); break;
			case "triggerspeed": new CommandTriggerSpeed().execute(sender, args); break;
			case "dashdelay": new CommandDashDelay().execute(sender, args); break;
			case "config": new CommandConfig().execute(sender); break;
			case "showconfig": new CommandShowConfig().execute(sender); break;
			case "resetconfig": new CommandResetConfig().execute(sender); break;
			case "togglejump": new CommandToggleJump().execute(sender); break;
			case "togglewalk": new CommandToggleWalk().execute(sender); break;
			case "toggledash": new CommandToggleDash().execute(sender); break;
			case "toggleverticaldash": new CommandToggleVerticalDash().execute(sender); break;
			case "togglenametag": new CommandToggleNametag().execute(sender); break;
			case "toggleinvisibility": new CommandToggleInvisibility().execute(sender); break;
			case "toggleblindness": new CommandToggleBlindness().execute(sender); break;
			case "playerslow": new CommandPlayerSlow().execute(sender, args); break;
			case "playerspeed": new CommandPlayerSpeed().execute(sender, args); break;
			case "playerjump": new CommandPlayerJump().execute(sender, args); break;
			case "dashstrength": new CommandDashStrength().execute(sender, args); break;
			case "play": new CommandPlay().execute(sender); break;
			case "joinqueue": new CommandJoinQueue().execute(sender); break;
			case "leavequeue": new CommandLeaveQueue().execute(sender); break;
			case "queue": new CommandQueue().execute(sender); break;
			case "gravity": new CommandGravity().execute(sender, args); break;
			case "spawnwand": new CommandSpawnWand().execute(sender); break;
			default: return true;
		}
		return true;
	}
}
