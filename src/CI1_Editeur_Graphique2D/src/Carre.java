package CI1_Editeur_Graphique2D.src;

import java.awt.Color;

public class Carre extends Forme2D
{
	private float cote;
	
	public Carre(float cote) 
	{
		super();
		setCote(cote);
	}
	
	public Carre(float x, float y, Color color)
	{
		super(x, y, color);
	}
	
	public Carre(Point2D ptOrigine, Color color, float cote) 
	{
		super(ptOrigine, color);
		setCote(cote);
	}

	@Override
	public double computeArea()
	{
		return cote*cote;
	}

	@Override
	public void translate(Point2D newOrigin)
	{
		setOrigine(newOrigin);
		
	}
	
	@Override
	public void translate(float x, float y)
	{
		setOrigine(x, y);
		
	}

	@Override
	public void draw()
	{
		// TODO Auto-generated method stub
		
	}

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
	
	@Override
	public Boolean isInside(float x, float y)
	{
		return isInside(new Point2D(x,y));
	}

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

	public float getCote()
	{
		return cote;
	}

	public void setCote(float cote)
	{
		this.cote = cote;
	}
}
