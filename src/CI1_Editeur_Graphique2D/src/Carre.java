/* Exercises of the course Interfaces Homme Machine.
 * Université Télécom Physique Strasbourg.
 *
 * @author Pedro Henrique SURUAGY PERRUSI
 */
package CI1_Editeur_Graphique2D.src;

import java.awt.Color;
import java.awt.Graphics;

// TODO: Auto-generated Javadoc
/**
 *  Squere geometry object (French).
 */
public class Carre extends Forme2D
{
	
	/**  Square size. */
	private float cote;
	
	/**
	 * Instantiates a new carre.
	 *
	 * @param cote the cote
	 */
	public Carre(float cote) 
	{
		super();
		setCote(cote);
	}
	
	/**
	 * Instantiates a new carre.
	 *
	 * @param x the origine.x
	 * @param y the origine.y
	 * @param color the color
	 * @param cote the cote
	 */
	public Carre(float x, float y, Color color, float cote)
	{
		super(x, y, color);
		setCote(cote);
	}
	
	/**
	 * Instantiates a new carre.
	 *
	 * @param ptOrigine the pt origine
	 * @param color the color
	 * @param cote the cote
	 */
	public Carre(Point2D ptOrigine, Color color, float cote) 
	{
		super(ptOrigine, color);
		setCote(cote);
	}

	/**
	 * Describe the object by:
	 * 		Carre: (x,y) : cote: area: coleur.
	 *
	 * @return the string
	 */
	@Override
	public String toString() 
	{
		return "Carre: origine: cote: aire: coleur " + 
				getOrigine().toString() + 
				": " + Float.toString(getCote()) + 
				": " + Double.toString(computeArea()) + 
				": " + getCouleur().toString();
	}
	
	/**
	 * Redimensionnement of the square size
	 *
	 * @param newCote the new cote
	 */
	public void redimensionnement(float newCote) 
	{
		setCote(newCote);
	}
	
	/* (non-Javadoc)
	 * @see CI1_Editeur_Graphique2D.src.Forme2D#computeArea()
	 */
	@Override
	public double computeArea()
	{
		return cote*cote;
	}

	/* (non-Javadoc)
	 * @see CI1_Editeur_Graphique2D.src.Forme2D#draw()
	 */
	@Override
	public void draw(Graphics g)
	{
		int x = Math.round(getOrigine().getX());
		int y = Math.round(getOrigine().getY());
		int cote = Math.round(getCote());
		g.drawRect(x, y, cote, cote);
	}

	/* (non-Javadoc)
	 * @see CI1_Editeur_Graphique2D.src.Forme2D#isInside(CI1_Editeur_Graphique2D.src.Point2D)
	 */
	@Override
	public Boolean isInside(Point2D pt)
	{
		// point must be placed in between origin and origine + cote for  both X and Y
		if(getOrigine().getX() <= pt.getX() && pt.getX() <= getOrigine().getX()+cote &&
		   getOrigine().getY() <= pt.getY() && pt.getY() <= getOrigine().getY()+cote) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see CI1_Editeur_Graphique2D.src.Forme2D#isInside(float, float)
	 */
	@Override
	public Boolean isInside(float x, float y)
	{
		return isInside(new Point2D(x,y));
	}

	/**
	 * Gets the cote.
	 *
	 * @return the cote
	 */
	public float getCote()
	{
		return cote;
	}

	/**
	 * Sets the cote.
	 *
	 * @param cote the new cote
	 * @throws WrongSizeException the wrong size exception
	 */
	public void setCote(float cote) throws WrongSizeException
	{
		if(cote <= 0) 
		{
			throw new WrongSizeException("Cote du carre "+ getFormID() + " invalide");
		}
		this.cote = cote;
	}
}
