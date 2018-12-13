/* Exercises of the course Interfaces Homme Machine.
 * Université Télécom Physique Strasbourg.
 *
 * @author Pedro Henrique SURUAGY PERRUSI
 */
package CI1_Editeur_Graphique2D.src;

import java.awt.Color;

/**
 * Geometrie ressources pour l'editeur graphique.
 */
public class Geometrie
{
	
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
		
		// Constructors ------------------------------------
		/**
		 * Instantiates a new forme 2 D.
		 */
		public Forme2D() 
		{
			setOrigine(0,0);
			setCouleur(java.awt.Color.blue);
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
		}
		
		// Abstract Methods ------------------------------------
		
		/**
		 * Compute area of the forme2D.
		 *
		 * @return the double
		 */
		public abstract double computeArea();
		
		/**
		 * Translate the forme2D by deplacing its origine.
		 * An adjust may be needed other points of the form too.
		 */
		public abstract void translate();
		
		/**
		 * Describe the object by:
		 * 		(x,y) : coleur
		 */
		public abstract String toString();
		
		/**
		 * Draw... still need to figure this out.
		 */
		public abstract void draw();
		
		/**
		 * Checks if a point is inside the forme2D element.
		 *
		 * @return the boolean
		 */
		public abstract Boolean isInside();
		
		/**
		 * Area comparison between forme2D elements
		 *
		 * @return the double
		 */
		public abstract double compareTo();
		
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
		 */
		public void setOrigine(Point2D origine) throws WrongOriginException
		{
			if(origine.x < 0 || origine.y < 0) 
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
		
		// Exceptions ----------------------------
		public class WrongOriginException extends IllegalArgumentException
		{
			WrongOriginException()
			{
				super("Invalid origin coordinate value");
			}
			
			WrongOriginException(String errorMsg)
			{
				super(errorMsg);
			}
		}
		
		public class WrongSizeException extends IllegalArgumentException
		{
			WrongSizeException()
			{
				super("Invalid values for width and height");
			}
			
			WrongSizeException(String errorMsg)
			{
				super(errorMsg);
			}
		}
	}
	
	
	/**
	 * The Class Point2D.
	 */
	public static class Point2D
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
}
