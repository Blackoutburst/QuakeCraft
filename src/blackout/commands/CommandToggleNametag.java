package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.quake.core.GameOption;

public class CommandToggleNametag {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		GameOption.NAMETAG = GameOption.NAMETAG ? false : true;
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas "+(GameOption.NAMETAG ? "§aEnabled" : "§cDisabled")+" §6Nametag");
	}
}
