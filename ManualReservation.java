
public class ManualReservation {
	public static void main(String[] args) {
		
		AirPlane aFlight;
		try {
			aFlight = new AirPlane(7, 50, 200);
			for(int i=0; i<50; i++){
				for(int j=0; j<7; j++){
					System.out.print(aFlight.getSeats()[i][j].getState());
				}
				System.out.println();
			}
			
			Runnable passenger1 = new autoProducer(1, aFlight, 50, 4, t);
			Runnable passenger2 = new autoProducer(2, aFlight, 50, 4, t);
			
			Thread thread1 = new Thread(passenger1);
			Thread thread2 = new Thread(passenger2);
			
			thread1.start();
			thread2.start();
		} 
		catch (Exception e) {System.out.println(e);}
	}
}
