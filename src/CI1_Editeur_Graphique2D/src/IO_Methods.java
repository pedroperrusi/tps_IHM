package CI1_Editeur_Graphique2D.src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

class ActionAjouter implements ActionListener
{
	ZoneDeDessin dessin;
	
	ActionAjouter(ZoneDeDessin dessin)
	{
		this.dessin = dessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			dessin.addRandomDefaultForme();
		} catch (Exception except)
		{
			System.out.println(except.toString());
		}
	}
}

class ActionSupress implements ActionListener
{
	ZoneDeDessin dessin;
	
	ActionSupress(ZoneDeDessin dessin)
	{
		this.dessin = dessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		dessin.removeLastForm();
	}

}

class ActionTriage implements ActionListener
{
	ZoneDeDessin dessin;
	
	ActionTriage(ZoneDeDessin dessin)
	{
		this.dessin = dessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		dessin.sortForme2D();
	}

}

class ActionSelectForme implements ActionListener
{
	ZoneDeDessin dessin;
	
	ActionSelectForme(ZoneDeDessin dessin)
	{
		this.dessin = dessin;
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand() == "Carre") 
		{
			dessin.selectedForme = 1;
		}
		else if(event.getActionCommand() == "Cercle") 
		{
			dessin.selectedForme = 2;
		}
	}
}

class KeyboardInputs implements KeyListener
{
	ZoneDeDessin dessin;
	
	KeyboardInputs(ZoneDeDessin dessin)
	{
		this.dessin = dessin;
	}
	
	@Override
	public void keyPressed(KeyEvent event)
	{
		char key = event.getKeyChar();
		// If user press r, draw square
		if(key == 'r') 
		{
			dessin.addRandomSquare();
		}
		// If user press c, draw circle
		if(key == 'c') 
		{
			dessin.addRandomCircle();
		}
		// If user press d, supress last form2D
		if(key == 'd') 
		{
			dessin.removeLastForm();
		}
	}
	@Override
	public void keyReleased(KeyEvent event){}
	@Override
	public void keyTyped(KeyEvent event){}
}

class MouseInputs implements MouseListener, MouseMotionListener
{
	boolean focusSelected;
	
	ZoneDeDessin dessin;
	
	MouseInputs(ZoneDeDessin dessin)
	{
		this.dessin = dessin;
	}
	
	@Override
	public void mouseClicked(MouseEvent event)
	{
		// if its a left click and no form is selected, select form2D
		if(SwingUtilities.isLeftMouseButton(event))
			this.focusSelected = dessin.selectForm2D(new Point2D(event.getX(), 
														 		     event.getY()));
		// if its a right click, descelect any form2D
		if(SwingUtilities.isRightMouseButton(event))
			dessin.unselectForm2D();
	}
	
	@Override
	public void mouseDragged(MouseEvent event)
	{
		// If the last form clicked is the selected one
		if(focusSelected) 
		{
			try 
			{
				dessin.deplaceSelectedForme(new Point2D(event.getX(), 
															event.getY()));
			}catch(Exception exeption) 
			{
				System.out.println(exeption);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0){}
	@Override
	public void mouseExited(MouseEvent arg0){}
	@Override
	public void mousePressed(MouseEvent arg0){}
	@Override
	public void mouseReleased(MouseEvent arg0){}
	@Override
	public void mouseMoved(MouseEvent arg0){}
}
