package calculator;
import acm.gui.*;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
public class JCalcGUI extends Program{
	
	/* credit: the 'temperature converter' program uploaded on myCourses*/
	
	final int BUTTON_SIZE = 100;
	
	// creating the input and output text box
	JTextField inputField; 
	JTextField outputField;
	
	// for calculation purpose
	Queue Qin = new Queue();
	postFix pf = new postFix();
	
	public void init() {

		setLayout(new TableLayout(9, 4));
			
		// input field
		inputField =  new JTextField("Key in your function here");
		inputField.setActionCommand("=");
		inputField.addActionListener(this);
			
		outputField = new JTextField("See the result here");
		outputField.setActionCommand("=");
		outputField.addActionListener(this);
			
		add(inputField,"gridwidth=400 height="+30);
		add(outputField,"gridwidth=400 height="+30);
		addButtons();
		addActionListeners();
		
		
	}
	
	private void addButtons() {
		String constraint = "width=" + BUTTON_SIZE; 
		
		// pas 1st row
		add(new JButton("C"), constraint);
		add(new JButton(""), constraint);
		add(new JButton(""), constraint);
		add(new JButton("/"), constraint);
		
		// pad - 2nd row
		add(new JButton("7"), constraint);
		add(new JButton("8"), constraint);
		add(new JButton("9"), constraint);
		add(new JButton("*"), constraint);
		
		// pad 3rd row
		add(new JButton("4"), constraint);
		add(new JButton("5"), constraint);
		add(new JButton("6"), constraint);
		add(new JButton("-"), constraint);
		
		// pad - 4th row
		add(new JButton("1"), constraint);
		add(new JButton("2"), constraint);
		add(new JButton("3"), constraint);
		add(new JButton("+"), constraint);
		
		// pad -5th row
		add(new JButton("0"), constraint);
		add(new JButton("."), constraint);
		add(new JButton("^"), constraint);
		add(new JButton("="), constraint);
		
		// pad -6th row
		add(new JButton("("), constraint);
		add(new JButton(")"), constraint);
		add(new JButton(""), constraint);
		add(new JButton(""), constraint);
		
		// pad -7th row
		add(new JButton(""), constraint);
		add(new JButton(""), constraint);
		add(new JButton(""), constraint);
		add(new JButton("Quit"), constraint);
	}

	public void actionPerformed(ActionEvent e) {
		
		// try-catch is used to prevent the crash of the program
		try {
			String cmd = e.getActionCommand();
			if(cmd.equals("=")) {
			
				pf.parse(inputField.getText(), Qin);
				double result = pf.doExpression(Qin); 
				outputField.setText(Double.toString(result));
			}
			else if (cmd.equals("C")) {
				inputField.setText("");
				outputField.setText("");
			}
			else if(cmd.equals("Quit")) {
				System.exit(0);
			}
			else {
				String txt = inputField.getText();
				inputField.setText(txt+cmd);
			}
		}	
		// do nothing just o prevent the crash of the program
		catch (NullPointerException e1) {
		
		}
	}
		

}
