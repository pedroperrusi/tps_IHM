/* Exercises of the course Interfaces Homme Machine.
 * Université Télécom Physique Strasbourg.
 *
 * @author Pedro Henrique SURUAGY PERRUSI
 */
package CI1_Editeur_Graphique2D.src;

import java.awt.Color;
import java.awt.Graphics;

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
	
	private int formID;
	
	// Constructors ------------------------------------
	/**
	 * Instantiates a new forme 2 D.
	 */
	public Forme2D() 
	{
		setOrigine(0,0);
		setCouleur(java.awt.Color.blue);
		nombreFormes2D++;
		setFormID(nombreFormes2D);
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
		setFormID(nombreFormes2D);
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
		setFormID(nombreFormes2D);
	}
	
	// Destructor ------------------------------------------
	/** When destructing an object, subtract the number of Forme2D*/
	protected void finalize()
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
	 * Draw object accordingly to its specification, or red if it is selected
	 *
	 * @param g the graphics element
	 * @param boolean which indicates if this form has been selected
	 */
	public abstract void draw(Graphics g, boolean selected);
	
	/**
	 * Checks if a point is inside the forme2D element.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isInside(Point2D pt);
	
	/**
	 * Checks if a point is in the border of a forme2D element.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isBorder(Point2D pt, int borderTolerance);
	
	/**
	 * Checks if a point is inside the forme2D element.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isInside(float x, float y);
	
	/**
	 * Redimension a form accordingly to user mouse input.
	 * Only if both gradient X and gradient Y grow, the size grows.
	 * The same fits both decreascing
	 */
	public abstract void redimensionnement(int gradientX, int gradientY);
	
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
	
	public int getFormID()
	{
		return formID;
	}

	public void setFormID(int formID)
	{
		this.formID = formID;
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
			super("Invalid origin coordinate valueof form2D " + getFormID());
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
			super("Invalid values for width and height of form2D " + getFormID());
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
