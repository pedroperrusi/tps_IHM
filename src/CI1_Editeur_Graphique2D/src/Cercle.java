/* Exercises of the course Interfaces Homme Machine.
 * Université Télécom Physique Strasbourg.
 *
 * @author Pedro Henrique SURUAGY PERRUSI
 */

package CI1_Editeur_Graphique2D.src;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The Class Cercle (Circle).
 */
public class Cercle extends Forme2D
{

	/** The radius. */
	private float radius;

	/**
	 * Instantiates a new carre.
	 *
	 * @param radius
	 *            the radius
	 */
	public Cercle(float radius)
	{
		super();
		setRadius(radius);
	}

	/**
	 * Instantiates a new carre.
	 *
	 * @param x
	 *            the origine.x
	 * @param y
	 *            the origine.y
	 * @param color
	 *            the color
	 * @param radius
	 *            the radius
	 */
	public Cercle(float x, float y, Color color, float radius)
	{
		super(x, y, color);
		setRadius(radius);
	}

	/**
	 * Instantiates a new carre.
	 *
	 * @param ptOrigine
	 *            the pt origine
	 * @param color
	 *            the color
	 * @param radius
	 *            the radius
	 */
	public Cercle(Point2D ptOrigine, Color color, float radius)
	{
		super(ptOrigine, color);
		setRadius(radius);
	}

	/**
	 * Describe the object by: Cercle: (x,y) : radius: area: coleur.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "Carre: origine: cote: aire: coleur " + getOrigine().toString()
				+ ": " + Float.toString(getRadius()) + ": "
				+ Double.toString(computeArea()) + ": "
				+ getCouleur().toString();
	}

	/**
	 * Redimensionnement of the circle size
	 *
	 * @param new
	 *            Radius will become radius
	 */
	public void redimensionnement(float newRadius)
	{
		setRadius(newRadius);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * CI1_Editeur_Graphique2D.src.Forme2D#compareTo(CI1_Editeur_Graphique2D.src
	 * .Forme2D)
	 */
	@Override
	public int compareTo(Forme2D arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CI1_Editeur_Graphique2D.src.Forme2D#computeArea()
	 */
	@Override
	public double computeArea()
	{
		return Math.PI * getRadius() * getRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CI1_Editeur_Graphique2D.src.Forme2D#draw()
	 */
	@Override
	public void draw(Graphics g)
	{
		int x = Math.round(getOrigine().getX());
		int y = Math.round(getOrigine().getY());
		
		int radius = Math.round(getRadius());
		
		g.drawOval(x, y, radius, radius);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * CI1_Editeur_Graphique2D.src.Forme2D#isInside(CI1_Editeur_Graphique2D.src.
	 * Point2D)
	 */
	@Override
	public Boolean isInside(Point2D pt)
	{
		return isInside(pt.getX(), pt.getY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CI1_Editeur_Graphique2D.src.Forme2D#isInside(float, float)
	 */
	@Override
	public Boolean isInside(float x, float y)
	{
		float dx = getOrigine().getX() - x;
		float dy = getOrigine().getY() - y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance < getRadius())
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * Gets the radius.
	 *
	 * @return the radius
	 */
	public float getRadius()
	{
		return radius;
	}

	/**
	 * Sets the radius.
	 *
	 * @param radius
	 *            the new radius
	 */
	public void setRadius(float radius) throws WrongSizeException
	{
		if (radius <= 0)
		{
			throw new WrongSizeException(
					"Radius du cercle" + getFormID() + " invalide");
		}
		this.radius = radius;
	}

}
