package com.blackoutburst.quake.event;

import com.blackoutburst.quake.core.Void;
import com.blackoutburst.quake.core.*;
import com.blackoutburst.quake.main.Main;
import com.blackoutburst.quake.menu.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public class EventListener implements Listener {

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
        final QuakePlayer qp = QuakePlayer.getFromPlayer(event.getPlayer());
        Void.fallInVoid(event, qp);

        if (qp == null) return;

        Launchpad.walkOn(event, qp);
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
        event.setCancelled(Main.gameRunning);
        event.setCancelled(Main.disableDamageEverywhere);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(Main.gameRunning);
    }
}
