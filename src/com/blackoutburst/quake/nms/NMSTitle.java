package com.blackoutburst.quake.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSTitle {

    /**
     * Display a title for a player
     *
     * @param player the player that will receive the packet
     * @param title the title text
     * @param subtitle the subtitle text
     * @param fadeIn the fade in delay in ticks
     * @param stay the number of ticks the title will stay
     * @param fadeOut the fade out delay in ticks
     */
    public static void send(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        try {
            final Class<?> playOutTitle = NMS.getClass("PacketPlayOutTitle");
            final Class<?> enumTitleAction = NMS.getClass("PacketPlayOutTitle").getDeclaredClasses()[0];
            final Class<?> chatBaseComponent = NMS.getClass("IChatBaseComponent");
            final Class<?> chatSerializer = NMS.getClass("IChatBaseComponent").getDeclaredClasses()[0];

            final Constructor<?> delayConstructor = playOutTitle.getConstructor(int.class, int.class, int.class);
            final Constructor<?> titleConstructor = playOutTitle.getConstructor(enumTitleAction, chatBaseComponent);

            final Object titleString = chatSerializer.getMethod("a", String.class).invoke(null, "{\"text\": \"" + title + "\"}");
            final Object subtitleString = chatSerializer.getMethod("a", String.class).invoke(null, "{\"text\": \"" + subtitle + "\"}");

            final Object delayPacket = delayConstructor.newInstance(fadeIn, stay, fadeOut);
            final Object titlePacket = titleConstructor.newInstance(enumTitleAction.getField("TITLE").get(null), titleString);
            final Object subtitlePacket = titleConstructor.newInstance(enumTitleAction.getField("SUBTITLE").get(null), subtitleString);

            NMS.sendPacket(player, delayPacket);
            NMS.sendPacket(player, titlePacket);
            NMS.sendPacket(player, subtitlePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
