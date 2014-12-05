public class Grid{

	private Tile[][] gridArray;
	private GridSettings gridSettings;

	public Grid(){
		this.gridSettings = Constants.getDefaultGridSettings();
	}

	public Grid(GridSettings gridSettings){
		this.gridSettings = g;
	}

	public GridSettings getGridSettings(){
		return gridSettings;
	}

	public void init(){
		int size = getGridSettings().getSize();

		gridArray = new Tile[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				gridArray[i][j] = new Tile();
			}
		}
	}

	public void shift(String dir){ //direction can be "up" "down" "left" or "right"	
        Shifter shifter = new Shifter(this);
        shifter.shift(dir);
	}	


}