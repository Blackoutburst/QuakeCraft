package blackout.quake.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Board {
	 
	private final Scoreboard scoreboard;
	private final Objective objective;
	private Team team;
 
	public Board(Player player) {
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.objective = scoreboard.registerNewObjective(player.getName(), "dummy");
		this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.objective.setDisplayName(player.getDisplayName());
		player.setScoreboard(this.scoreboard);
		
		if (this.scoreboard.getTeam(player.getName()) == null) {
			this.scoreboard.registerNewTeam(player.getName());
		}
		team = this.scoreboard.getTeam(player.getName());
		team.setNameTagVisibility(NameTagVisibility.ALWAYS);
		team.addEntry(player.getName());
	}

	public void setTitle(String name) {
		this.objective.setDisplayName(name);
	}
 
	public void set(int row, String text) {
		if(text.length() > 32) { text = text.substring(0, 32); }
		for(String entry : this.scoreboard.getEntries()) {
			if(this.objective.getScore(entry).getScore() == row) {
				this.scoreboard.resetScores(entry);
				break;
			}
		}
		this.objective.getScore(text).setScore(row);
	}

	public void addTeam(QuakePlayer player, QuakePlayer newPlayer, NameTagVisibility tagVisible) {
		if (player.getBoard().scoreboard.getTeam(newPlayer.getPlayer().getName()) == null) {
			player.getBoard().scoreboard.registerNewTeam(newPlayer.getPlayer().getName());
		}

		team = player.getBoard().scoreboard.getTeam(newPlayer.getPlayer().getName());
		team.setPrefix("§"+newPlayer.gunProfile.nameColor.getChar());
		team.setNameTagVisibility(tagVisible);
		team.addEntry(newPlayer.getPlayer().getName());
	}
}