package blackout.commands;

import blackout.quake.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn {

	public void execute(CommandSender sender) {
		if (sender instanceof Player)
			((Player) sender).teleport(Main.spawn);
	}
}
