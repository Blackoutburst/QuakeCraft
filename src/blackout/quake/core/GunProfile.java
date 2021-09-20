package blackout.quake.core;

import org.bukkit.Color;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;

public class GunProfile {
	protected String name;
	protected Material gun;
	protected Type shape;
	protected Color color;
	protected boolean superior;
	
	public GunProfile(String name, Material gun, Type shape, Color color, boolean superior) {
		this.name = name;
		this.gun = gun;
		this.shape = shape;
		this.color = color;
		this.superior = superior;
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

	public GunProfile setShape(Type shape) {
		this.shape = shape;
		return (this);
	}

	public Color getColor() {
		return color;
	}

	public GunProfile setColor(Color color) {
		this.color = color;
		return (this);
	}

	public boolean isSuperior() {
		return superior;
	}

	public GunProfile setSuperior(boolean superior) {
		this.superior = superior;
		return (this);
	}
	
}
