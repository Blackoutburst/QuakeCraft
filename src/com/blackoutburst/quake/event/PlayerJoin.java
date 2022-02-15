package com.blackoutburst.quake.event;

import com.blackoutburst.quake.core.GunProfile;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.core.ScoreboardManager;
import com.blackoutburst.quake.main.Main;
import com.blackoutburst.quake.menu.CustomMenu;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.io.File;

public class PlayerJoin {

    public void execute(Player p) {
        p.removePotionEffect(PotionEffectType.SPEED);
        p.setHealth(20);
        p.setSaturation(20);
        p.setFoodLevel(20);
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();

        GunProfile gunProfile = new GunProfile("§bWooden Case", Material.WOOD_HOE, FireworkEffect.Type.BALL, Color.AQUA, false, Sound.BLAZE_DEATH, 2, ChatColor.WHITE, EnumParticle.FIREWORKS_SPARK);
        QuakePlayer qp = new QuakePlayer(p, gunProfile);

        ScoreboardManager.init(qp);
        CustomMenu.giveItem(p);

        if (new File("./plugins/Quake/player data/"+p.getUniqueId().toString().replace("-", "")+".yml").exists()) {
            Main.players.add(qp);
            qp.readPlayerData();
        } else {
            qp.savePlayerData("gun", 11);
            qp.savePlayerData("shape", 11);
            qp.savePlayerData("color", 31);
            qp.savePlayerData("sound", 11);
            qp.savePlayerData("nameColor", 23);
            qp.savePlayerData("trail", 11);
            Main.players.add(qp);
        }

        ScoreboardManager.updatePlayers();
    }
}
