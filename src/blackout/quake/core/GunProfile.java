package blackout.quake.core;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.Sound;

public class GunProfile {
	protected String name;
	protected Material gun;
	protected Type shape;
	protected Color color;
	protected boolean superior;
	protected Sound sound;
	protected float pitch;
	protected ChatColor nameColor;
	
	public GunProfile(String name, Material gun, Type shape, Color color, boolean superior, Sound sound, float pitch, ChatColor nameColor) {
		this.name = name;
		this.gun = gun;
		this.shape = shape;
		this.color = color;
		this.superior = superior;
		this.sound = sound;
		this.pitch = pitch;
		this.nameColor = nameColor;
	}

	public String getName() {
		return name;
	}

	public GunProfile setName(String name) {
		this.name = name;
		return (this);
	}

	public Material getGun() {
		return gun;
	}

	public GunProfile setGun(Material gun) {
		this.gun = gun;
		return (this);
	}

	public Type getShape() {
		return shape;
	}

	public void setShape(Type shape) {
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isSuperior() {
		return superior;
	}

	public GunProfile setSuperior(boolean superior) {
		this.superior = superior;
		return (this);
	}

	public Sound getSound() {
		return sound;
	}

	public GunProfile setSound(Sound sound) {
		this.sound = sound;
		return (this);
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public ChatColor getNameColor() {
		return nameColor;
	}

	public void setNameColor(ChatColor nameColor) {
		this.nameColor = nameColor;
	}
}
