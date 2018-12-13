package CI1_Editeur_Graphique2D.src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	
	/**
	 * The main method.
	 *
	 * @param args default arguments list
	 */
	public static void main(String args[])
	{
		EditeurGraphique2D mg = new EditeurGraphique2D("Suragy Perrusi : Editeur Graphique 2D");
		mg.setSize(LARGEUR_PAR_DEFAUT,HAUTEUR_PAR_DEFAUT);
		mg.setVisible(true);
	}
	
	/** The zone dessin. */
	public ZoneDeDessin zoneDessin;
	/** Editeur Panel */
	public JPanel editeurPanel;
	
	/**
	 * Instantiates a new editeur graphique 2 D.
	 *
	 * @param nomFenetre the nom fenetre
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
		final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,200,0));
		// création du bouton ajouter
		final JButton ajouter = new JButton("+");
		// création d’une action pour ajouter dans la liste
		ActionAjouter actionAjout = new ActionAjouter();
		// affectation de cette action au bouton
		ajouter.addActionListener(actionAjout);
		// ajout du bouton dans la fenetre
		panel.add(ajouter);
		
		// création du bouton ajouter
		final JButton supprimer = new JButton("-");
		// création d’une action pour ajouter dans la liste
		ActionSupress actionSupress = new ActionSupress();
		// affectation de cette action au bouton
		supprimer.addActionListener(actionSupress);
		// ajout du bouton dans la fenetre
		panel.add(supprimer);
		
		return panel;
	}
	
	/**
	 * The Class ZoneDeDessin.
	 */
	class ZoneDeDessin extends Component
	{
		/** The liste formes. */
		ArrayList<Forme2D> listeFormes;
		
		/**
		 * Instantiates a new zone de dessin.
		 */
		public ZoneDeDessin()
		{
			super();
			listeFormes = new ArrayList<Forme2D>();
		}
		
		public void paint(Graphics g)
		{
			for(int i = 0; i < listeFormes.size(); i++) 
			{
				listeFormes.get(i).draw(g);
			}
		}
	}
	
	class ActionAjouter implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Point2D origin = randomPosition();
			Color color = new Color(randomColor(), randomColor(), randomColor());
			float cote = randomTaille();
			try 
			{
				zoneDessin.listeFormes.add(new Carre(origin, color, cote));
				zoneDessin.repaint();
			}
			catch(Exception except) 
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
			if(zoneDessin.listeFormes.size() == 0)
				return;
			try {
				zoneDessin.listeFormes.remove(zoneDessin.listeFormes.size()-1);
				zoneDessin.repaint();
			}
			catch(Exception except) 
			{
				System.out.println(except.toString());
			}
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
		return (int)(Math.random() * 255);
	}
	
	float randomTaille() 
	{
		return (float) (50 + Math.random() * ( 150 - 50));
	}
	
}
