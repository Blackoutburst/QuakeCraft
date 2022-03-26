package com.blackoutburst.quake.core;

import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;

public class GunProfile {
	protected String name;
	protected Material gun;
	protected Type shape;
	protected Color color;
	protected boolean superior;
	protected Sound sound;
	protected float pitch;
	protected ChatColor nameColor;
	protected Particle trail;
	
	public GunProfile(String name, Material gun, Type shape, Color color, boolean superior, Sound sound, float pitch, ChatColor nameColor, Particle trail) {
		this.name = name;
		this.gun = gun;
		this.shape = shape;
		this.color = color;
		this.superior = superior;
		this.sound = sound;
		this.pitch = pitch;
		this.nameColor = nameColor;
		this.trail = trail;
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

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public ChatColor getNameColor() {
		return nameColor;
	}

	public void setNameColor(ChatColor nameColor) {
		this.nameColor = nameColor;
	}

	public Particle getTrail() {
		return trail;
	}

	public void setTrail(Particle trail) {
		this.trail = trail;
	}

}
