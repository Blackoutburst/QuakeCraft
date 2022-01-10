package blackout.commands;

import blackout.quake.core.RailGun;
import blackout.quake.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMaxScore {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cSpecifie a score");
			return;
		}
		
		int score = 25;

		try {
			score = Integer.parseInt(args[0]);
		} catch (Exception e) {
			sender.sendMessage("§cThe value must be a valid number");
		}
		Main.maxScore = score;
		
		Player p = (Player) sender;
		
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas set the max score to §6"+score+"s");
	}
}
