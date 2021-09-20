package blackout.quake.core;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import blackout.quake.main.Main;
import net.minecraft.server.v1_8_R3.EntityFireworks;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_8_R3.PlayerConnection;


public class RailGun {
	
	public static final float FIRE_DELAY = 17;
	public static final float DASH_DELAY = 20;
	
	protected Location location;
	protected Vector direction;
	protected Player owner;
	protected short lifetime;
	protected boolean alive;
	
	public RailGun(Location location, Vector direction, Player owner) {
		this.location = location;
		this.direction = direction;
		this.owner = owner;
		this.lifetime = 500;
		this.alive = true;
	}
	
	public boolean insideBlock() {
		return (!location.getWorld().getBlockAt(location).getType().equals(Material.AIR));
	}
	
	public void fire(Player p) {
		final RailGun b = this;
		
		p.getWorld().playSound(p.getLocation(), Sound.BLAZE_HIT, 1, 1);
		QuakePlayer.getFromPlayer(p).cooldown = FIRE_DELAY;
		new BukkitRunnable(){
			@Override
			public void run(){
				for (int i = 0; i < 50; i++) {
					b.setLocation(b.getLocation().add(b.getDirection().normalize().multiply(0.25)));
					b.setLifetime((short) (b.getLifetime() - 1));
					
					b.getNearbyPlayer();
					b.trail();
					if (!b.isAlive() || b.insideBlock()) {
						this.cancel();
						break;
					}
				}
			}
		}.runTaskTimer(Main.getPlugin(Main.class), 0L, 1L);
	}
	
	public void getNearbyPlayer() {
		for (Entity e : location.getWorld().getEntities()) {
			if (e instanceof Player) {
				double distance = Math.sqrt(
						Math.pow((location.getX() - e.getLocation().getX()), 2) +
						Math.pow((location.getY() - e.getLocation().getY()), 2) +
						Math.pow((location.getZ() - e.getLocation().getZ()), 2));
				
				if (e.getUniqueId() != owner.getUniqueId() && distance <= 2) {
					Core.teleportToRespawn((Player) e);
					this.detonate();
					owner.getWorld().playSound(owner.getLocation(), Sound.BLAZE_DEATH, 1, 2);
					Bukkit.broadcastMessage(owner.getName()+" §egibbed§r "+e.getName());
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
	
	public void detonate() {
		QuakePlayer qp = QuakePlayer.getFromPlayer(owner);
		qp.score++;
		ScoreboardManager.updatePlayers();
		
		if (qp.score >= 25) {
			Core.endGame();
		}
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			ItemStack stackFirework = new ItemStack(Material.FIREWORK);
			FireworkMeta fireworkMeta = (FireworkMeta) stackFirework.getItemMeta();
			FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(Color.AQUA).with(Type.BURST).build();
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

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public short getLifetime() {
		return lifetime;
	}

	public void setLifetime(short lifetime) {
		this.lifetime = lifetime;
		if (this.lifetime <= 0) {
			this.alive = false;
		}
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
}
