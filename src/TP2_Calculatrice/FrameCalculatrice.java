package TP2_Calculatrice;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FrameCalculatrice extends JFrame {
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
         
        add(clr);
         
        buttons = new JButton[11];
        for (int i = 0; i<buttons.length-1; i++){
            buttons[i] = new JButton(i+"");
            add(buttons[i]);
        }
        buttons[buttons.length-1]=new JButton(".");
        add(buttons[buttons.length-1]);
         
        operators = new JButton[OPS.length];
        for (int i = 0; i<operators.length; i++){
            operators[i] = new JButton(OPS[i]);
            add(operators[i]);
        }
         
        eq = new JButton("=");
        add(eq);
             
        setSize(220,200);
        setVisible(true);
        setResizable(false);
         
    }
}
