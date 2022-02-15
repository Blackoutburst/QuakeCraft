package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.main.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CommandSetQuakeSpawn {

	public void execute(CommandSender sender) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			File f = new File("plugins/Quake/config/config.yml");
			YamlConfiguration file = YamlConfiguration.loadConfiguration(f);
			Main.spawn = p.getLocation().clone();

			file.set("allow-map-selector", false);
			file.set("spawn", null);
			file.set("spawn.world", p.getWorld().getName());
			file.set("spawn.x", p.getLocation().getX());
			file.set("spawn.y", p.getLocation().getY());
			file.set("spawn.z", p.getLocation().getZ());
			file.set("spawn.yaw", p.getLocation().getYaw());
			file.set("spawn.pitch", p.getLocation().getPitch());

			try {
				file.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
