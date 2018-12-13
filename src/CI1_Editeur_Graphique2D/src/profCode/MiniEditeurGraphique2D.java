import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MiniEditeurGraphique2D extends JFrame implements ComponentListener{

	FeuilleDeDessin c;
	boolean peindre = false;
	boolean effacer = true;
	int x=0;
	int y=0;
	int epaisseur=10;
	ArrayList<MouseEvent> l;

	public MiniEditeurGraphique2D(){

		c = new FeuilleDeDessin();
		c.setSize(this.getWidth(),this.getHeight());
		l=new ArrayList<MouseEvent>();
		addComponentListener(this);
		add(c);

	}

	
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		System.out.println("Resizing.....");
		peindre = true;
		effacer = false;
		c.repaint();
	}
	
	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
	class FeuilleDeDessin extends JPanel implements MouseListener, MouseMotionListener{
		
		public FeuilleDeDessin(){
			addMouseMotionListener(this);
			addMouseListener(this);
			setFocusable(true);
			
		}
		
		public void paint(Graphics g){
			System.out.println(effacer+" "+peindre);
			if (effacer == true && peindre==false){ super.paint(g); }
			if (peindre == true && effacer==false)
				for(MouseEvent e: l){
					x= e.getX();
					y= e.getY();
					System.out.println("repainting stuff...");
					g.fillOval(x, y, epaisseur, epaisseur);
				}
		}



		@Override
		public void mouseDragged(MouseEvent arg0) {
			l.add(arg0);
			repaint();

		}
		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			
				peindre=false;
				effacer=true;
				l.clear();
				repaint();
			
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			l.add(arg0);
			peindre=true;
			effacer=false;
			repaint();
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			peindre=false;
			effacer=false;


		}

	}
	
	public static void main(String args[]){
		MiniEditeurGraphique2D mg = new MiniEditeurGraphique2D();
		mg.setSize(150,150);
		mg.setVisible(true);
	}
}
