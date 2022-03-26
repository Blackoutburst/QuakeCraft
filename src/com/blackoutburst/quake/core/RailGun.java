package com.blackoutburst.quake.core;

import com.blackoutburst.quake.main.Main;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutEntityStatus;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.entity.projectile.EntityFireworks;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
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
	protected byte kill;

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
		this.kill = 0;
	}
	
	public boolean insideBlock(Location loc) {
		final Block b = loc.getWorld().getBlockAt(loc);
		
		return (!b.getType().equals(Material.AIR) && 
				!b.getType().equals(Material.TORCH) &&
				!b.getType().equals(Material.LEGACY_STEP) &&
				!b.getType().equals(Material.LEGACY_RED_ROSE) &&
				!b.getType().equals(Material.LEGACY_YELLOW_FLOWER) &&
				!b.getType().equals(Material.LEGACY_LONG_GRASS) &&
				!b.getType().equals(Material.LEGACY_DOUBLE_PLANT) &&
				!b.getType().equals(Material.DEAD_BUSH) &&
				!b.getType().equals(Material.RED_MUSHROOM) &&
				!b.getType().equals(Material.BROWN_MUSHROOM) &&
				!b.getType().equals(Material.VINE) &&
				!b.getType().equals(Material.LEGACY_WOOD_STEP) &&
				!b.getType().equals(Material.LEGACY_STONE_PLATE) &&
				!b.getType().equals(Material.LEGACY_WOOD_PLATE) &&
				!b.getType().equals(Material.LEGACY_GOLD_PLATE) &&
				!b.getType().equals(Material.LEGACY_IRON_PLATE) &&
				!b.getType().equals(Material.LEGACY_TRAP_DOOR) &&
				!b.getType().equals(Material.IRON_TRAPDOOR) &&
				!b.getType().equals(Material.LEGACY_CARPET) &&
				!b.getType().equals(Material.BARRIER) &&
				!b.getType().equals(Material.STONE_BUTTON) &&
				!b.getType().equals(Material.LEGACY_WOOD_BUTTON) &&
				!b.getType().equals(Material.SNOW) &&
				!b.getType().equals(Material.FIRE) &&
				!b.getType().equals(Material.LEGACY_SIGN) &&
				!b.getType().equals(Material.WATER) &&
				!b.getType().equals(Material.LEGACY_WATER_LILY) &&
				!b.getType().equals(Material.LEGACY_STATIONARY_WATER) &&
				!b.getType().equals(Material.LAVA) &&
				!b.getType().equals(Material.LEGACY_STATIONARY_LAVA) &&
				!b.getType().equals(Material.LEGACY_RAILS) &&
				!b.getType().equals(Material.ACTIVATOR_RAIL) &&
				!b.getType().equals(Material.DETECTOR_RAIL) &&
				!b.getType().equals(Material.POWERED_RAIL) &&
				!b.getType().equals(Material.LEVER) &&
				!b.getType().equals(Material.REDSTONE_WIRE) &&
				!b.getType().equals(Material.LEGACY_REDSTONE_TORCH_ON) &&
				!b.getType().equals(Material.LEGACY_REDSTONE_TORCH_OFF) &&
				!b.getType().equals(Material.LADDER) &&
				!b.getType().equals(Material.TRIPWIRE_HOOK) &&
				!b.getType().equals(Material.LEGACY_REDSTONE_COMPARATOR) &&
				!b.getType().equals(Material.LEGACY_REDSTONE_COMPARATOR_ON) &&
				!b.getType().equals(Material.LEGACY_REDSTONE_COMPARATOR_OFF) &&
				!b.getType().equals(Material.FLOWER_POT) &&
				!b.getType().equals(Material.LEGACY_PORTAL) &&
				!b.getType().equals(Material.LEGACY_ENDER_PORTAL) &&
				!b.getType().equals(Material.LEGACY_BANNER) &&
				!b.getType().equals(Material.LEGACY_WALL_BANNER) &&
				!b.getType().equals(Material.LEGACY_WALL_SIGN) &&
				!b.getType().equals(Material.LEGACY_IRON_DOOR_BLOCK) &&
				!b.getType().equals(Material.LEGACY_WOODEN_DOOR));
	}
	
	public void fire(QuakePlayer p) {
		trailColor = 0;

		if (!shatter)
			p.getPlayer().getWorld().playSound(p.getPlayer().getLocation(), Sound.ENTITY_BLAZE_HURT, 2, 2);
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

		for (final QuakePlayer qp : Main.players)
			if (kill > 1)
				qp.player.sendMessage(Utils.multiKill(kill));

		new BukkitRunnable() {
			public void run() {
				for (final QuakePlayer qp : Main.players) {
					final PlayerConnection connection = ((CraftPlayer) qp.player).getHandle().b;

					for (final int id : headsID)
						connection.sendPacket(new PacketPlayOutEntityDestroy(id));
				}
				headsID.clear();
			}
		}.runTaskLaterAsynchronously(Main.getPlugin(Main.class), 7L);
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
					final String killStreak = Utils.killStreak(this.owner);
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

					this.kill++;
					this.detonate(this.owner);
				}
			} else if (e instanceof LivingEntity && ((LivingEntity) e).getHealth() > 0 && dist) {
				for (final QuakePlayer qp : Main.players)
					qp.player.sendMessage(this.owner.player.getDisplayName()+" §egibbed a§r "+e.getName()+" ??");
				((LivingEntity) e).setHealth(0);
			}
		}
	}

	public void trail() {
		final double xloc = this.location.getX();
		final double yloc = this.location.getY();
		final double zloc = this.location.getZ();
		final EntityItem hanndItem = new EntityItem(SkullLoader.world, xloc, yloc-0.5f, zloc, SkullLoader.hanndNMS);

		switch (this.owner.gunProfile.trail) {
			case REDSTONE:
				final Color c = Utils.getColor(trailColor);
				final float r = c.getRed() == 0 ? Float.MIN_VALUE : (float)(c.getRed()) / 255.0f;
				final float g = (float)(c.getGreen()) / 255.0f;
				final float b = (float)(c.getBlue()) / 255.0f;

				for (final QuakePlayer qp : Main.players)
					qp.getPlayer().getWorld().spawnParticle(this.owner.gunProfile.trail, xloc, yloc, zloc, 1, r, g, b);
				break;
			case BARRIER:
				for (final QuakePlayer qp : Main.players) {
					qp.getPlayer().getWorld().spawnParticle(Particle.FLAME, xloc, yloc, zloc, 1);
					if (circle > 2) {
						Utils.createCircle(location);
					}
				}
				break;
			case SUSPENDED_DEPTH:
				for (final QuakePlayer qp : Main.players) {
					final PlayerConnection connection = ((CraftPlayer) qp.player).getHandle().b;
					qp.getPlayer().getWorld().spawnParticle(Particle.REDSTONE, xloc, yloc, zloc, 1, 1.0f, 0.5f, 0, 1);
					if (head > 2) {
						connection.sendPacket(new PacketPlayOutSpawnEntity(hanndItem));
						connection.sendPacket(new PacketPlayOutEntityMetadata(hanndItem.getId(), hanndItem.getDataWatcher(), true));
						headsID.add(hanndItem.getId());
					}
				}
				break;
			default:
				for (final QuakePlayer qp : Main.players)
					qp.getPlayer().getWorld().spawnParticle(this.owner.gunProfile.trail, xloc, yloc, zloc, 1);
				break;
		}
		if (circle > 2) circle = 0;
		if (head > 2) head = 0;
		trailColor++;
		if (trailColor > 23) trailColor = 0;
		circle++;
		head++;
	}

	public void detonate(QuakePlayer owner) {
		ScoreboardManager.updatePlayers();
		owner.score++;

		final float xloc = (float) this.location.getX();
		final float yloc = (float) this.location.getY();
		final float zloc = (float) this.location.getZ();
		final WorldServer world = ((CraftWorld) location.getWorld()).getHandle();

		final ItemStack stackFirework = new ItemStack(Material.FIREWORK_ROCKET);
		final FireworkMeta fireworkMeta = (FireworkMeta) stackFirework.getItemMeta();
		final FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(this.owner.gunProfile.color).with(this.owner.gunProfile.shape).build();
		fireworkMeta.addEffect(effect);
		fireworkMeta.setPower(2);
		stackFirework.setItemMeta(fireworkMeta);
		final EntityFireworks firework = new EntityFireworks(world, xloc, yloc, zloc, CraftItemStack.asNMSCopy(stackFirework));
		firework.f = 0;

		for (final QuakePlayer qp : Main.players) {
			final PlayerConnection connection = ((CraftPlayer) qp.player).getHandle().b;

			connection.sendPacket(new PacketPlayOutSpawnEntity(firework, 76));
			connection.sendPacket(new PacketPlayOutEntityMetadata(firework.getId(), firework.getDataWatcher(), true));
			connection.sendPacket(new PacketPlayOutEntityStatus(firework, (byte) 17));
			connection.sendPacket(new PacketPlayOutEntityDestroy(firework.getId()));
		}
	}
}
