import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.Comparable;

public abstract class Forme2D implements Comparable<Forme2D>{
	private Point origine;
	private int hauteur;
	private int largeur;
	private Color couleur;
	
	private final static int HAUTEUR_PAR_DEFAUT = 100;
	private final static int LARGEUR_PAR_DEFAUT = 200;
	private final static Point ORIGINE_PAR_DEFAUT = new Point(50,50);
	private final static Color COULEUR_PAR_DEFAUT = Color.BLACK;
	
	private static int nombreFormes2D = 0;
	
	public Forme2D(Point p, int largeur, int hauteur, Color couleur)throws DimensionException{
		setHauteur(hauteur);
		setLargeur(largeur);
		setCouleur(couleur);
		setOrigine(p);
		nombreFormes2D++;
	}
	
	public Forme2D(Point p, int largeur, int hauteur)throws DimensionException{
		this(p, largeur, hauteur, COULEUR_PAR_DEFAUT);
	}
	
	
	public Forme2D(int largeur, int hauteur, Color couleur)throws DimensionException{
		this(ORIGINE_PAR_DEFAUT, largeur, hauteur,couleur);
	}

	public Forme2D(int largeur, int hauteur)throws DimensionException{
		this(ORIGINE_PAR_DEFAUT,largeur,hauteur,COULEUR_PAR_DEFAUT);		
	}
	
	public Forme2D(Point p, Color couleur){
		setOrigine(p);
		setCouleur(couleur);
		hauteur = HAUTEUR_PAR_DEFAUT;
		largeur = LARGEUR_PAR_DEFAUT;
		nombreFormes2D++;
	}
	
	public Forme2D(Point p){
		this(p, COULEUR_PAR_DEFAUT);
	}

	public Forme2D(Color couleur){
		this(ORIGINE_PAR_DEFAUT, couleur);
	}
		
	public Forme2D(){
		this(ORIGINE_PAR_DEFAUT);
	}
	
	public Point getOrigine(){
		return origine;
	}
	
	public void setOrigine(Point p){
		origine = new Point(p.x,p.y);
	}
	
	public int getHauteur(){
		return hauteur;
	}
	
	public void setHauteur(int hauteur)throws DimensionException{
		if(hauteur < 0) throw new DimensionException();
		this.hauteur = hauteur;
	}
	
	public int getLargeur(){
		return largeur;
	}
	
	public void setLargeur(int largeur)throws DimensionException{
		if (hauteur < 0) throw new DimensionException();
		this.largeur = largeur;
	}
	
	public Color getCouleur(){
		return couleur;
	}
	
	public void setCouleur(Color couleur){
		this.couleur = couleur;
	}
	
	public int compareTo(Forme2D f){	
		if (this.surface() < f.surface())
			return -1;
		else if(this.surface() > f.surface())
			return 1;
		return 0;		
	}
	
	public void translate(int dx, int dy){
		origine.x +=dx;
		origine.y +=dy;
	}
	
	public abstract double surface();
	
	public abstract void reDimensionner(int dx, int dy) throws DimensionException;
	
	public abstract void dessiner(Graphics g);
	
	public String toString(){
		return "Forme2D de couleur "+getCouleur().toString()+", de surface "+surface();
	}
	
	
public static int nombreFormes2D(){
		return nombreFormes2D;
	}
	
	protected void finalize() throws Throwable{
		nombreFormes2D--;
	}
}
