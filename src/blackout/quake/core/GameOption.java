package blackout.quake.core;

public class GameOption {

	public static float FIRE_DELAY = 17;
	public static float DASH_DELAY = 40;
	public static int MAX_SCORE = 25;
	public static int PLAYER_SPEED = 2;
	public static int JUMP_BOOST = 0;
	public static int SLOWNESS = 0;
	
	public static boolean BLINDNESS = false;
	public static boolean INVISIBILITY = false;
	public static boolean NAMETAG = false;
	public static boolean DASH = true;
	public static boolean WALK = true;
	public static boolean JUMP = true;
	
	public static void restoreConfiguration() {
		FIRE_DELAY = 17;
		DASH_DELAY = 40;
		MAX_SCORE = 25;
		PLAYER_SPEED = 2;
		JUMP_BOOST = 0;
		SLOWNESS = 0;
		
		BLINDNESS = false;
		INVISIBILITY = false;
		NAMETAG = false;
		DASH = true;
		JUMP = true;
		WALK = true;
	}
	
}
