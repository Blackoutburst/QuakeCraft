package blackout.commands;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import blackout.quake.core.Core;
import blackout.quake.main.Main;

public class CommandClean {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cInvalid usage try §e/clean <worldName>");
			return;
		}
		
		StringBuilder worldName = new StringBuilder();
		
		for (String s : args) {
			worldName.append(s).append(" ");
		}
		
		worldName = new StringBuilder(worldName.substring(0, worldName.length() - 1).toLowerCase());
		
		
		File index = new File("./plugins/Quake/");
		String[] entries = index.list();
		String finalWorldName = null;
		
		if (entries == null) return;

		for(String s: entries) {
			File tmp = new File(index.getPath(), s);
			if (tmp.isDirectory()) continue;
			
			if (tmp.getName().replace(".yml", "").equalsIgnoreCase(worldName.toString())) {
				finalWorldName = tmp.getName().replace(".yml", "");
			}
		}
		
		if (finalWorldName == null) {
			sender.sendMessage("§cThe world §f"+worldName+"§c doesn't exist!");
			return;
		}
		
		Core.loadRespawn(finalWorldName);
		
		for (Location l : Main.respawns) {
            Block b = l.getWorld().getBlockAt(l);
            b.setType(Material.AIR);
        }
		sender.sendMessage("§bRemoved sponges on the §6"+Main.respawns.size()+" §bspawnpoints found in §6"+finalWorldName);
	}
}
