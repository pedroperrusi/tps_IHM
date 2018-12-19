package CI1_Editeur_Graphique2D.src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

/**
 * The Class EditeurGraphique2D.
 */
public class EditeurGraphique2D extends JFrame
{

	/** The Constant HAUTEUR_PAR_DEFAUT. */
	private final static int HAUTEUR_PAR_DEFAUT = 600;

	/** The Constant LARGEUR_PAR_DEFAUT. */
	private final static int LARGEUR_PAR_DEFAUT = 600;
	
	private final static int MIN_FORM_TAILLE = 50;
	
	private final static int MAX_FORM_TAILLE = 150;

	/**
	 * The main method.
	 *
	 * @param args
	 *            default arguments list
	 */
	public static void main(String args[])
	{
		EditeurGraphique2D mg = new EditeurGraphique2D(
				"Suragy Perrusi : Editeur Graphique 2D");
		mg.setSize(LARGEUR_PAR_DEFAUT, HAUTEUR_PAR_DEFAUT);
		mg.setVisible(true);
	}

	/** The zone dessin. */
	public ZoneDeDessin zoneDessin;
	/** Editeur Panel */
	public JPanel editeurPanel;

	/**
	 * Instantiates a new editeur graphique 2 D.
	 *
	 * @param nomFenetre
	 *            the nom fenetre
	 */
	public EditeurGraphique2D(String nomFenetre)
	{
		super(nomFenetre);
		// Editeur panel () background
		editeurPanel = new JPanel(new BorderLayout());
		setContentPane(editeurPanel);
		this.add(new JLabel("TP4"), BorderLayout.NORTH);

		zoneDessin = new ZoneDeDessin();
		add(zoneDessin, BorderLayout.CENTER);
		add(createBoutons(), BorderLayout.SOUTH);
	}

	/**
	 * Instantiates a new editeur graphique 2 D.
	 */
	public EditeurGraphique2D()
	{
		this("Editeur Graphique 2D");
	}

	private JPanel createBoutons()
	{
		// créé un panel avec des éléments alignés à gauche
		final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
		// création du bouton ajouter
		final JButton ajouter = new JButton("+");
		// bouton ne peut pas etre focalisee
		ajouter.setFocusable(false);
		// création d’une action pour ajouter dans la liste
		ActionAjouter actionAjout = new ActionAjouter();
		// affectation de cette action au bouton
		ajouter.addActionListener(actionAjout);
		// ajout du bouton dans la fenetre
		panel.add(ajouter);

		// création du bouton suprimmer
		final JButton supprimer = new JButton("-");
		// bouton ne peut pas etre focalisee
		supprimer.setFocusable(false);
		// création d’une action pour ajouter dans la liste
		ActionSupress actionSupress = new ActionSupress();
		// affectation de cette action au bouton
		supprimer.addActionListener(actionSupress);
		// ajout du bouton dans la fenetre
		panel.add(supprimer);

		// création du bouton triage
		final JButton tri = new JButton("TRI");
		// bouton ne peut pas etre focalisee
		tri.setFocusable(false);
		// création d’une action pour ajouter dans la liste
		ActionTriage actionTriage = new ActionTriage();
		// affectation de cette action au bouton
		tri.addActionListener(actionTriage);
		// ajout du bouton dans la fenetre
		panel.add(tri);

		return panel;
	}

	/**
	 * The Class ZoneDeDessin.
	 */
	class ZoneDeDessin extends Component
	{
		/** The liste formes. */
		ArrayList<Forme2D> listeFormes;

		/** The selected forme2D id. */
		int selectedId;
		
		/**
		 * Instantiates a new zone de dessin.
		 */
		public ZoneDeDessin()
		{
			super();
			listeFormes = new ArrayList<Forme2D>();
			this.setFocusable(true);
			// listens to keyboard inputs
			this.addKeyListener(new KeyboardInputs());
			// listens to mouse clicks
			this.addMouseListener(new MouseInputs());
		}
		
		public void paint(Graphics g)
		{
			for(Forme2D forme : listeFormes)
			{
				if(this.selectedId == forme.getFormID())
					forme.draw(g, true);
				else
					forme.draw(g, false);
			}
		}
		
		public void addRandomSquare() 
		{
			Point2D origin = randomPosition();
			Color color = new Color(randomColor(), randomColor(), randomColor());
			float cote = randomTaille();
			
			this.listeFormes.add(new Carre(origin, color, cote));
			this.repaint();
		}
		
		public void addRandomCircle() 
		{
			Point2D origin = randomPosition();
			Color color = new Color(randomColor(), randomColor(), randomColor());
			float radius = randomTaille();
			
			this.listeFormes.add(new Cercle(origin, color, radius));
			this.repaint();
		}
		
		public void removeLastForm() 
		{
			if (this.listeFormes.size() == 0)
				return;
			try
			{
				this.listeFormes.remove(this.listeFormes.size() - 1);
				this.repaint();
			} catch (Exception except)
			{
				System.out.println(except.toString());
			}
		}
		
		public void sortForme2D() 
		{
			// Sort objects forme2D accordingly to their surface
			Collections.sort(this.listeFormes, Collections.reverseOrder());
			// Deplace them in reverse order.
			int accumX = 0, accumY = 0;
			for(Forme2D forme : listeFormes)
			{
				forme.setOrigine(accumX, accumY);
				accumX += MAX_FORM_TAILLE;
				// if formes depass the default width, reset X and deplace Y
				if(accumX > LARGEUR_PAR_DEFAUT) 
				{
					accumX = 0;
					accumY += MAX_FORM_TAILLE; 
					// if formes depass both width and height of the window... 
					// reset to 0
					if(accumY > HAUTEUR_PAR_DEFAUT) 
					{
						accumX = 0;
						accumY = 0;
					}
				}
			}
			this.repaint();
		}
		
		public void selectForm2D(Point2D point) 
		{
			for(Forme2D forme : listeFormes)
			{
				if(forme.isInside(point)) 
				{
					this.selectedId = forme.getFormID();
					break;
				}
			}
			this.repaint();
		}
		
	}
	
	class ActionAjouter implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				zoneDessin.addRandomSquare();
			} catch (Exception except)
			{
				System.out.println(except.toString());
			}
		}
	}

	class ActionSupress implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			zoneDessin.removeLastForm();
		}

	}
	
	class ActionTriage implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			zoneDessin.sortForme2D();
		}

	}

	Point2D randomPosition()
	{
		float x = (float) (Math.random() * LARGEUR_PAR_DEFAUT);
		float y = (float) (Math.random() * HAUTEUR_PAR_DEFAUT);
		return new Point2D(x, y);
	}

	int randomColor()
	{
		return (int) (Math.random() * 255);
	}

	float randomTaille()
	{
		return (float) (MIN_FORM_TAILLE + Math.random() * (MAX_FORM_TAILLE - MIN_FORM_TAILLE));
	}

	class KeyboardInputs implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent event)
		{
			char key = event.getKeyChar();
			// If user press r, draw square
			if(key == 'r') 
			{
				zoneDessin.addRandomSquare();
			}
			// If user press c, draw circle
			if(key == 'c') 
			{
				zoneDessin.addRandomCircle();
			}
			// If user press d, supress last form2D
			if(key == 'd') 
			{
				zoneDessin.removeLastForm();
			}
		}

		@Override
		public void keyReleased(KeyEvent event)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent event)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class MouseInputs implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent event)
		{
			zoneDessin.selectForm2D(new Point2D(event.getX(), event.getY()));
		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
