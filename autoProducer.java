import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.table.DefaultTableModel;

/**
 * A class for other threads, which implements Runnable
 * @author Mia Wang 260559637
 *
 */
public class autoProducer implements Runnable{
	private int numID;
	private String seatNum;
	private static final int DELAY = 200;
	private AirPlane flight;
	private Random r = new Random();
	private int rowNum;
	private int columnNum;
	private DefaultTableModel model;
	
	/**
	 * Construct a thread that can choose seat position automatically
	 * @param numID	User ID of thread.
	 * @param aFlight	An airplane object that contains seats objects.
	 * @param rowNum	Total number of rows of an airplane object.
	 * @param columnNum	Total number of columns of an airplane.
	 * @param t		User interface object.
	 */
	public autoProducer(int numID, AirPlane aFlight, int rowNum, int columnNum, Test t){
		this.numID = numID;
		this.seatNum = ""+(r.nextInt(rowNum)+1)+(char) (r.nextInt(columnNum)+65);
		this.flight = aFlight;
		this.rowNum = rowNum;
		this.columnNum = columnNum;
		this.model = t.getModel();
	}
	
	/**
	 * Method for running thread with lock object inside.
	 */
	public void run(){
		Lock aLock = new ReentrantLock();
		aLock.lock();
		try{
			while(true){
				if(flight.IsFull() == false){
					if(flight.chooseSeat(seatNum, numID)==true){
						model.setValueAt("User " + numID, flight.getRow(seatNum), flight.getColumn(seatNum));
					}
					else
						this.seatNum = ""+(r.nextInt(rowNum)+1)+(char) (r.nextInt(columnNum)+65);
						
				}
				else break;
				Thread.sleep((int)(Math.random()*DELAY));
			}
		}
		catch (InterruptedException exception){System.out.println("auto-Interrupt"); } 
		catch (Exception e) {System.out.println(e);}
		finally{aLock.unlock();}
	}

}
