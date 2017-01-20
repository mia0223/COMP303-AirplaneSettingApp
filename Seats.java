/**
 * Each Seat object has a state that indicates if this seat has been taken or not.
 * @author Mia Wang 260559637
 */
public class Seats{
	private String state;	// true for occupied, false for empty
	/**
	 * Constructs a Seats object that contains seat state information.
	 * @param row	Input row from an airplane that indicates row position of seat.
	 * @param column	Input column from an airplane that indicates column position of seat
	 * @param rowNum	Total number of rows of an airplane
	 * @throws Exception	Row or column out of range of the airplane.
	 */
	public Seats(int row, int column, int rowNum) throws Exception{
		if(row > -1 && row < rowNum && (column > 0 && column < 3) || (column > 4 && column < 7)){
			state = "  __";
		}
		else if(row > -1 && row < rowNum && column == 3 || column == 4)
			state = "  ";
		else if(row > -1 && row < rowNum && column == 0)
			state = ""+(row+1);
		else{
			throw new Exception("InvalidSeatNumber");
		}
	}
	
	/**
	 * For user to choose seat with their user ID.
	 * @param numID	User ID number, that can be shown on each seat.
	 * @throws Exception	User ID is not valid.
	 */
	public void setState(int numID) throws Exception{
		if(numID > 0 && numID < 4){
			state = ""+ numID;
		}
		else
			throw new Exception("InvalidProducerID");
	}
	
	/**
	 * Used for check if a specific has been taken.
	 * @return The state of that seat.
	 */
	public String getState(){
		return state;
	}
}