package CI1_Editeur_Graphique2D.src;

import java.awt.*;
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

	/** Methodes utiles --------------------------*/
	
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
	
	private JPanel createBoutons()
	{
		// créé un panel avec des éléments alignés à gauche
		final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
		// création du bouton ajouter
		final JButton ajouter = new JButton("+");
		// bouton ne peut pas etre focalisee
		ajouter.setFocusable(false);
		// création d’une action pour ajouter dans la liste
		ActionAjouter actionAjout = new ActionAjouter(zoneDessin);
		// affectation de cette action au bouton
		ajouter.addActionListener(actionAjout);
		// ajout du bouton dans la fenetre
		panel.add(ajouter);

		// création du bouton suprimmer
		final JButton supprimer = new JButton("-");
		// bouton ne peut pas etre focalisee
		supprimer.setFocusable(false);
		// création d’une action pour ajouter dans la liste
		ActionSupress actionSupress = new ActionSupress(zoneDessin);
		// affectation de cette action au bouton
		supprimer.addActionListener(actionSupress);
		// ajout du bouton dans la fenetre
		panel.add(supprimer);

		// création du bouton triage
		final JButton tri = new JButton("TRI");
		// bouton ne peut pas etre focalisee
		tri.setFocusable(false);
		// création d’une action pour ajouter dans la liste
		ActionTriage actionTriage = new ActionTriage(zoneDessin);
		// affectation de cette action au bouton
		tri.addActionListener(actionTriage);
		// ajout du bouton dans la fenetre
		panel.add(tri);

		// adds radio buttons for selecting square or circle
		// squere button
		JRadioButton carreButton = new JRadioButton("Carre");
		carreButton.setFocusable(false);
		carreButton.setActionCommand("Carre");
		carreButton.setSelected(true);
		// circle button
		JRadioButton cercleButton = new JRadioButton("Cercle");
		cercleButton.setFocusable(false);
		cercleButton.setActionCommand("Cercle");
		cercleButton.setSelected(false);
		// radio button group (pour selectioner seulement 1 des buttons)
		ButtonGroup group = new ButtonGroup();
		group.add(carreButton);
		group.add(cercleButton);
		// add actionListener
		carreButton.addActionListener(new ActionSelectForme(zoneDessin));
		cercleButton.addActionListener(new ActionSelectForme(zoneDessin));
		// add group 
		panel.add(carreButton);
		panel.add(cercleButton);
		
		return panel;
	}

	/**
	 * The Class ZoneDeDessin.
	 */
	class ZoneDeDessin extends Component
	{
		/** Selected forme by default (1: carre, 2: cercle)*/
		public int selectedForme = 1;
		
		/** The liste formes. */
		ArrayList<Forme2D> listeFormes;

		/** The selected forme2D id. */
		private int selectedId;
		
		/**
		 * Instantiates a new zone de dessin.
		 */
		public ZoneDeDessin()
		{
			super();
			listeFormes = new ArrayList<Forme2D>();
			this.setFocusable(true);
			// listens to keyboard inputs
			this.addKeyListener(new KeyboardInputs(this));
			// listens to mouse clicks
			MouseInputs mouseInterface = new MouseInputs(this);
			this.addMouseListener(mouseInterface);
			this.addMouseMotionListener(mouseInterface);
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
		
		public void addRandomDefaultForme() 
		{
			if(selectedForme == 1)
				this.addRandomSquare();
			if(selectedForme == 2)
				this.addRandomCircle();
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
		
		public void unselectForm2D() 
		{
			this.selectedId = 0;
			this.repaint();
		}
		
		public boolean selectForm2D(Point2D point) 
		{
			boolean sameFormSelected = false;
			for(Forme2D forme : listeFormes)
			{
				if(forme.isInside(point)) 
				{
					if(this.selectedId == forme.getFormID())
						sameFormSelected = true;
					else
						this.selectedId = forme.getFormID();
					break;
				}
			}
			this.repaint();
			return sameFormSelected;
		}
		
		public void deplaceSelectedForme(Point2D newOrigin) 
		{
			for(Forme2D forme : listeFormes)
			{
				if(this.selectedId == forme.getFormID()) 
				{
					forme.setOrigine(newOrigin);
					break;
				}
			}
			this.repaint();
		}
		
		public boolean anySelected() 
		{
			if(this.selectedId != 0)
				return true;
			else
				return false;
		}
		
	}
	
}
