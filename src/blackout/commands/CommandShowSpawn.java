package blackout.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import blackout.quake.core.Core;
import blackout.quake.main.Main;

public class CommandShowSpawn {

	public void execute(String[] args) {
		if (args.length == 0) return;
		
		StringBuilder worldName = new StringBuilder();
		
		for (String s : args) {
			worldName.append(s).append(" ");
		}
		
		worldName = new StringBuilder(worldName.substring(0, worldName.length() - 1));
		
		Core.loadRespawn(worldName.toString());
		
		for (Location l : Main.respawns) {
            Block b = l.getWorld().getBlockAt(l);
            b.setType(Material.SPONGE);
        }
	}
}
