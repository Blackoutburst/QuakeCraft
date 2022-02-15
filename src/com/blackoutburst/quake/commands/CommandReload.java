package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.core.Utils;

public class CommandReload {

	public void execute() {
		Utils.loadConfig();
	}
	
}
