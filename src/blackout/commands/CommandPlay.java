package blackout.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blackout.menu.MapMenu;

public class CommandPlay {

	public void execute(CommandSender sender) {
		if (sender instanceof Player)
			MapMenu.open((Player)sender);
	}
}
