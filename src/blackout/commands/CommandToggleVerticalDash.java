package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.quake.core.GameOption;

public class CommandToggleVerticalDash {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		run(p);
	}
	
	public static void run(Player p) {
		GameOption.VERTICAL_DASH = GameOption.VERTICAL_DASH ? false : true;
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas "+(GameOption.VERTICAL_DASH ? "§aEnabled" : "§cDisabled")+" §6Vertical Dash");
	}
}
