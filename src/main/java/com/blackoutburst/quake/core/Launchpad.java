package com.blackoutburst.quake.core;

import com.blackoutburst.quake.main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Launchpad {

    private static void redstonePad(Player player, QuakePlayer qp, World world) {
        qp.setJumpPadCooldown(1);
        Vector dash = player.getLocation().getDirection().clone();
        world.playSound(player.getLocation(), Sound.PISTON_EXTEND, 4, 0.5f);

        dash.setY(0.05f);
        dash.setX(0.0f);
        dash.setZ(0.0f);

        player.setVelocity(dash.multiply(5));

        new BukkitRunnable(){
            @Override
            public void run(){
                Vector dash = player.getLocation().getDirection().clone();
                dash.setY(0.1f);
                player.setVelocity(dash.multiply(5));
                this.cancel();
            }
        }.runTaskLater(Main.getPlugin(Main.class), 1L);

        new BukkitRunnable(){
            @Override
            public void run(){
                qp.setJumpPadCooldown(0);
                this.cancel();
            }
        }.runTaskLater(Main.getPlugin(Main.class), 10L);
    }

    private static void lapisPad(Player player, QuakePlayer qp, World world) {
        qp.setJumpPadCooldown(1);
        Vector dash = player.getLocation().getDirection().clone();
        world.playSound(player.getLocation(), Sound.PISTON_EXTEND, 4, 0.5f);

        dash.setY(0.05f);
        dash.setX(0.0f);
        dash.setZ(0.0f);

        player.setVelocity(dash.multiply(2.5f));

        new BukkitRunnable(){
            @Override
            public void run(){
                Vector dash = player.getLocation().getDirection().clone();
                dash.setY(1.0f);
                dash.setX(0.0f);
                dash.setZ(0.0f);
                player.setVelocity(dash.multiply(2.1f));
                this.cancel();
            }
        }.runTaskLater(Main.getPlugin(Main.class), 1L);

        new BukkitRunnable(){
            @Override
            public void run(){
                qp.setJumpPadCooldown(0);
                this.cancel();
            }
        }.runTaskLater(Main.getPlugin(Main.class), 10L);
    }

    public static void walkOn(PlayerMoveEvent event, QuakePlayer qp) {
        final Player player = event.getPlayer();
        final World world = player.getWorld();
        final Location belowPlayer = player.getLocation();
        belowPlayer.setY(belowPlayer.getY() - 1);

        if (qp.getJumpPadCooldown() <= 0 && world.getBlockAt(belowPlayer).getType().equals(Material.REDSTONE_BLOCK))
            redstonePad(player, qp, world);

        if (qp.getJumpPadCooldown() <= 0 && world.getBlockAt(belowPlayer).getType().equals(Material.LAPIS_BLOCK))
            lapisPad(player, qp, world);
    }

}
