/* Exercises of the course Interfaces Homme Machine.
 * Université Télécom Physique Strasbourg.
 *
 * @author Pedro Henrique SURUAGY PERRUSI
 */

package CI1_Editeur_Graphique2D.src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

/**
 * The Class Action Ajouter for the + button.
 */
class ActionAjouter implements ActionListener
{
	/** Zone de dessin object */
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

/**
 * The Class Action Suppress for the - button.
 */

class ActionSupress implements ActionListener
{
	/** Zone de dessin object */
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

/**
 * The Class Triage Ajouter for the "TRI" button.
 */

class ActionTriage implements ActionListener
{
	/** Zone de dessin object */
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

/**
 * The Class Select Select form for the radio button.
 */

class ActionSelectForme implements ActionListener
{
	/** Zone de dessin object */
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

/**
 * The Class KeyboardInputs Ajouter for the keyboard interface
 */
class KeyboardInputs implements KeyListener
{
	/** Zone de dessin object */
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

/**
 * The Mouse Inputs for the mouse clicks processing.
 * 
 * OBS 1: The second click over an selected forme2D should be close to a double click.
 * OBS 2: the resize feature is not functional
 */
class MouseInputs implements MouseListener, MouseMotionListener
{
	/** Integer if an object has been selected (1), if a border has been selected (2), or none (0)*/
	int formeSelectionCode;
	
	/** Tolerance for region considered as border*/
	int borderTolerance;
	
	Point2D lastDraggedPoint;
	
	/** Zone de dessin object */
	ZoneDeDessin dessin;
	
	MouseInputs(ZoneDeDessin dessin, int borderTolerance)
	{
		this.dessin = dessin;
		this.borderTolerance = borderTolerance;
		this.lastDraggedPoint = new Point2D(0,0);
	}
	
	@Override
	public void mouseClicked(MouseEvent event)
	{
		// if its a left click and no form is selected, select form2D
		if(SwingUtilities.isLeftMouseButton(event))
			this.formeSelectionCode = dessin.selectForm2D(new Point2D(event.getX(), event.getY()),
													 	  this.borderTolerance);
		// if its a right click, descelect any form2D
		if(SwingUtilities.isRightMouseButton(event))
		{
			dessin.unselectForm2D();
			this.lastDraggedPoint = new Point2D(0,0);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent event)
	{
		// Rappel from ZoneDeDessin.selectForm: 
		//		(0: out of bounds, 1: form selected, 2: second click on the same form, 3: click on the border)
		// If we click again the selected form, deplace its origin
		if(formeSelectionCode == 2) 
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
		// if we click on the border
		if(formeSelectionCode == 3) 
		{
//			Point2D newPt = new Point2D(event.getX(),event.getY());
//			// if its the first time the user resizes this form...
//			if(lastDraggedPoint.getX() == 0 && lastDraggedPoint.getY() == 0)
//				lastDraggedPoint = newPt;
//			else 
//			{
//				dessin.resizeSelectedForm(newPt,lastDraggedPoint);
//				lastDraggedPoint = newPt;
//			}
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
