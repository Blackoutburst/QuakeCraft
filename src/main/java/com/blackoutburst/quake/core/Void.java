package com.blackoutburst.quake.core;

import com.blackoutburst.quake.main.Main;
import org.bukkit.event.player.PlayerMoveEvent;

public class Void {

    public static void fallInVoid(PlayerMoveEvent event, QuakePlayer qp) {
        if (event.getPlayer().getLocation().getY() < -10) {
            if (Main.voidBackToLobbyOutsideGame) {
                event.getPlayer().teleport(Main.spawn);
            }
            if (Main.gameRunning && qp != null) {
                Core.teleportToRespawn(event.getPlayer());
            }
        }
    }
}
