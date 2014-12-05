
public class Tile{

	private int status = 0;

	public Tile(){}

	public Tile(int status){
		this.status = status;
	}

	public void set(int newStatus){
		status = newStatus;
	}

	public void dble(){
		status *= 2;
	}

	public int getStatus(){
		return status;
	}

	public String toString(){
		return "[ " + Integer.toString(status) + " ]";
	}





}