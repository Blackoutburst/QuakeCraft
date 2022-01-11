package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.quake.core.GameOption;

public class CommandDashStrength {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cSpecifie a value in seconds");
			return;
		}
		
		float value = 1;
		
		try {
			value = Float.parseFloat(args[0]);
		} catch (Exception e) {
			sender.sendMessage("§cThe value must be a valid number");
		}
		GameOption.DASH_STRENGTH = value;
		
		Player p = (Player) sender;
		
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas set the dash strength to §6"+value);
	}
}
