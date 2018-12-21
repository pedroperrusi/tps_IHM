/* Exercises of the course Interfaces Homme Machine.
 * Université Télécom Physique Strasbourg.
 *
 * @author Pedro Henrique SURUAGY PERRUSI
 */
package CI1_Editeur_Graphique2D.src;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * The Class EditeurGraphique2D.
 */
public class EditeurGraphique2D extends JFrame
{

	/** The Constant HAUTEUR_PAR_DEFAUT. */
	private final static int HAUTEUR_PAR_DEFAUT = 600;

	/** The Constant LARGEUR_PAR_DEFAUT. */
	private final static int LARGEUR_PAR_DEFAUT = 600;
	
	/** The Constant MIN_FORM_TAILLE. */
	private final static int MIN_FORM_TAILLE = 50;
	
	/** The Constant MAX_FORM_TAILLE. */
	private final static int MAX_FORM_TAILLE = 150;
	
	/** The Constant BORDER_TOLERANCE */
	private final static int BORDER_TOLERANCE = 5;

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
	
	/**  Editeur Panel. */
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

		zoneDessin = new ZoneDeDessin(LARGEUR_PAR_DEFAUT, HAUTEUR_PAR_DEFAUT,
									  MIN_FORM_TAILLE, MAX_FORM_TAILLE,
									  BORDER_TOLERANCE);
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

	
	/**
	 * Creates the boutons.
	 *
	 * @return the j panel
	 */
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
	
}
