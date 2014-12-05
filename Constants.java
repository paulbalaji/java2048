public class Constants{

	public static String LEFT_CMD = "l";
	public static String RIGHT_CMD = "r";
	public static String UP_CMD = "u";
	public static String DOWN_CMD = "d";


	public static GridSettings getDefaultGridSettings(){
		GridSettings gridSettings = new GridSettings();
		gridSettings.setSize(4); //4 is the default grid size
		gridSettings.setTwoProbability(0.9); //90% for a random 2, 10% for a random 4
	}

}