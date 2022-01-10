package blackout.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandScan {
	
	 /**
     * Save every spawn location inside a file
     * @param pos index in the yaml file
     * @param x position x in world
     * @param y position y in world
     * @param z position z in world
     */
    private void saveLocation(int pos, String worldName, int x, int y, int z) {
        YamlConfiguration file = YamlConfiguration.loadConfiguration(new File("plugins/Quake/"+worldName+".yml"));

        file.set("loc."+pos+".world", worldName);
        file.set("loc."+pos+".x", x);
        file.set("loc."+pos+".y", y);
        file.set("loc."+pos+".z", z);
        try {
            file.save(new File("plugins/Quake/"+worldName+".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 6) return false;

        int x1 = Integer.min(Integer.parseInt(args[0]), Integer.parseInt(args[3]));
        int y1 = Integer.min(Integer.parseInt(args[1]), Integer.parseInt(args[4]));
        int z1 = Integer.min(Integer.parseInt(args[2]), Integer.parseInt(args[5]));

        int x2 = Integer.max(Integer.parseInt(args[0]), Integer.parseInt(args[3]));
        int y2 = Integer.max(Integer.parseInt(args[1]), Integer.parseInt(args[4]));
        int z2 = Integer.max(Integer.parseInt(args[2]), Integer.parseInt(args[5]));

        Player player = (Player) sender;
        World world = player.getWorld();

        sender.sendMessage("§bStarting scan");
        int pos = 0;

        File tmp = new File("plugins/Quake/"+world.getName()+".yml");
        if (tmp.exists()) tmp.delete();

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    Block b = world.getBlockAt(new Location(world, x, y, z));
                    if (b.getType().equals(Material.SPONGE)) {
                        saveLocation(pos, world.getName(), x, y, z);
                        pos++;
                    }
                }
            }
        }
        sender.sendMessage("§bScan complete found §6"+pos+" §blocation");

        return (true);
    }
	
}
