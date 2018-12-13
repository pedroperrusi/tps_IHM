/* Exercises of the course Interfaces Homme Machine.
 * Université Télécom Physique Strasbourg.
 *
 * @author Pedro Henrique SURUAGY PERRUSI
 */
package CI1_Editeur_Graphique2D.src;

import java.awt.Color;

/**
 * The Class Forme2D.
 */
public abstract class Forme2D implements Comparable<Forme2D>
{
	// Parameters ------------------------------------
	/** The origine. */
	private Point2D origine;
	
	/** The couleur. */
	private Color couleur;
	
	/** Static parameter to count the number of created Forme2D;
	 * All objects share this same memory adress. */
	private static int nombreFormes2D = 0;
	
	// Constructors ------------------------------------
	/**
	 * Instantiates a new forme 2 D.
	 */
	public Forme2D() 
	{
		setOrigine(0,0);
		setCouleur(java.awt.Color.blue);
		nombreFormes2D++;
	}
	
	/**
	 * Instantiates a new forme 2 D.
	 *
	 * @param x the x
	 * @param y the y
	 * @param color the color
	 */
	public Forme2D(float x, float y, Color color)
	{
		setOrigine(x,y);
		setCouleur(color);
		nombreFormes2D++;
	}
	
	/**
	 * Instantiates a new forme 2 D.
	 *
	 * @param ptOrigine the pt origine
	 * @param color the color
	 */
	public Forme2D(Point2D ptOrigine, Color color) 
	{
		setOrigine(ptOrigine);
		setCouleur(color);
		nombreFormes2D++;
	}
	
	// Destructor ------------------------------------------
	/** When destructing an object, subtract the number of Forme2D*/
	protected void finalize() throws Throwable
	{
		nombreFormes2D--;
	}
	
	// Methods ---------------------------------------------
	
	/**
	 * Describe the object by:
	 * 		(x,y) : coleur.
	 *
	 * @return the string
	 */
	public String toString() 
	{
		return "origine:coleur " + origine.toString() + ": " + couleur.toString();
	}
	
	/**
	 * Translate the forme2D by deplacing its origine.
	 * An adjust may be needed other points of the form too.
	 */
	public void translate(int dx, int dy)
	{
		setOrigine(new Point2D(origine.getX()+dx, 
							   origine.getY()+dy));
	}
	
	/**
	 * Nombre formes 2 D created (static parameter).
	 *
	 * @return the int
	 */
	public static int nombreFormes2D()
	{
		return nombreFormes2D;
	}
	
	/** Compare areas between two forme 2D
	 * 
	 * @return 1 if this object area is bigger, -1 otherwise
	 */
	@Override
	public int compareTo(Forme2D autreForme)
	{
		if(this.computeArea() > autreForme.computeArea()) 
		{
			return 1;
		}
		else 
		{
			return -1;
		}
	}
	
	// Abstract Methods ------------------------------------
	
	/**
	 * Compute area of the forme2D.
	 *
	 * @return the double
	 */
	public abstract double computeArea();
	
	/**
	 * Draw... still need to figure this out.
	 */
	public abstract void draw();
	
	/**
	 * Checks if a point is inside the forme2D element.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isInside(Point2D pt);
	
	/**
	 * Checks if a point is inside the forme2D element.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isInside(float x, float y);
	
	// 		Get/Set Methods ------------------------------------
	/**
	 * Gets the origine.
	 *
	 * @return the origine
	 */
	public Point2D getOrigine()
	{
		return origine;
	}

	/**
	 * Sets the origine.
	 *
	 * @param origine the new origine
	 * @throws WrongOriginException the wrong origin exception
	 */
	public void setOrigine(Point2D origine) throws WrongOriginException
	{
		if(origine.getX() < 0 || origine.getY() < 0) 
		{
			throw new WrongOriginException();
		}
		this.origine = origine;
	}
	
	/**
	 * Sets the origine.
	 *
	 * @param x the x
	 * @param y the y
	 * @throws WrongOriginException the wrong origin exception
	 */
	public void setOrigine(float x, float y) throws WrongOriginException
	{
		if(x < 0 || y < 0) 
		{
			throw new WrongOriginException();
		}
		this.origine.setX(x);
		this.origine.setY(y);
	}

	/**
	 * Gets the couleur.
	 *
	 * @return the couleur
	 */
	public Color getCouleur()
	{
		return couleur;
	}

	/**
	 * Sets the couleur.
	 *
	 * @param couleur the new couleur
	 */
	public void setCouleur(Color couleur)
	{
		this.couleur = couleur;
	}
	
	/**
	 * The Class WrongOriginException.
	 */
	// Exceptions ----------------------------
	public class WrongOriginException extends IllegalArgumentException
	{
		
		/**
		 * Instantiates a new wrong origin exception.
		 */
		WrongOriginException()
		{
			super("Invalid origin coordinate value");
		}
		
		/**
		 * Instantiates a new wrong origin exception.
		 *
		 * @param errorMsg the error msg
		 */
		WrongOriginException(String errorMsg)
		{
			super(errorMsg);
		}
	}
	
	/**
	 * The Class WrongSizeException.
	 */
	public class WrongSizeException extends IllegalArgumentException
	{
		
		/**
		 * Instantiates a new wrong size exception.
		 */
		WrongSizeException()
		{
			super("Invalid values for width and height");
		}
		
		/**
		 * Instantiates a new wrong size exception.
		 *
		 * @param errorMsg the error msg
		 */
		WrongSizeException(String errorMsg)
		{
			super(errorMsg);
		}
	}
}
