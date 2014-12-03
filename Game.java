import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game{

	private Tile[][] grid;

	public Game(){
		init();

	}

    private Tile[][] deepCopy(Tile[][] g) {
        Tile[][] result = new Tile[g.length][g[0].length];
        for (int i = 0 ; i < g.length ; i++){
            for (int j = 0 ; j < g[0].length ; j++) {
                result[i][j] = new Tile(g[i][j].getStatus());
            }
        }

        return result;
    }

    private boolean deepEquals(Tile[][] g, Tile[][] g2){
        boolean eq = true;

        return Arrays.deepToString(g).equals(Arrays.deepToString(g2));
        /*for(int i = 0; i < g.length; i++){
            for(int j = 0; j< g[i].length; j++){
                H.o("hh: " + g[i][j].getStatus() );
                H.o("gg: " + g2[i][j].getStatus());
                H.o(g[i][j].getStatus() == g2[i][j].getStatus());
                eq = eq && (g[i][j].getStatus() == g2[i][j].getStatus());
            }
        }*/

        //return eq;
    }
    
 

    private boolean validMoveExists() {
        Tile[][] temp = deepCopy(grid);
        moveLeft();

        if  (!deepEquals(temp, grid)){
            grid = deepCopy(temp);
            return true;
        }
    
         
        moveRight();
        if (!deepEquals(temp, grid)) {
            grid = deepCopy(temp);
            return true;
        }
         
        moveUp();
        if (!deepEquals(temp, grid)) {
            grid = deepCopy(temp);
            return true;
        }
        
        moveDown();
        if (!deepEquals(temp, grid)) {
            grid = deepCopy(temp);
            return true;
        }
       

        return false;
    }

 

	private void init(){
		grid = new Tile[4][4];
		setupTiles();

		H.o(this);

		
		
        do {
         Tile[][] temp = deepCopy(grid);

        String cmd = IOUtil.readString();

        if(cmd.equals("l")){
			moveLeft();
		}if(cmd.equals("r")){
            moveRight();
        }if(cmd.equals("u")){
            moveUp();
        }if(cmd.equals("d")){
            moveDown();
        }
        if(!deepEquals(grid,temp)){
            randomFill();
		}
        H.o(this);
		} while (validMoveExists());
	}
    
    private void randomFill(){
        int randRow = (int) Math.random()*grid.length;
       
       while(!rowContainsZero(randRow)){
             int direction = (Math.random() > 0.5) ? 1 : -1;
            if(randRow == 0 && direction == -1){
                randRow = grid.length -1;

            }else if(randRow == grid.length -1 && direction == 1){
                randRow = 0;
            }else{
              randRow += direction;

            }   
        
        }
     
        randomFillRowZero(randRow);
    }

    private boolean rowContainsZero(int i){
        

        for(Tile tile : grid[i]){
            if(tile.getStatus() == 0){
                return true;
            }
        }
        
        return false;
    }

    //randomly selects a 0 from the given row and inserts a 2 or a 4 (randomly)
    private void randomFillRowZero(int randRow){
        int[] zeroIndexes = new int[grid[randRow].length];
        Arrays.fill(zeroIndexes,-1);
        int k = 0;
        for(int j = 0; j< grid[randRow].length;j++){
            if(grid[randRow][j].getStatus() == 0){
                zeroIndexes[k] = j;
                k++;
            }
        }

       
        int randIndex;
        do{
             randIndex = (int) (Math.random() * zeroIndexes.length);
        }while(zeroIndexes[randIndex] == -1);
        
        H.o("rand row: " + randRow + " \n randIndex: " + randIndex);
        H.o(zeroIndexes.length);
        grid[randRow][zeroIndexes[randIndex]].set(chooseNumRand(2, 4, 0.7));
    }

    public int chooseNumRand(int x, int y, double prob){
        return (Math.random() < prob) ? x : y;
    }

	private void setupTiles(){
		for(int i = 0; i< grid.length; i++){
			for(int j = 0; j< grid[i].length;j++){
				grid[i][j] = new Tile(0);
			}
		}
        grid[0][1].set(2);
        grid[0][3].set(2);
		int rand1 =  (int) Math.ceil(Math.random()*grid.length) - 1;
		int rand2 =  (int) Math.ceil(Math.random()*grid[0].length) -1;
		//grid[rand1][rand2].set(2);

		int rand3 =  (int) Math.ceil(Math.random()*grid.length) -1;
		int rand4 =  (int) Math.ceil(Math.random()*grid[0].length) -1;
		//grid[rand3][rand4].set(2);

		int rand5 =  (int) Math.ceil(Math.random()*grid.length) -1;
		int rand6 =  (int) Math.ceil(Math.random()*grid[0].length) -1;
		//grid[rand5][rand6].set(2);

		int rand7 =  (int) Math.ceil(Math.random()*grid.length) -1;
		int rand8 =  (int) Math.ceil(Math.random()*grid[0].length) -1;
		//grid[rand7][rand8].set(2);

		int rand9 =  (int) Math.ceil(Math.random()*grid.length) -1;
		int rand10 =  (int) Math.ceil(Math.random()*grid[0].length) -1;
		//grid[rand9][rand10].set(2);
	}

	//-1 = invalid lookup
    private int rightOf(int i,int  j){
    	if(j+1 < grid[i].length){
    		return grid[i][j+1].getStatus();
    	}else{
    		return -1;
    	}
        
    }

    private void swap(int i, int j){
        int temp = grid[i][j].getStatus();
        grid[i][j].set(grid[j][i].getStatus());
        grid[j][i].set(temp);
    }

    private void transpose(){
        for(int i = 0; i < grid.length; i++){
            for(int j = i; j< grid[i].length; j++){
                swap(i, j);
            }
        }
    }

    private int leftOf(int i,int  j){
    	if(j>=1){
    		return grid[i][j-1].getStatus();
    	}else{
    		return -1;
    	}
        
    }

	private void moveLeft(){	
        for(int i = 0; i < grid.length;i++){
            	moveRowLeft(i);
        }
	}	

    private void moveRight(){
        for(int i = 0; i < grid.length; i++){
            moveRowRight(i);
        }
    }

    private void moveUp(){
        transpose();
        moveLeft();
        transpose();
    }

    private void moveDown(){
        transpose();
        moveRight();
        transpose();
    }

  
    public void moveRowLeft(int i){
    	Tile[] theRow = grid[i];

    	boolean breakCondition = false;
        
        for(int k = 0; k< grid[i].length;k++){
            stripRowSpacesLeft(i);
        }
    
    	while(!breakCondition){
    		for(int j = 0; j< theRow.length;j++){
    			Tile tile = theRow[j];
    			int theStatus = tile.getStatus();

    			if(rightOf(i,j) == theStatus){
    				tile.dble();
    				grid[i][j+1].set(0);
    			}else if(theStatus == 0){
    				continue;
    			}else if(leftOf(i,j) == 0){
    				grid[i][j-1].set(theStatus);
    				tile.set(0);
    			}
    		}

    		stripRowSpacesLeft(i);
    		//true if should break
    		breakCondition = checkLeftRowBreakCondition(i);

    	}
       

    }

    public void moveRowRight(int i){
         List<Tile> l = Arrays.asList(grid[i]);
         Collections.reverse(l);
         
         grid[i] = (Tile[]) l.toArray();
         moveRowLeft(i);
         l = Arrays.asList(grid[i]);
         Collections.reverse(l);
         grid[i] = (Tile[]) l.toArray();

         
    }

    public void stripRowSpacesLeft(int i){

        
    	for(int j = 0; j < grid[i].length-1; j++){
    		Tile tile = grid[i][j];
    		int theStatus = tile.getStatus();
    		if(theStatus == 0){
    			tile.set(grid[i][j+1].getStatus());
    			grid[i][j+1].set(0);

    		}

    	}
    }

    //returns true if the moveLeft is done for the row. (will cause break)
    public boolean checkLeftRowBreakCondition(int i){
    	//problem with upToFarRightIsNotZero
    	boolean allZero = true;
    	boolean allNotZero = true;
    	boolean farRightIsZero = grid[i][grid[i].length-1].getStatus()==0;
    	boolean upToFarRightIsNotZero = true;
    	boolean farLeftNotZero = grid[i][0].getStatus() != 0;
    	int j = 0;
    	for(Tile tile : grid[i]){
    		int theStatus = tile.getStatus();
    		if(theStatus != 0){
    			allZero = false;
    		}else if(theStatus == 0){
    			if(j < grid[i].length - 1){
    				upToFarRightIsNotZero = false;
    			}else{
    				upToFarRightIsNotZero = true;
    			}
    			allNotZero = false;
    		}
    		j++;
    	}
    	if(allZero || (farRightIsZero && upToFarRightIsNotZero && farLeftNotZero) || allNotZero){
    		/*H.o("allZero: " + allZero);
    		H.o("farRightIsZero: " + farRightIsZero);
    		H.o("upToFarRightIsNotZero: " + upToFarRightIsNotZero);
    		H.o("farLeftNotZero: " + farLeftNotZero);
    		H.o("allNotZero: " + allNotZero);

    		H.o("");*/
    	}
    	return allZero || (farRightIsZero && upToFarRightIsNotZero && farLeftNotZero) || allNotZero;

    }




	public String toString(){
		String output = "";

		for(Tile[] row : grid){
			for(Tile tile : row){
				output += tile.toString();
			}

			output += "\n";
		}

		return output;
	}

}
