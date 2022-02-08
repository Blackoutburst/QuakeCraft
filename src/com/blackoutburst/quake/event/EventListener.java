package com.blackoutburst.quake.event;

import com.blackoutburst.quake.core.Void;
import com.blackoutburst.quake.core.*;
import com.blackoutburst.quake.main.Main;
import com.blackoutburst.quake.menu.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class EventListener  implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        SpawnWand.leftClick(event);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new PlayerJoin().execute(event.getPlayer());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        final QuakePlayer qp = QuakePlayer.getFromPlayer(event.getPlayer());
        final World world = player.getWorld();
        final Location belowPlayer = player.getLocation();
        belowPlayer.setY(belowPlayer.getY() - 1);

        Void.fallInVoid(event, qp);
        if (qp == null) return;

        if (qp.getJumpPadCooldown() <= 0 && world.getBlockAt(belowPlayer).getType().equals(Material.REDSTONE_BLOCK)) {
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

        if (qp.getJumpPadCooldown() <= 0 && world.getBlockAt(belowPlayer).getType().equals(Material.LAPIS_BLOCK)) {
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
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Main.players.remove(QuakePlayer.getFromPlayer(event.getPlayer()));
        ScoreboardManager.updatePlayers();

        if (Main.players.size() == 0) {
            Main.gameRunning = false;
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        event.setCancelled(Main.gameRunning);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setMessage(event.getMessage().replace("&", "§"));
        event.setFormat("%s: %s");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        SpawnWand.rightClick(event);

        QuakePlayer qp = QuakePlayer.getFromPlayer(event.getPlayer());
        if (qp == null) return;

        if (event.getPlayer().getItemInHand().getType().equals(Material.NETHER_STAR)) {
            CustomMenu.open(event.getPlayer());
        }

        if (!Main.gameRunning) return;

        Dash.dash(qp, event);

        if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK) ||
                event.getAction().equals(Action.RIGHT_CLICK_AIR)) &&
                Core.clickHoe(event.getPlayer().getItemInHand().getType()) &&
                qp.getCooldown() <= 0) {

            Location loc = event.getPlayer().getLocation().clone();
            loc.setY(loc.getY() + event.getPlayer().getEyeHeight());

            new RailGun(loc, event.getPlayer().getLocation().getDirection().clone(), qp, GameOption.RAY_LENGTH, false).fire(qp);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        switch(event.getInventory().getName()) {
            case "Gun customisation Menu":
                CustomMenu.click(event.getSlot(), (Player) event.getWhoClicked());
                event.setCancelled(true);
                break;
            case "Gun Menu":
                GunMenu.getValue(event.getSlot(), (Player) event.getWhoClicked(), true);
                event.setCancelled(true);
                break;
            case "Shape Menu":
                ShapeMenu.getValue(event.getSlot(), (Player) event.getWhoClicked(), true);
                event.setCancelled(true);
                break;
            case "Color Menu":
                ColorMenu.getValue(event.getSlot(), (Player) event.getWhoClicked(), true);
                event.setCancelled(true);
                break;
            case "Kill Sounds":
                if (event.isLeftClick())
                    SoundsMenu.getValue(event.getSlot(), (Player) event.getWhoClicked(), true);
                if (event.isRightClick())
                    SoundsMenu.preview(event.getSlot(), (Player) event.getWhoClicked());
                event.setCancelled(true);
                break;
            case "Name Color Menu":
                NameColorMenu.getValue(event.getSlot(), (Player) event.getWhoClicked(), true);
                event.setCancelled(true);
                break;
            case "Game Configuration":
                ConfigMenu.getValue(event.getSlot(), (Player) event.getWhoClicked(), event.getClickedInventory());
                event.setCancelled(true);
                break;
            case "Map Selector (1/2)": case "Map Selector (2/2)":
                if (event.getCurrentItem() != null && !event.getCurrentItem().getType().equals(Material.ARROW)) {
                    MapMenu.getValue(event.getCurrentItem().getItemMeta().getDisplayName());
                    event.setCancelled(true);
                } else if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.ARROW)) {
                    if (event.getSlot() == 53) {
                        MapMenu.open2((Player) event.getWhoClicked());
                        event.setCancelled(true);
                    } else if (event.getSlot() == 45) {
                        MapMenu.open((Player) event.getWhoClicked());
                        event.setCancelled(true);
                    }
                }
                break;
            case "Beam Menu":
                BeamMenu.getValue(event.getSlot(), (Player) event.getWhoClicked(), true);
                event.setCancelled(true);
                break;
            default: break;
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        if (event.isSneaking())
            event.setCancelled(Main.gameRunning);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
