package CI1_Editeur_Graphique2D.src;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Class ZoneDeDessin.
 */
class ZoneDeDessin extends Component
{
	/** Selected forme by default (1: carre, 2: cercle)*/
	public int selectedForme = 1;
	
	/** The liste formes. */
	ArrayList<Forme2D> listeFormes;

	/** The selected forme2D id. */
	private int selectedId;
	
	int windowWidith, windowHeight;
	int minFormSize, maxFormSize;
	
	/**
	 * Instantiates a new zone de dessin.
	 */
	public ZoneDeDessin(int windowWidith, int windowHeight, int minFormSize, int maxFormSize)
	{
		super();
		// Add window and forms minimum and maximum sizes
		this.windowWidith = windowWidith;
		this.windowHeight = windowHeight;
		this.minFormSize = minFormSize;
		this.maxFormSize = maxFormSize;
		listeFormes = new ArrayList<Forme2D>();
		this.setFocusable(true);
		// listens to keyboard inputs
		this.addKeyListener(new KeyboardInputs(this));
		// listens to mouse clicks
		MouseInputs mouseInterface = new MouseInputs(this);
		this.addMouseListener(mouseInterface);
		this.addMouseMotionListener(mouseInterface);
	}
	
	public void paint(Graphics g)
	{
		for(Forme2D forme : listeFormes)
		{
			if(this.selectedId == forme.getFormID())
				forme.draw(g, true);
			else
				forme.draw(g, false);
		}
	}
	
	public void addRandomDefaultForme() 
	{
		if(selectedForme == 1)
			this.addRandomSquare();
		if(selectedForme == 2)
			this.addRandomCircle();
	}
	
	public void addRandomSquare() 
	{
		Point2D origin = randomPosition();
		Color color = new Color(randomColor(), randomColor(), randomColor());
		float cote = randomTaille();
		
		this.listeFormes.add(new Carre(origin, color, cote));
		this.repaint();
	}
	
	public void addRandomCircle() 
	{
		Point2D origin = randomPosition();
		Color color = new Color(randomColor(), randomColor(), randomColor());
		float radius = randomTaille();
		
		this.listeFormes.add(new Cercle(origin, color, radius));
		this.repaint();
	}
	
	public void removeLastForm() 
	{
		if (this.listeFormes.size() == 0)
			return;
		try
		{
			this.listeFormes.remove(this.listeFormes.size() - 1);
			this.repaint();
		} catch (Exception except)
		{
			System.out.println(except.toString());
		}
	}
	
	public void sortForme2D() 
	{
		// Sort objects forme2D accordingly to their surface
		Collections.sort(this.listeFormes, Collections.reverseOrder());
		// Deplace them in reverse order.
		int accumX = 0, accumY = 0;
		for(Forme2D forme : listeFormes)
		{
			forme.setOrigine(accumX, accumY);
			accumX += maxFormSize;
			// if formes depass the default width, reset X and deplace Y
			if(accumX > windowWidith) 
			{
				accumX = 0;
				accumY += maxFormSize; 
				// if formes depass both width and height of the window... 
				// reset to 0
				if(accumY > windowHeight) 
				{
					accumX = 0;
					accumY = 0;
				}
			}
		}
		this.repaint();
	}
	
	public void unselectForm2D() 
	{
		this.selectedId = 0;
		this.repaint();
	}
	
	public boolean selectForm2D(Point2D point) 
	{
		boolean sameFormSelected = false;
		for(Forme2D forme : listeFormes)
		{
			if(forme.isInside(point)) 
			{
				if(this.selectedId == forme.getFormID())
					sameFormSelected = true;
				else
					this.selectedId = forme.getFormID();
				break;
			}
		}
		this.repaint();
		return sameFormSelected;
	}
	
	public void deplaceSelectedForme(Point2D newOrigin) 
	{
		for(Forme2D forme : listeFormes)
		{
			if(this.selectedId == forme.getFormID()) 
			{
				forme.setOrigine(newOrigin);
				break;
			}
		}
		this.repaint();
	}
	
	public boolean anySelected() 
	{
		if(this.selectedId != 0)
			return true;
		else
			return false;
	}
	
/** Methodes utiles --------------------------*/
	
	Point2D randomPosition()
	{
		float x = (float) (Math.random() * windowWidith);
		float y = (float) (Math.random() * windowHeight);
		return new Point2D(x, y);
	}

	int randomColor()
	{
		return (int) (Math.random() * 255);
	}

	float randomTaille()
	{
		return (float) (minFormSize + Math.random() * (maxFormSize - minFormSize));
	}
	
}