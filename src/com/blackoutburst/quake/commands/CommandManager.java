package com.blackoutburst.quake.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandManager {

    public static void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!label.startsWith("quakecraft:")) label = "quakecraft:"+label;

        switch(label.toLowerCase()) {
            case "quakecraft:maxscore": new CommandMaxScore().execute(sender, args); break;
            case "quakecraft:bouncecount": new CommandBounceCount().execute(sender, args); break;
            case "quakecraft:raylength": new CommandRayLength().execute(sender, args); break;
            case "quakecraft:shattercount": new CommandShatterCount().execute(sender, args); break;
            case "quakecraft:shatterlength": new CommandShatterLength().execute(sender, args); break;
            case "quakecraft:listmap": new CommandListMap().execute(sender); break;
            case "quakecraft:start": new CommandStart().execute(sender, args); break;
            case "quakecraft:end": new CommandEnd().execute(sender); break;
            case "quakecraft:scan": new CommandScan().execute(sender, args); break;
            case "quakecraft:clean": new CommandClean().execute(sender, args); break;
            case "quakecraft:showspawn": new CommandShowSpawn().execute(sender, args); break;
            case "quakecraft:loadspawn": new CommandLoadSpawn().execute(sender, args); break;
            case "quakecraft:triggerspeed": new CommandTriggerSpeed().execute(sender, args); break;
            case "quakecraft:dashdelay": new CommandDashDelay().execute(sender, args); break;
            case "quakecraft:config": new CommandConfig().execute(sender); break;
            case "quakecraft:showconfig": new CommandShowConfig().execute(sender); break;
            case "quakecraft:resetconfig": new CommandResetConfig().execute(sender); break;
            case "quakecraft:togglejump": new CommandToggleJump().execute(sender); break;
            case "quakecraft:togglewalk": new CommandToggleWalk().execute(sender); break;
            case "quakecraft:toggledash": new CommandToggleDash().execute(sender); break;
            case "quakecraft:toggleverticaldash": new CommandToggleVerticalDash().execute(sender); break;
            case "quakecraft:togglenametag": new CommandToggleNametag().execute(sender); break;
            case "quakecraft:toggleinvisibility": new CommandToggleInvisibility().execute(sender); break;
            case "quakecraft:toggleblindness": new CommandToggleBlindness().execute(sender); break;
            case "quakecraft:playerslow": new CommandPlayerSlow().execute(sender, args); break;
            case "quakecraft:playerspeed": new CommandPlayerSpeed().execute(sender, args); break;
            case "quakecraft:playerjump": new CommandPlayerJump().execute(sender, args); break;
            case "quakecraft:dashstrength": new CommandDashStrength().execute(sender, args); break;
            case "quakecraft:play": new CommandPlay().execute(sender); break;
            case "quakecraft:joinqueue": new CommandJoinQueue().execute(sender); break;
            case "quakecraft:leavequeue": new CommandLeaveQueue().execute(sender); break;
            case "quakecraft:queue": new CommandQueue().execute(sender); break;
            case "quakecraft:gravity": new CommandGravity().execute(sender, args); break;
            case "quakecraft:spawnwand": new CommandSpawnWand().execute(sender); break;
            case "quakecraft:setquakespawn": new CommandSetQuakeSpawn().execute(sender); break;
            case "quakecraft:reload": new CommandReload().execute(sender); break;
            default: break;
        }
    }

}
