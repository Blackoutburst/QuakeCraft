package blackout.quake.core;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;


public class RailGun {
	
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
				!b.getType().equals(Material.ENDER_PORTAL));
	}
	
	public void fire(QuakePlayer p) {
		final RailGun b = this;

		p.getPlayer().getWorld().playSound(p.getPlayer().getLocation(), Sound.BLAZE_HIT, 2, 2);
		p.cooldown = GameOption.FIRE_DELAY;
		
		for (int i = 500; i > 0; i--) {
			b.location.add(b.direction.normalize().multiply(0.5));
			b.trail();
			b.getNearbyPlayer();
			if (b.insideBlock()) break;
		}
	}
	
	public void getNearbyPlayer() {
		for (Entity e : location.getWorld().getEntities()) {
			final float x = (float) (location.getX() - e.getLocation().getX());
			final float y = (float) (location.getY() - e.getLocation().getY());
			final float z = (float) (location.getZ() - e.getLocation().getZ());
			final boolean dist = ((x * x) + (y * y) + (z * z)) <= 4.0;
			if (e instanceof Player) {
				if (((Player) e).getGameMode() == GameMode.SPECTATOR) continue;
				if (e.getUniqueId() != owner.getPlayer().getUniqueId() && dist) {
					Core.teleportToRespawn((Player) e);
					owner.getPlayer().getWorld().playSound(owner.getPlayer().getLocation(), owner.getGunProfile().getSound(), 3, owner.getGunProfile().getPitch());
					Bukkit.broadcastMessage(owner.getPlayer().getDisplayName()+" §egibbed§r "+((Player)e).getDisplayName());
					this.detonate(owner);
				}
			} else if (e instanceof LivingEntity && dist && ((LivingEntity) e).getHealth() > 0) {
				Bukkit.broadcastMessage(owner.getPlayer().getDisplayName()+" §egibbed a§r "+e.getName()+" ??");
				((LivingEntity) e).setHealth(0);
			}
		}
	}
	
	public void trail() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutWorldParticles(owner.gunProfile.trail, true, (float) location.getX(), (float) location.getY(), (float) location.getZ(), 0, 0, 0, 0, 1, 0));
		}
	}
	
	public void detonate(QuakePlayer owner) {
		owner.score++;
		ScoreboardManager.updatePlayers();
		
		if (owner.score >= GameOption.MAX_SCORE) {
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
}
