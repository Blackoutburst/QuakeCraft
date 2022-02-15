package com.blackoutburst.quake.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandManager {

    public static void execute(CommandSender sender, Command command, String[] args) {
        switch(command.getName().toLowerCase()) {
            case "maxscore": new CommandMaxScore().execute(sender, args); break;
            case "bouncecount": new CommandBounceCount().execute(sender, args); break;
            case "raylength": new CommandRayLength().execute(sender, args); break;
            case "shattercount": new CommandShatterCount().execute(sender, args); break;
            case "shatterlength": new CommandShatterLength().execute(sender, args); break;
            case "listmap": new CommandListMap().execute(sender); break;
            case "start": new CommandStart().execute(sender, args); break;
            case "end": new CommandEnd().execute(sender); break;
            case "scan": new CommandScan().execute(sender, args); break;
            case "clean": new CommandClean().execute(sender, args); break;
            case "showspawn": new CommandShowSpawn().execute(sender, args); break;
            case "loadspawn": new CommandLoadSpawn().execute(sender, args); break;
            case "triggerspeed": new CommandTriggerSpeed().execute(sender, args); break;
            case "dashdelay": new CommandDashDelay().execute(sender, args); break;
            case "config": new CommandConfig().execute(sender); break;
            case "showconfig": new CommandShowConfig().execute(sender); break;
            case "resetconfig": new CommandResetConfig().execute(sender); break;
            case "togglejump": new CommandToggleJump().execute(sender); break;
            case "togglewalk": new CommandToggleWalk().execute(sender); break;
            case "toggledash": new CommandToggleDash().execute(sender); break;
            case "toggleverticaldash": new CommandToggleVerticalDash().execute(sender); break;
            case "togglenametag": new CommandToggleNametag().execute(sender); break;
            case "toggleinvisibility": new CommandToggleInvisibility().execute(sender); break;
            case "toggleblindness": new CommandToggleBlindness().execute(sender); break;
            case "playerslow": new CommandPlayerSlow().execute(sender, args); break;
            case "playerspeed": new CommandPlayerSpeed().execute(sender, args); break;
            case "playerjump": new CommandPlayerJump().execute(sender, args); break;
            case "dashstrength": new CommandDashStrength().execute(sender, args); break;
            case "play": new CommandPlay().execute(sender); break;
            case "joinqueue": new CommandJoinQueue().execute(sender); break;
            case "leavequeue": new CommandLeaveQueue().execute(sender); break;
            case "queue": new CommandQueue().execute(sender); break;
            case "gravity": new CommandGravity().execute(sender, args); break;
            case "spawnwand": new CommandSpawnWand().execute(sender); break;
            case "setquakespawn": new CommandSetQuakeSpawn().execute(sender); break;
            default: break;
        }
    }

}
