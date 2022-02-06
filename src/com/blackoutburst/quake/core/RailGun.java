package com.blackoutburst.quake.core;

import com.blackoutburst.quake.main.Main;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RailGun {
	
	private static final Random RNG = new Random();
	
	protected Location location;
	protected Vector direction;
	protected QuakePlayer owner;
	protected boolean shatter;
	protected int length;
	
	private byte trailColor = 0;
	private byte circle = 0;
	private byte head = 0;
	private int bounceLeft = GameOption.BOUNCE_COUNT + 1;
	
	private final List<Integer> headsID = new ArrayList<>();
	
	public RailGun(Location location, Vector direction, QuakePlayer owner, int length, boolean shatter) {
		this.location = location;
		this.direction = direction;
		this.owner = owner;
		this.length = length;
		this.shatter = shatter;
	}
	
	public boolean insideBlock(Location loc) {
		final Block b = loc.getWorld().getBlockAt(loc);
		
		return (!b.getType().equals(Material.AIR) && 
				!b.getType().equals(Material.TORCH) &&
				!b.getType().equals(Material.STEP) &&
				!b.getType().equals(Material.RED_ROSE) &&
				!b.getType().equals(Material.YELLOW_FLOWER) &&
				!b.getType().equals(Material.LONG_GRASS) &&
				!b.getType().equals(Material.DOUBLE_PLANT) &&
				!b.getType().equals(Material.DEAD_BUSH) &&
				!b.getType().equals(Material.RED_MUSHROOM) &&
				!b.getType().equals(Material.BROWN_MUSHROOM) &&
				!b.getType().equals(Material.VINE) &&
				!b.getType().equals(Material.WOOD_STEP) &&
				!b.getType().equals(Material.STONE_PLATE) &&
				!b.getType().equals(Material.WOOD_PLATE) &&
				!b.getType().equals(Material.GOLD_PLATE) &&
				!b.getType().equals(Material.IRON_PLATE) &&
				!b.getType().equals(Material.TRAP_DOOR) &&
				!b.getType().equals(Material.IRON_TRAPDOOR) &&
				!b.getType().equals(Material.CARPET) &&
				!b.getType().equals(Material.BARRIER) &&
				!b.getType().equals(Material.STONE_BUTTON) &&
				!b.getType().equals(Material.WOOD_BUTTON) &&
				!b.getType().equals(Material.SNOW) &&
				!b.getType().equals(Material.FIRE) &&
				!b.getType().equals(Material.SIGN) &&
				!b.getType().equals(Material.WATER) &&
				!b.getType().equals(Material.WATER_LILY) &&
				!b.getType().equals(Material.STATIONARY_WATER) &&
				!b.getType().equals(Material.LAVA) &&
				!b.getType().equals(Material.STATIONARY_LAVA) &&
				!b.getType().equals(Material.RAILS) &&
				!b.getType().equals(Material.ACTIVATOR_RAIL) &&
				!b.getType().equals(Material.DETECTOR_RAIL) &&
				!b.getType().equals(Material.POWERED_RAIL) &&
				!b.getType().equals(Material.LEVER) &&
				!b.getType().equals(Material.REDSTONE_WIRE) &&
				!b.getType().equals(Material.REDSTONE_TORCH_ON) &&
				!b.getType().equals(Material.REDSTONE_TORCH_OFF) &&
				!b.getType().equals(Material.LADDER) &&
				!b.getType().equals(Material.TRIPWIRE_HOOK) &&
				!b.getType().equals(Material.WATER_LILY) &&
				!b.getType().equals(Material.REDSTONE_COMPARATOR) &&
				!b.getType().equals(Material.REDSTONE_COMPARATOR_ON) &&
				!b.getType().equals(Material.REDSTONE_COMPARATOR_OFF) &&
				!b.getType().equals(Material.FLOWER_POT) &&
				!b.getType().equals(Material.PORTAL) &&
				!b.getType().equals(Material.ENDER_PORTAL) &&
				!b.getType().equals(Material.BANNER) &&
				!b.getType().equals(Material.WALL_BANNER) &&
				!b.getType().equals(Material.WALL_SIGN) &&
				!b.getType().equals(Material.IRON_DOOR_BLOCK) &&
				!b.getType().equals(Material.WOODEN_DOOR));
	}
	
	public void fire(QuakePlayer p) {
		trailColor = 0;

		if (!shatter)
			p.getPlayer().getWorld().playSound(p.getPlayer().getLocation(), Sound.BLAZE_HIT, 2, 2);
		p.cooldown = GameOption.FIRE_DELAY;
		
		for (int i = length; i > 0; i--) {
			final Vector dir = direction.add(new Vector(0, -0.001f * GameOption.GRAVITY, 0)).normalize().multiply(0.5);
			this.location.add(dir);
			
			this.trail();
			this.getNearbyPlayer();
			
			Location l2 = this.location.clone();
			l2.setX(l2.getX() + dir.getX());
			
			if (this.insideBlock(l2)) {
				direction.setX(-direction.getX());
				bounceLeft--;
			}
			
			l2 = this.location.clone();
			l2.setY(l2.getY() + dir.getY());
			
			if (this.insideBlock(l2)) {
				direction.setY(-direction.getY());
				bounceLeft--;
			}
			
			l2 = this.location.clone();
			l2.setZ(l2.getZ() + dir.getZ());
			
			if (this.insideBlock(l2)) {
				direction.setZ(-direction.getZ());
				bounceLeft--;
			}
			
			if (bounceLeft <= 0) {
				if (!shatter) {
					for (int j = 0; j < GameOption.SHATTER_COUNT; j++) {
						new RailGun(location.clone(), new Vector(RNG.nextFloat() * 2 - 1, RNG.nextFloat() * 2 - 1, RNG.nextFloat() * 2 - 1).normalize(), p, GameOption.SHATTER_LENGTH, true).fire(p);
					}
				}
				break;
			}
		}
		
		for (final QuakePlayer qp : Main.players) {
			final PlayerConnection connection = ((CraftPlayer) qp.player).getHandle().playerConnection;

			for (final int id : headsID)
				connection.sendPacket(new PacketPlayOutEntityDestroy(id));
		}
		headsID.clear();
	}
	
	public void getNearbyPlayer() {
		final float xloc = (float) this.location.getX();
		final float yloc = (float) this.location.getY();
		final float zloc = (float) this.location.getZ();
		final List<Entity> entities = this.location.getWorld().getEntities();

		for (final Entity e : entities) {
			if (e instanceof ArmorStand) continue;

			final float x = (float) (xloc - e.getLocation().getX());
			final float y = (float) (yloc - e.getLocation().getY());
			final float z = (float) (zloc - e.getLocation().getZ());
			final boolean dist = ((x * x) + (y * y) + (z * z)) <= 4.0f;

			if (e instanceof Player) {
				if (((Player) e).getGameMode() == GameMode.SPECTATOR) continue;

				if (e.getUniqueId() != this.owner.player.getUniqueId() && dist) {
					this.owner.killstreak++;


					Core.teleportToRespawn((Player) e);
					this.owner.player.getWorld().playSound(this.owner.player.getLocation(), this.owner.gunProfile.sound, 3, this.owner.gunProfile.pitch);

					final String headshot = location.getY() - e.getLocation().getY() > 0.9f ? "§e§lHEADSHOT" : "";
					final String killStreak = killStreak();
					for (final QuakePlayer qp : Main.players) {
						qp.player.sendMessage(this.owner.player.getDisplayName()+" §7gibbed§r "+((Player)e).getDisplayName()+" "+headshot);
						if (!killStreak.equals("none")) {
							qp.player.sendMessage(killStreak);
						}
					}

					QuakePlayer quakePlayer = QuakePlayer.getFromPlayer((Player) e);
					if (quakePlayer != null && quakePlayer.killstreak >= 5) {
						for (final QuakePlayer qp : Main.players)
							qp.player.sendMessage(quakePlayer.getPlayer().getDisplayName()+" §r§b§ogot shutdown by §r"+this.owner.getPlayer().getDisplayName());
					}
					if (quakePlayer != null) {
						quakePlayer.killstreak = 0;
					}

					this.detonate(this.owner);
				}
			} else if (e instanceof LivingEntity && ((LivingEntity) e).getHealth() > 0 && dist) {
				for (final QuakePlayer qp : Main.players)
					qp.player.sendMessage(this.owner.player.getDisplayName()+" §egibbed a§r "+e.getName()+" ??");
				((LivingEntity) e).setHealth(0);
			}
		}
	}

	private String killStreak() {
		switch(this.owner.killstreak) {
			case 5: return (this.owner.player.getDisplayName()+" §r§b§ois on a Killing Spree!");
			case 10: return (this.owner.player.getDisplayName()+" §r§b§ois on a Rampage!");
			case 15: return (this.owner.player.getDisplayName()+" §r§b§ois Dominating!");
			case 20: return (this.owner.player.getDisplayName()+" §r§b§ois Unstoppable!");
			case 25: return (this.owner.player.getDisplayName()+" §r§b§ois Godlike!");
			case 30: return (this.owner.player.getDisplayName()+" §r§b§obroke the game!");
			case 35: return (this.owner.player.getDisplayName()+" §r§b§ofixed the game!");
			case 40: return (this.owner.player.getDisplayName()+" §r§b§oentered a new dimension!");
			case 50: return (this.owner.player.getDisplayName()+" §r§b§owent into oblivion!");
			case 60: return (this.owner.player.getDisplayName()+" §r§b§oreached infinite and beyond!");
			case 70: return (this.owner.player.getDisplayName()+" §r§b§ofound the meaning of life!");
			case 75: return (this.owner.player.getDisplayName()+" §r§b§ocame back to Earth!");
			case 80: return (this.owner.player.getDisplayName()+" §r§b§obecame the first Quakecraft prophet!");
			case 85: return (this.owner.player.getDisplayName()+" §r§b§ocreated an army of Quake players!");
			case 90: return (this.owner.player.getDisplayName()+" §r§b§ois now a Quake emperor!");
			case 100: return (this.owner.player.getDisplayName()+"§r§b§o, you sure have a lot of friends.");
			case 101: return ("§r§b§oNice party you have there, §r"+this.owner.player.getDisplayName()+"§r§b§o.");
			default: return ("none");
		}
	}

	private Color getColor() {
		switch(trailColor) {
			default: return new Color(255, 0, 0);
			case 1: return new Color(255, 85, 0);
			case 2: return new Color(255, 132, 0);
			case 3: return new Color(255, 174, 0);
			case 4: return new Color(255, 255, 0);
			case 5: return new Color(174, 255, 0);
			case 6: return new Color(132, 255, 0);
			case 7: return new Color(85, 255, 0);
			case 8: return new Color(0, 255, 0);
			case 9: return new Color(0, 255, 85);
			case 10: return new Color(0, 255, 132);
			case 11: return new Color(0, 255, 174);
			case 12: return new Color(0, 255, 255);
			case 13: return new Color(0, 174, 255);
			case 14: return new Color(0, 132, 255);
			case 15: return new Color(0, 85, 255);
			case 16: return new Color(0, 0, 255);
			case 17: return new Color(85, 0, 255);
			case 18: return new Color(132, 0, 255);
			case 19: return new Color(174, 0, 255);
			case 20: return new Color(255, 0, 255);
			case 21: return new Color(255, 0, 174);
			case 22: return new Color(255, 0, 132);
			case 23: trailColor = 0; return new Color(255, 0, 85);
		}
	}

	private void createCircle(PlayerConnection connection) {
		for (int i = 9; i > 0; i--) {
			final double angle = 2 * Math.PI * i / 9;
			final double x = Math.cos(angle) * 0.3f;
			final double y = Math.sin(angle) * 0.3f;

			Vector v = rotateAroundAxisX(new Vector(x, y, 0), this.location.getPitch());
			v = rotateAroundAxisY(v, this.location.getYaw());

			final Location temp = this.location.clone().add(v);

			connection.sendPacket(new PacketPlayOutWorldParticles(EnumParticle.FLAME, true, (float) temp.getX(), (float) temp.getY(), (float) temp.getZ(), 0, 0, 0, 0, 1));
		}
	}

	private Vector rotateAroundAxisX(Vector v, double angle) {
		angle = Math.toRadians(angle);

		final double cos = Math.cos(angle);
		final double sin = Math.sin(angle);
		final double y = v.getY() * cos - v.getZ() * sin;
		final double z = v.getY() * sin + v.getZ() * cos;
		return v.setY(y).setZ(z);
	}

	private Vector rotateAroundAxisY(Vector v, double angle) {
		angle = -angle;
		angle = Math.toRadians(angle);

		final double cos = Math.cos(angle);
		final double sin = Math.sin(angle);
		final double x = v.getX() * cos + v.getZ() * sin;
		final double z = v.getX() * -sin + v.getZ() * cos;
		return v.setX(x).setZ(z);
	}

	public void trail() {
		final float xloc = (float) this.location.getX();
		final float yloc = (float) this.location.getY();
		final float zloc = (float) this.location.getZ();

		SkullLoader.hanndItem.setLocation(xloc, yloc, zloc, 0, 0);

		for (final QuakePlayer qp : Main.players) {
			final PlayerConnection connection = ((CraftPlayer) qp.player).getHandle().playerConnection;

			switch (this.owner.gunProfile.trail) {
				case REDSTONE:
					final Color c = getColor();
					final float r = c.getRed() == 0 ? Float.MIN_VALUE : (float)(c.getRed()) / 255.0f;
					final float g = (float)(c.getGreen()) / 255.0f;
					final float b = (float)(c.getBlue()) / 255.0f;

					connection.sendPacket(new PacketPlayOutWorldParticles(this.owner.gunProfile.trail, true, xloc, yloc, zloc, r, g, b, 1, 0));
				break;
				case BARRIER:
					connection.sendPacket(new PacketPlayOutWorldParticles(EnumParticle.FLAME, true, xloc, yloc, zloc, 0, 0, 0, 0, 1));

					circle++;
					if (circle > 2) {
						createCircle(connection);
						circle = 0;
					}
				break;
				case SUSPENDED_DEPTH:
					connection.sendPacket(new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, xloc, yloc, zloc, 1.0f, 0.5f, 0, 1, 0));
					head++;
					if (head > 2) {
				        connection.sendPacket(new PacketPlayOutSpawnEntity(SkullLoader.hanndItem, 2, 100));
				        connection.sendPacket(new PacketPlayOutEntityMetadata(SkullLoader.hanndItemID, SkullLoader.hanndItemWatcher, true));
				        headsID.add(SkullLoader.hanndItemID);
						head = 0;
					}
				break;
				default:
					connection.sendPacket(new PacketPlayOutWorldParticles(this.owner.gunProfile.trail, true, xloc, yloc, zloc, 0, 0, 0, 0, 1));
				break;
			}
		}
		trailColor++;
	}

	public void detonate(QuakePlayer owner) {
		ScoreboardManager.updatePlayers();
		owner.score++;

		final float xloc = (float) this.location.getX();
		final float yloc = (float) this.location.getY();
		final float zloc = (float) this.location.getZ();
		final World world = ((CraftWorld) location.getWorld()).getHandle();

		final ItemStack stackFirework = new ItemStack(Material.FIREWORK);
		final FireworkMeta fireworkMeta = (FireworkMeta) stackFirework.getItemMeta();
		final FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(this.owner.gunProfile.color).with(this.owner.gunProfile.shape).build();
		fireworkMeta.addEffect(effect);
		fireworkMeta.setPower(2);
		stackFirework.setItemMeta(fireworkMeta);
		final EntityFireworks firework = new EntityFireworks(world, xloc, yloc, zloc, CraftItemStack.asNMSCopy(stackFirework));
		firework.expectedLifespan = 0;

		for (final QuakePlayer qp : Main.players) {
			final PlayerConnection connection = ((CraftPlayer) qp.player).getHandle().playerConnection;

			connection.sendPacket(new PacketPlayOutSpawnEntity(firework, 76));
			connection.sendPacket(new PacketPlayOutEntityMetadata(firework.getId(), firework.getDataWatcher(), true));
			connection.sendPacket(new PacketPlayOutEntityStatus(firework, (byte) 17));
			connection.sendPacket(new PacketPlayOutEntityDestroy(firework.getId()));
		}
	}
}
