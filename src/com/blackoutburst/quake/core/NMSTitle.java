package com.blackoutburst.quake.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSTitle {

    private static Class<?> getClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);

            playerConnection.getClass().getMethod("sendPacket", getClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        try {
            Class<?> playOutTitle = getClass("PacketPlayOutTitle");
            Class<?> enumTitleAction = getClass("PacketPlayOutTitle").getDeclaredClasses()[0];
            Class<?> chatBaseComponent = getClass("IChatBaseComponent");
            Class<?> chatSerializer = getClass("IChatBaseComponent").getDeclaredClasses()[0];

            Constructor<?> delayConstructor = playOutTitle.getConstructor(int.class, int.class, int.class);
            Constructor<?> titleConstructor = playOutTitle.getConstructor(enumTitleAction, chatBaseComponent);

            Object titleString = chatSerializer.getMethod("a", String.class).invoke(null, "{\"text\": \"" + title + "\"}");
            Object subtitleString = chatSerializer.getMethod("a", String.class).invoke(null, "{\"text\": \"" + subtitle + "\"}");

            Object delayPacket = delayConstructor.newInstance(fadeIn, stay, fadeOut);
            Object titlePacket = titleConstructor.newInstance(enumTitleAction.getField("TITLE").get(null), titleString);
            Object subtitlePacket = titleConstructor.newInstance(enumTitleAction.getField("SUBTITLE").get(null), subtitleString);

            sendPacket(player, delayPacket);
            sendPacket(player, titlePacket);
            sendPacket(player, subtitlePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}