package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.quake.core.GameOption;

public class CommandToggleBlindness {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		GameOption.BLINDNESS = GameOption.BLINDNESS ? false : true;
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas "+(GameOption.BLINDNESS ? "§aEnabled" : "§cDisabled")+" §6Blindness");
	}
}
