package blackout.quake.core;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;

public class Utils {
	
	public static String centerText(String text) {
		int maxWidth = 60;
		int spaces = (int) Math.round((maxWidth - 1.4 * ChatColor.stripColor(text).length()) / 2);
		
		return StringUtils.repeat(" ", spaces) + text;
	}

}
