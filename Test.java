import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 * User interface class that extends JFrame class.
 * @author Mia Wang 260559637
 *
 */
public class Test extends JFrame{
	static AirPlane aFlight;
	DefaultTableModel model;
	
	/**
	 * User interface with JTable, JTextField, JButton objects.
	 * ActionListener inside method to get user input from application.
	 */
	public Test(){
		String[] columns = new String[]{"Row","seat: A", "seat: B","","", "seat: C", "seat: D"};
		String[][] seats = new String[50][7];
		seats[0][0]="    1";	seats[0][1]="  __";	seats[0][2]="  __";	seats[0][3]="";	seats[0][4]="";
		seats[0][5]="  __";		seats[0][6]="  __";
		
		for(int i=1; i<50; i++){
				for(int j=1; j<7; j++){
					seats[i][0] = "    "+(i+1);
					seats[i][j] = seats[0][j];
			}
		}
		
		model = new DefaultTableModel(seats, columns);
		JTable table = new JTable(model);
		
		//Container
		JPanel p = new JPanel();
		//SOUTH
		JPanel south = new JPanel();
		JTextField input1 = new JTextField();
		input1.setColumns(10);
		JTextField input2 = new JTextField();
		input2.setColumns(10);
		
		JButton submit = new JButton("Choose!");

		// ActionListener
		submit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				
				String number = input1.getText(); 
				String letter = input2.getText();
				int column = (int)letter.charAt(0)-64;
				if(column == 3 || column == 4)	column += 2;
				int row = Integer.parseInt(number)-1;
				
				try {
					if(aFlight.chooseSeat(number+letter, 3) == true){
						model.setValueAt("User 3", row, column);
						JOptionPane.showMessageDialog(null, "Reserved Successfully!");
					}
					else{
						if(aFlight.IsFull() == true)
							JOptionPane.showMessageDialog(null, "No Available Seats Left.");
						else
							JOptionPane.showMessageDialog(null, "Try Again!");
					}
				} 
				catch (Exception e1) {JOptionPane.showMessageDialog(null, "Invalid Row/Seat Letter Input.");}
			}
		});
		
		//CENTER
		JPanel center = new JPanel();
		JScrollPane scroll = new JScrollPane(table);

		//Label
		JLabel label1 = new JLabel("Row:");
		JLabel label2 = new JLabel("Seat Letter:");
		
		p.setLayout(new BorderLayout());

		center.add(scroll);
		p.add(center, BorderLayout.CENTER);

		south.setLayout(new FlowLayout(80, 10, 20));
		south.add(label1);	south.add(input1);
		south.add(label2);	south.add(input2);
		south.add(submit);	
		south.setSize(200, 40);
		p.add(south, BorderLayout.SOUTH);
		p.setSize(900,1500);
		add(p);
	
		this.setTitle("Choosing Seats System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public DefaultTableModel getModel(){
		return model;
	}

	/**
	 * Main method for running application and starting threads.
	 * @param args
	 */
	public static void main(String[] args){
		Test t = new Test();
		
		try {
			aFlight = new AirPlane(7, 50, 200);
			
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

