package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.quake.core.GameOption;

public class CommandToggleWalk {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		GameOption.WALK = GameOption.WALK ? false : true;
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas "+(GameOption.WALK ? "§aEnabled" : "§cDisabled")+" §6Walk");
	}
}
