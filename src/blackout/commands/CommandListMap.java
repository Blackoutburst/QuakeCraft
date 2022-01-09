package blackout.commands;

import java.io.File;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import blackout.quake.main.Main;

public class CommandListMap {

	public void execute(CommandSender sender, String[] args) {
		File index = new File("./plugins/Quake/");
		String[] entries = index.list();
		
		if (entries == null) return;

		sender.sendMessage("§b-------------------");
		sender.sendMessage("World name | Number of spawns");
		sender.sendMessage(" ");
		for(String s: entries) {
			File tmp = new File(index.getPath(), s);
			if (tmp.isDirectory()) continue;
			
			YamlConfiguration config = YamlConfiguration.loadConfiguration(tmp);
			
			Main.respawns.clear();
			Set<String> respawns = config.getConfigurationSection("loc").getKeys(false);
			
			sender.sendMessage("§6"+tmp.getName()+" §r|§e "+respawns.size());
		}
		sender.sendMessage("§b-------------------");
	}
}
