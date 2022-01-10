package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.quake.core.RailGun;

public class CommandTriggerSpeed {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cSpecifie a value in seconds");
			return;
		}
		
		float delay = 0.85f;
		
		try {
			delay = Float.parseFloat(args[0]);
		} catch (Exception e) {
			sender.sendMessage("§cThe value must be a valid number");
		}
		RailGun.FIRE_DELAY = 20 * delay;
		
		Player p = (Player) sender;
		
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas set the trigger speed to §6"+delay+"s");
	}
}
