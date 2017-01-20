import javax.swing.JFrame;
//import javax.swing.Jpanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class BasicSwing extends JFrame{
	//Jpanel p = new Jpanel();
	JButton b = new JButton("Hello World");
	JTextField t = new JTextField("Hi");
	JTextArea ta = new JTextArea("How\nare\nyou?");

	public static void main(String[] args){
		new BasicSwing();
	}

	public BasicSwing(){
		super("Basic Swing App");

		setSize(400, 300);
	}
}