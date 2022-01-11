package blackout.commands;

import blackout.menu.ConfigMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandConfig {

	public void execute(CommandSender sender) {
		if (sender instanceof Player)
			ConfigMenu.open((Player)sender);
	}
}
