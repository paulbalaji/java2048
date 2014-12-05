public class Shifter{

	private Grid grid;

	public Shifter(Grid grid){
		this.grid = grid;
	}

	public void shift(String dir){

		if(dir.equals(Constants.CMD_LEFT) || dir.equals(Constants.CMD_RIGHT)){
			for(int i = 0; i < grid.getGridSettings().getSize();i++){
				shiftRowHorizontally(dir);
			}
		}else if(dir.equals(Constants.CMD_UP) || dir.equals(Constants.CMD_DOWN)){
			for(int j = 0; j< grid.getGridSettings().getSize();j++){
				//shiftRowVertically(dir);
			}
		}
	 	 
	 }

	 public void shiftRowHorizontally(String dir){
	 	int jVector = dir.equals(Constants.CMD_LEFT) ? 1 : -1;

	 }

}