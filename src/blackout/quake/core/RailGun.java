package blackout.quake.core;

import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R3.EntityFireworks;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_8_R3.PlayerConnection;


public class RailGun {
	
	public static float FIRE_DELAY = 17;
	public static float DASH_DELAY = 20;
	
	protected Location location;
	protected Vector direction;
	protected QuakePlayer owner;
	
	public RailGun(Location location, Vector direction, QuakePlayer owner) {
		this.location = location;
		this.direction = direction;
		this.owner = owner;
	}
	
	public boolean insideBlock() {
		final Block b = location.getWorld().getBlockAt(location);
		
		return (!b.getType().equals(Material.AIR) && 
				!b.getType().equals(Material.TORCH) &&
				!b.getType().equals(Material.STEP) &&
				!b.getType().equals(Material.RED_ROSE) &&
				!b.getType().equals(Material.LONG_GRASS) &&
				!b.getType().equals(Material.DOUBLE_PLANT) &&
				!b.getType().equals(Material.DEAD_BUSH) &&
				!b.getType().equals(Material.RED_MUSHROOM) &&
				!b.getType().equals(Material.BROWN_MUSHROOM) &&
				!b.getType().equals(Material.VINE));
	}
	
	public void fire(Player p) {
		final RailGun b = this;
		
		p.getWorld().playSound(p.getLocation(), Sound.BLAZE_HIT, 1, 2);
		QuakePlayer.getFromPlayer(p).cooldown = FIRE_DELAY;
		
		for (int i = 500; i > 0; i--) {
			b.location.add(b.direction.normalize().multiply(0.5));
			b.trail();
			b.getNearbyPlayer();
			if (b.insideBlock()) break;
		}
	}
	
	public void getNearbyPlayer() {
		for (Entity e : location.getWorld().getEntities()) {
			if (e instanceof Player) {
				final float x = (float) (location.getX() - e.getLocation().getX());
				final float y = (float) (location.getY() - e.getLocation().getY());
				final float z = (float) (location.getZ() - e.getLocation().getZ());
				
				if (e.getUniqueId() != owner.getPlayer().getUniqueId() && ((x * x) + (y * y) + (z * z)) <= 4.0) {
					Core.teleportToRespawn((Player) e);
					owner.getPlayer().getWorld().playSound(owner.getPlayer().getLocation(), owner.getGunProfile().getSound(), 1, owner.getGunProfile().getPitch());
					Bukkit.broadcastMessage(owner.getPlayer().getDisplayName()+" §egibbed§r "+((Player)e).getDisplayName());
					this.detonate(owner);
				}
			}
		}
	}
	
	public void trail() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutWorldParticles(EnumParticle.FIREWORKS_SPARK, true, (float) location.getX(), (float) location.getY(), (float) location.getZ(), 0, 0, 0, 0, 1, null));
		}
	}
	
	public void detonate(QuakePlayer owner) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(owner.getPlayer());
		qp.score++;
		ScoreboardManager.updatePlayers();
		
		if (qp.score >= 25) {
			Core.endGame();
		}
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getWorld() != owner.getPlayer().getWorld()) continue;
			
			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			ItemStack stackFirework = new ItemStack(Material.FIREWORK);
			FireworkMeta fireworkMeta = (FireworkMeta) stackFirework.getItemMeta();
			FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(this.owner.getGunProfile().getColor()).with(this.owner.getGunProfile().getShape()).build();
			fireworkMeta.addEffect(effect);
			fireworkMeta.setPower(2);
			stackFirework.setItemMeta(fireworkMeta);
			EntityFireworks firework = new EntityFireworks(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), CraftItemStack.asNMSCopy(stackFirework));
			firework.expectedLifespan = 0;
			connection.sendPacket(new PacketPlayOutSpawnEntity(firework, 76));
			connection.sendPacket(new PacketPlayOutEntityMetadata(firework.getId(), firework.getDataWatcher(), true));
			connection.sendPacket(new PacketPlayOutEntityStatus(firework, (byte) 17));
			connection.sendPacket(new PacketPlayOutEntityDestroy(firework.getId()));
		}
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Vector getDirection() {
		return direction;
	}

	public void setDirection(Vector direction) {
		this.direction = direction;
	}

	public QuakePlayer getOwner() {
		return owner;
	}

	public void setOwner(QuakePlayer owner) {
		this.owner = owner;
	}
	
}
