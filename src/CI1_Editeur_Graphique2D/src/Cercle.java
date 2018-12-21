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
	 * Redimensionnement of the circle size.
	 *
	 * @param newRadius the new radius
	 */
	public void redimensionnement(float newRadius)
	{
		setRadius(newRadius);
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
	public void draw(Graphics g, boolean selected)
	{
		int x = Math.round(getOrigine().getX());
		int y = Math.round(getOrigine().getY());
		int radius = Math.round(getRadius());
		// check if the object is selected
		if(selected)
			g.setColor(Color.red);
		else
			g.setColor(getCouleur());
		// fill oval uses the diameter
		g.fillOval(x, y, 2*radius, 2*radius);
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
		float dx = getCenter().getX() - x;
		float dy = getCenter().getY() - y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance < getRadius())
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see CI1_Editeur_Graphique2D.src.Forme2D#isBorder(CI1_Editeur_Graphique2D.src.Point2D, int)
	 */
	@Override
	public Boolean isBorder(Point2D pt, int borderTolerance)
	{
		float dx = getCenter().getX() - pt.getX();
		float dy = getCenter().getY() - pt.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		double diffRadiusPoint = getRadius() - distance;
		if (diffRadiusPoint < borderTolerance)
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
	 * @param radius            the new radius
	 * @throws WrongSizeException the wrong size exception
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

	public Point2D getCenter()
	{
		int centerX = (int) (getOrigine().getX() + getRadius());
		int centerY = (int) (getOrigine().getY() + getRadius());
		return new Point2D(centerX, centerY);
	}
	
	/* (non-Javadoc)
	 * @see CI1_Editeur_Graphique2D.src.Forme2D#redimensionnement(int)
	 */
	@Override
	public void redimensionnement(int gradientX, int gradientY)
	{
		int mediumDeplacement;
		if(gradientX > 0 && gradientY > 0 ) 
		{
			// operation keeps the sign
			mediumDeplacement = (gradientX + gradientY)/2;
			this.setRadius(this.getRadius() + mediumDeplacement);
		}
		if(gradientX < 0 && gradientY < 0 ) 
		{
			// operation keeps the sign
			mediumDeplacement = (gradientX + gradientY)/2;
			this.setRadius(this.getRadius() + mediumDeplacement);
		}	
	}

}
