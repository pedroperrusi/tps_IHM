package TP2_Calculatrice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FrameCalculatrice extends JFrame implements ActionListener {
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
         
         
        tf = new JTextField(10);
        add(tf);
         
        clr = new JButton("CLR");
        addButton(clr);
         
        buttons = new JButton[11];
        for (int i = 0; i<buttons.length-1; i++){
            buttons[i] = new JButton(i+"");
            addButton(buttons[i]);
        }
        buttons[buttons.length-1]=new JButton(".");
        addButton(buttons[buttons.length-1]);
         
        operators = new JButton[OPS.length];
        for (int i = 0; i<operators.length; i++){
            operators[i] = new JButton(OPS[i]);
            addButton(operators[i]);
        }
         
        eq = new JButton("=");
        addButton(eq);
             
        setSize(220,200);
        setVisible(true);
        setResizable(false);
    }
    
    // simple function to remember calling actionListener
    public void addButton(JButton button) 
    {
    	button.addActionListener(this);
    	this.add(button);
    }
    
    /// When a button is pressed...
    public void actionPerformed(ActionEvent e)
    {
    	// get string command of the button pressed
    	String command = e.getActionCommand();
    	// series of conditionals to determine the operation type
    	tf.setText(tf.getText() + command);
    }
}
