package blackout.quake.core;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardBaseCriteria;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;
import net.minecraft.server.v1_8_R3.ScoreboardTeam;

public class ScoreboardManager {

	public static void init(Player p) {
		PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;

		Scoreboard board = new Scoreboard();
		board.registerObjective("Score", new ScoreboardBaseCriteria("dummy"));
		
		ScoreboardObjective objective = board.getObjective("Score");
		objective.setDisplayName("§6Quake");
		board.setDisplaySlot(1, objective);
		
		ScoreboardScore score = new ScoreboardScore(board, objective, "§aTest");
		score.setScore(5);

		ScoreboardTeam team = new ScoreboardTeam(board, p.getName());
		
		connection.sendPacket(new PacketPlayOutScoreboardTeam(team, 0));
		connection.sendPacket(new PacketPlayOutScoreboardObjective(objective, 0));
		connection.sendPacket(new PacketPlayOutScoreboardDisplayObjective(1, objective));
		connection.sendPacket(new PacketPlayOutScoreboardScore(score));
	}
}
