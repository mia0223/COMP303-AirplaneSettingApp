/**
 * A class for constructing an air plane that contains seats objects.
 * @author Mia Wang 260559637
 * 
 */
public class AirPlane{
	private int columnNum;
	private int rowNum;
	private static Seats[][] allSeats;
	private boolean state;
	private int totalSeats;
	
	/**
	 * Construct an airplane which contains seats objects.
	 * @param columnNum		Total number of columns of seats in an airplane.
	 * @param rowNum		Total number of rows of seats in an airplane.
	 * @param totalSeats	Total number of seats in an airplane
	 * @throws Exception	If number of columns or number of rows or number of total seats out of range, thorw exception.
	 */
	public AirPlane(int columnNum, int rowNum, int totalSeats) throws Exception{
		if(columnNum > 0 && rowNum > 0 && totalSeats > 0){
			this.columnNum = columnNum;
			this.rowNum = rowNum;
			allSeats = new Seats[rowNum][columnNum];
			for(int i=0; i<rowNum; i++){
				for(int j=0; j<columnNum; j++){
					allSeats[i][j] = new Seats(i, j, rowNum);
				}
			}
			state = false;
			this.totalSeats = totalSeats;
		}
		else
			throw new Exception("InvalidConstruction");
	}
	
	/**
	 * A method to check if all seats in an airplane have been reserved.
	 * @return	True if airplane is full. False if airplane has seats left.
	 */
	public boolean IsFull(){
		if(totalSeats == 0)
			state = true;
		return state;
	}
	
	/**
	 * A method for user to choose seat that they typed into application
	 * @param seatNum	The string user typed into application. With form "numberLetter" (e.g. 20A)
	 * @param numID		ID number of user
	 * @return	True if user reserved seat successfully. False if user reserved unsuccessfully.
	 * @throws Exception	When user chose invalid seat position or user ID number is invalid.
	 */
	public boolean chooseSeat(String seatNum, int numID) throws Exception{
		int row; int column;
		//boolean result = false;
		Seats choice;
		boolean success = false;
		
		//Check Seat Input value
		row = getRow(seatNum);
		column = getColumn(seatNum);
		
		//Check Producer ID 
		if(numID > 0 && numID < 4){
				if(column == 3 || column ==4)
					column += 2;
				choice = allSeats[row][column];
				if(choice.getState() == "  __"){
					choice.setState(numID);
					System.out.println("("+row+", "+column+")   "+"Reserved Your Seat Successfully!" + "    "+totalSeats+" seats left");
					totalSeats--;
					success = true;
				}
				else
					System.out.println("("+row+", "+column+")   "+"This seat is unavailable."+ "    "+totalSeats+" seats left");
		}
		else
			throw new Exception("Invalid Producer ID");
		
		return success;
		
	}
	
	/**
	 * Parsing user input string to integer number.
	 * @param seatNum 	User input string.
	 * @return	Integer row number.
	 * @throws Exception	User input string is invalid.
	 */
	public int getRow(String seatNum) throws Exception{
		int row;
		if(seatNum.length() == 2)
			row = Character.getNumericValue(seatNum.charAt(0))-1;

		else if(seatNum.length() == 3)
			row = Character.getNumericValue(seatNum.charAt(0))*10 + Character.getNumericValue(seatNum.charAt(1))-1;
		else
			throw new Exception("Invalid Seat Choice");
		// Final Check of Seat Input
		if(row < 0 || row > rowNum)
			throw new Exception("Invalid Seat Choice");
		return row;
	}
	
	/**
	 * Parsing user input string to integer number.
	 * @param seatNum	User input string.
	 * @return	Integer column number.
	 * @throws Exception	User input string is invalid.
	 */
	public int getColumn(String seatNum) throws Exception{
		int column;
		if(seatNum.length() == 2){
			column = (int)seatNum.charAt(1)-64;
			if(column > 2)	column += 2; 
		}

		else if(seatNum.length() == 3){
			column = (int)seatNum.charAt(2)-64;
			if(column > 2)	column += 2;
		}
		else
			throw new Exception("Invalid Seat Choice");
		// Final Check of Seat Input
		if(column < 0 || column > columnNum)
			throw new Exception("Invalid Seat Choice");
		
		return column;
	}
	
	/**
	 * Method to get seat arrangement of an airplane.
	 * @return	2D array with seats object.
	 */
	public static Seats[][] getSeats(){
		return allSeats;
	}
	
	
}
