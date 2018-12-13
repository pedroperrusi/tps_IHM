package CI1_Editeur_Graphique2D.src;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	ZoneDeDessin zoneDessin;
	/** Editeur Panel */
	JPanel editeurPanel;
	
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
	}
	
	/**
	 * Instantiates a new editeur graphique 2 D.
	 */
	public EditeurGraphique2D() 
	{
		this("Editeur Graphique 2D");
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
			repaint();
		}
		
		public void paint(Graphics g)
		{
			g.fillOval(50, 50, 100, 100);
		}

	}
}
