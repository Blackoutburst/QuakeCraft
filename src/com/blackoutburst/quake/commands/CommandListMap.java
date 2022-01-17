package com.blackoutburst.quake.commands;

import java.io.File;
import java.util.Arrays;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class CommandListMap {

	public void execute(CommandSender sender) {
		File index = new File("./plugins/Quake/");
		String[] entries = index.list();
		if (entries == null) return;
		Arrays.sort(entries);

		sender.sendMessage("§b-------------------");
		sender.sendMessage("§6World name §r|§e Number of spawns");
		sender.sendMessage(" ");
		sender.sendMessage("§e"+(entries.length - 1)+" §6worlds found");
		sender.sendMessage(" ");
		for(String s: entries) {
			File tmp = new File(index.getPath(), s);
			if (tmp.isDirectory()) continue;
			
			YamlConfiguration config = YamlConfiguration.loadConfiguration(tmp);
			
			Set<String> respawns = config.getConfigurationSection("loc").getKeys(false);
			
			sender.sendMessage("§6"+tmp.getName().replace(".yml", "")+" §r|§e "+respawns.size());
		}
		sender.sendMessage("§b-------------------");
	}
}
