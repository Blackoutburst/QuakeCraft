package com.blackoutburst.quake.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CommandSpawnWand {

    public void execute(CommandSender sender) {
        if (sender instanceof Player) {
            ItemStack stack = new ItemStack(Material.BLAZE_ROD);
            ItemMeta meta = stack.getItemMeta();
            List<String> lore = new ArrayList<>();

            meta.setDisplayName("§6Spawn wand");
            lore.add("§eRight click §aa block to §bcreate §aa spawn or §cdelete §aa spawn");
            lore.add("§eLeft click §&a spawn to edit the spawn §borientation");
            meta.setLore(lore);
            stack.setItemMeta(meta);

            ((Player)sender).getInventory().addItem(stack);
        }
    }
}
