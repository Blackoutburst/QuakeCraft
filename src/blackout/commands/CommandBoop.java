package blackout.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBoop {

	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cInvlid usage !");
			return;
		}
		
		Player p = Bukkit.getPlayer(args[0]);
		
		if (p == null) {
			sender.sendMessage("§cThis player doesn't exist !");
			return;
		}
		
		if (sender instanceof Player) {
			p.sendMessage("§5From "+((Player)sender).getDisplayName()+" §dBoop!");
		} else {
			p.sendMessage("§5From "+sender.getName()+" §dBoop!");
		}
		sender.sendMessage("§5To "+p.getDisplayName()+" §dBoop!");
		
	}
}
