package com.blackoutburst.quake.core;

import com.blackoutburst.quake.main.Main;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;

public class SpawnWand {

    public static void leftClick(BlockBreakEvent event) {
        Player p = event.getPlayer();
        Block b = event.getBlock();

        if (Utils.isWand(p.getInventory())) {
            event.setCancelled(true);
            if (Utils.isSpawn(b.getLocation())) {
                Location s = Utils.getSpawn(b.getLocation());
                s.setYaw(s.getYaw() + 45);
                if (s.getYaw() >= 360)
                    s.setYaw(0);
                Utils.saveSpawns(p.getWorld().getName());
                p.sendMessage("§6Spawn rotation set to §b"+s.getYaw()+"°");
            }
        }
    }

    private static void createSpawn(Player p, Block b) {
        Location s = b.getLocation().clone();
        s.setX(s.getX()+0.5f);
        s.setY(s.getY()+0.5f);
        s.setZ(s.getZ()+0.5f);

        if (Main.respawns.size() > 0 && !Main.respawns.get(0).getWorld().getName().equals(p.getWorld().getName())) {
            Main.respawns.clear();
            if (new File("./plugins/Quake/"+p.getWorld().getName()+".yml").exists()) {
                p.sendMessage("§aAutomatically loaded the spawn of §e"+p.getWorld().getName());
                Core.loadRespawn(p.getWorld().getName());
            } else {
                p.sendMessage("§aThe world §e"+p.getWorld().getName()+" §adoesn't seems to have a spawn configuration file, a new one has been made");
            }
        }
        if (!new File("./plugins/Quake/"+p.getWorld().getName()+".yml").exists()) {
            p.sendMessage("§aThe world §e"+p.getWorld().getName()+" §adoesn't seems to have a spawn configuration file, a new one has been made");
        }

        Main.respawns.add(s);
        Utils.saveSpawns(p.getWorld().getName());
        Utils.spawnParticleCubeCustom(b, p, EnumParticle.VILLAGER_HAPPY);
        p.sendMessage("§aNew spawn created at location §b("+b.getX()+", "+b.getY()+", "+b.getZ()+")");
    }

    public static void deleteSpawn(Player p, Block b) {
        Location s = Utils.getSpawn(b.getLocation());
        Main.respawns.remove(s);
        Utils.saveSpawns(p.getWorld().getName());
        Utils.spawnParticleCubeCustom(b, p, EnumParticle.FLAME);
        p.sendMessage("§cSpawn removed at location §b("+b.getX()+", "+b.getY()+", "+b.getZ()+")");
    }

    public static void rightClick(PlayerInteractEvent event) {
        if (Utils.isWand(event.getPlayer().getInventory()) && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = event.getPlayer();
            Block b = event.getClickedBlock();
            if (b == null) return;

            if (!Utils.isSpawn(b.getLocation())) {
                createSpawn(p, b);
            } else {
                deleteSpawn(p, b);
            }
            event.setCancelled(true);
        }
    }
}
