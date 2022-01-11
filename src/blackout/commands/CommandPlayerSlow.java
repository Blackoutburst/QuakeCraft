package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.quake.core.GameOption;

public class CommandPlayerSlow {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cSpecifie a value");
			return;
		}
		
		int value = 0;

		try {
			value = Integer.parseInt(args[0]);
		} catch (Exception e) {
			sender.sendMessage("§cThe value must be a valid number");
		}
		GameOption.SLOWNESS = value;
		
		Player p = (Player) sender;
		
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas set the player slowness to §6"+value);
	}
}
