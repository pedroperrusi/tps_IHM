package TP2_Calculatrice;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

public class MiniFrame extends JFrame
{
	private final List<JButton> buttonList;
	private static final int N = 4;
	JTextField textField;
	
//	private JButton getGridButton(int r, int c) 
//	{
//        int index = r * N + c;
//        return buttonList.get(index);
//    }
//	
//	private JButton createGridButton(final int row, final int col) {
//        final JButton b = new JButton("r" + row + ",c" + col);
//        b.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JButton gb = GridButtonPanel.this.getGridButton(row, col);
//                System.out.println("r" + row + ",c" + col
//                    + " " + (b == gb)
//                    + " " + (b.equals(gb)));
//            }
//        });
//        return b;
//    }
//	
	public MiniFrame() 
	{
//		J Frame definition ---------------------------------
		super("Calculatrice");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
//		Layout definition -----------------------------------
		this.setLayout(new FlowLayout());
//		Zone d'affichage -------------------------------------
		textField = new JTextField(10);
//		Add it to frame
		this.add(textField);
//		Buttons -----------------------------------------------
		buttonList = new ArrayList<JButton>();
		
		
		this.setSize(300,300);
        this.setVisible(true);
	}
}
