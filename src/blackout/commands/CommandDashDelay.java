package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.quake.core.GameOption;

public class CommandDashDelay {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cSpecifie a value in seconds");
			return;
		}
		
		float delay = 1;
		
		try {
			delay = Float.parseFloat(args[0]);
		} catch (Exception e) {
			sender.sendMessage("§cThe value must be a valid number");
		}
		GameOption.DASH_DELAY = 20 * delay;
		
		Player p = (Player) sender;
		
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas set the dash delay to §6"+delay+"s");
	}
}
