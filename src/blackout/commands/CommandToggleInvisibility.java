package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.quake.core.GameOption;

public class CommandToggleInvisibility {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		GameOption.INVISIBILITY = GameOption.INVISIBILITY ? false : true;
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas "+(GameOption.INVISIBILITY ? "§aEnabled" : "§cDisabled")+" §6Invisibility");
	}
}
