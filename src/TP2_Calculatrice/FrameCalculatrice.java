package TP2_Calculatrice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FrameCalculatrice extends JFrame {
	Calculatrice calculatrice;
	JButton buttons[] ;
    JButton operators[];
    JButton eq;
    JButton clr;
    JTextField tf;
     
     
    private final String OPS[] = { "+","-","x","/"};
    
    public FrameCalculatrice(String s){
        super(s);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        calculatrice = new Calculatrice();
         
        tf = new JTextField(10);
        add(tf);
         
        clr = new JButton("CLR");
        addOperation(clr);
         
        buttons = new JButton[11];
        for (int i = 0; i<buttons.length-1; i++){
            buttons[i] = new JButton(i+"");
            addNumber(buttons[i]);
        }
        buttons[buttons.length-1]=new JButton(".");
        addNumber(buttons[buttons.length-1]);
         
        operators = new JButton[OPS.length];
        for (int i = 0; i<operators.length; i++){
            operators[i] = new JButton(OPS[i]);
            addOperation(operators[i]);
        }
         
        eq = new JButton("=");
        addOperation(eq);
             
        setSize(220,200);
        setVisible(true);
        setResizable(false);
    }
    
    public float getFloatFromText() 
    {
    	// Function which handles exceptions on parsing values from the text box
		float value = 0;
    	String accumStr = tf.getText();
		try 
		{
			value = Float.parseFloat(accumStr);
			// clean text area
    		tf.setText("");
		}
		catch(NumberFormatException ex) 
		{
			tf.setText("Error: Invalid user entry");
		}
		return value;
    } 
    
    /* User interfaces Callbacks */
    // simple function to remember calling actionListener
    public void addNumber(JButton button) 
    {
    	// define reaction to each button
    	button.addActionListener(
    			new ActionListener()
					{
						public void actionPerformed(ActionEvent e) 
						{
				    		// get string command of the button pressed
				        	String command = e.getActionCommand();
				        	// Simply concatenate string to text area
				        	tf.setText(tf.getText() + command);
						}
					});
    	this.add(button);
    }
    
    public void addOperation(JButton button) 
    {
    	// Anonyme class to process operation
    	button.addActionListener(
    			new ActionListener()
    			{
					public void actionPerformed(ActionEvent e) 
					{
						// get string command of the button pressed
				    	String command = e.getActionCommand();
				    	// If command is an operation (+, -, x, /)
				    	if((command == "+")||(command == "-")||(command == "x")||(command == "/")) 
				    	{
				    		// pass the text value to the calculator as first operand
				    		calculatrice.setOperand(getFloatFromText());
				    		// pass the operation type
				    		calculatrice.setOperation(command);
				    	}
				    	else if(command == "CLR") 
				    	{
				    		calculatrice.clear();
				    		tf.setText("");
				    	} else if(command == "=") 
				    	{
				    		try 
				    		{
				    			float result = calculatrice.getResult(getFloatFromText());
				    			tf.setText(Float.toString(result));
				    		} catch(Calculatrice.InvalidUserEntry except) 
				    		{
				    			tf.setText(except.getMessage());
				    		}
				    	}
					}
    			});
    	this.add(button);
    }
    
}
