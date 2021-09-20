package blackout.quake.core;

import org.bukkit.Color;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;

public class GunProfile {
	protected Material gun;
	protected Type shape;
	protected Color color;
	
	public GunProfile(Material gun, Type shape, Color color) {
		this.gun = gun;
		this.shape = shape;
		this.color = color;
	}

	public Material getGun() {
		return gun;
	}

	public void setGun(Material gun) {
		this.gun = gun;
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
	
}
