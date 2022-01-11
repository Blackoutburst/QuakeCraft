package blackout.commands;

import org.bukkit.command.CommandSender;

import blackout.quake.core.GameOption;

public class CommandShowConfig {

	public void execute(CommandSender sender) {
		sender.sendMessage("�b-------------------");
		sender.sendMessage("�6Trigger: �e"+GameOption.FIRE_DELAY/20+"s");
		sender.sendMessage("�6Dash: �e"+GameOption.DASH_DELAY/20+"s");
		sender.sendMessage("�6Max score: �e"+GameOption.MAX_SCORE);
		sender.sendMessage("");
		sender.sendMessage("�6Speed: �e"+GameOption.PLAYER_SPEED);
		sender.sendMessage("�6Jump: �e"+GameOption.JUMP_BOOST);
		sender.sendMessage("�6Slow: �e"+GameOption.SLOWNESS);
		sender.sendMessage("");
		sender.sendMessage("�6Blindness: "+(GameOption.BLINDNESS ? "�aYes" : "�cNo"));
		sender.sendMessage("�6Invisibility: "+(GameOption.INVISIBILITY ? "�aYes" : "�cNo"));
		sender.sendMessage("�6NameTag: "+(GameOption.NAMETAG ? "�aYes" : "�cNo"));
		sender.sendMessage("");
		sender.sendMessage("�6Dash: "+(GameOption.DASH ? "�aYes" : "�cNo"));
		sender.sendMessage("�6Walk: "+(GameOption.WALK ? "�aYes" : "�cNo"));
		sender.sendMessage("�6Jump: "+(GameOption.JUMP ? "�aYes" : "�cNo"));
		sender.sendMessage("�b-------------------");
	}
}