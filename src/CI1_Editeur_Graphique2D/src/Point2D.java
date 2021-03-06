/* Exercises of the course Interfaces Homme Machine.
 * Université Télécom Physique Strasbourg.
 *
 * @author Pedro Henrique SURUAGY PERRUSI
 */
package CI1_Editeur_Graphique2D.src;

/**
 * The Class Point2D.
 */
public class Point2D
{
	
	/** Point x. */
	private float x;
	
	/** Point y. */
	private float y;

	/**
	 * Instantiates a new point 2 D.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Point2D(float x, float y)
	{
		setX(x);
		setY(y);
	}
	
	/**
	 * Instantiates a new point 2 D.
	 * Default values are x = y = 0.
	 */
	public Point2D()
	{
		setX(0);
		setY(0);
	}


	/* Point2D to String
	 */
	public String toString() 
	{
		return "(" + x + "," + y + ")"; 
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(float x)
	{
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public float getY()
	{
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(float y)
	{
		this.y = y;
	}
}
