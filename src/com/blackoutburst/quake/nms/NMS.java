package com.blackoutburst.quake.nms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class NMS {

    /**
     * Retrieve a net.minecraft.server (NMS) class using its name
     *
     * @param name the name of the class
     * @return the class with the corresponding name
     */
    public static Class<?> getClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Send a packet to a player
     *
     * @param player the player receiving the packet
     * @param packet the packet you wish to send
     */
    public static void sendPacket(Player player, Object packet) {
        try {
            final Object handle = player.getClass().getMethod("getHandle").invoke(player);
            final Object playerConnection = handle.getClass().getField("playerConnection").get(handle);

            playerConnection.getClass().getMethod("sendPacket", getClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Allow you to manually set class fields
     *
     * @param edit the object containing the field
     * @param fieldName the name of the field
     * @param value the value you want to put in the field
     */
    public static void setField(Object edit, String fieldName, Object value) {
        try {
            final Field field = edit.getClass().getDeclaredField(fieldName);

            field.setAccessible(true);
            field.set(edit, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
