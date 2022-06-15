/* ========================================================== */
/*                  Bibliotheque MoteurDeJeu                  */
/* --------------------------------------------               */
/* Bibliotheque pour aider la création de jeu video comme :   */
/* - Jeux de role                                             */
/* - Jeux de plateforme                                       */
/* - Jeux de combat                                           */
/* - Jeux de course                                           */
/* - Ancien jeu d'arcade (Pac-Man, Space Invider, Snake, ...) */
/* ========================================================== */

package afficheur;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import physique.*;

/**
 * Permet d'afficher des objets
 * @author Pierre-Frederic Villard
 */
public class Afficheur extends JPanel {
	/* le monde a affcher */
	public Monde m;
	
	/* l'afficheur de Decor*/
	public DecorVariable decor=new DecorVariable();
	public Level level;
	//public DecorFixe decor=new DecorFixe();

	/* double buffering */
	public BufferStrategy bs;

	// public static final int GAME_WIDTH = 800;
	// public static final int GAME_HEIGHT = 500;
	public static final int TILES_DEFAULT_SIZE = 32;
	public static final float SCALE = 1.5f;
	public static final int TILES_IN_WIDTH = 24;
	public static final int TILES_IN_HEIGHT = 14;
	public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	public static boolean isPaused = false;
	
    /**
     * creation d'un afficheur
     * @param monde
     */
	public Afficheur(Monde monde) {
		JFrame f = new JFrame();
		setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(this);		
		//TODO uncomment below
		//f.setUndecorated(true);

		//ajouter pour eviter les repaint
		f.setIgnoreRepaint(true);
		f.pack();
		//TODO uncomment below
		//f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		//double buffering
		f.createBufferStrategy(2);
	    bs = f.getBufferStrategy();
		this.setIgnoreRepaint(true);
	    
		
	

		this.m = monde;
		level = new Level(m);
	}

    /**
     * permet de faire un affichage
     */
	public void render() {
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();

		g.setColor(Color.black);

		//affiche le decor
		//decor.affiche( g);
		decor.affiche((int)m.balle.px, g);

		// affiche les objets
		for (Objet obj : m.objets) {
			obj.draw(g);
		}
		
		// affiche la balle
		ObjetHeros b = m.balle;
		b.draw(g);

		//affiche les monstres
		for (ObjetMonstre monstre : m.monstres)
		{
			monstre.draw(g);
		}

		g.setColor(Color.green);
		g.fillRect(10, 40, 100, 20);

		g.setColor(Color.red);
		g.fillRect(110-(100-m.balle.vie), 40, 100-m.balle.vie, 20);

		g.setColor(Color.black);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
		g.drawString("Vie : " + m.balle.vie, 130, 57);
		
		bs.show();
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}

	//draw Overlay buttons, Vie, Timer, Pause, Score...
	public void drawButtons(){
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		//TODO A good looking pause menu
		if(m.c.pause){
			g.setColor(new Color(0f,0f,0f,0.0020f));
			g.fillRect(GAME_WIDTH/4, GAME_HEIGHT/4, GAME_WIDTH/2, GAME_HEIGHT/2);

			g.setColor(Color.white);
			Rectangle rect = new Rectangle(GAME_WIDTH, GAME_HEIGHT);
			Font strFont = new Font("TimesRoman", Font.PLAIN, 100);
			g.setFont(strFont);
			FontMetrics metrics = g.getFontMetrics();
			int x = rect.x + (rect.width - metrics.stringWidth("PAUSED")) / 2;
			int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
			
			g.drawString("PAUSED", x, y);
		}
		else{
			//g.drawString("PAUSE", GAME_WIDTH - 150, 57);
		}
		bs.show();
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	/** draw Game Over Overlay */
	public void drawGameOver(){
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		
		g.setColor(new Color(0f,0f,0f,0.0020f));
		g.fillRect(GAME_WIDTH/4, GAME_HEIGHT/4, GAME_WIDTH/2, GAME_HEIGHT/2);

		g.setColor(Color.red);
		Rectangle rect = new Rectangle(GAME_WIDTH, GAME_HEIGHT);
		Font strFont = new Font("TimesRoman", Font.PLAIN, 100);
		g.setFont(strFont);
		FontMetrics metrics = g.getFontMetrics();
		int x = rect.x + (rect.width - metrics.stringWidth("GAME OVER")) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		
		g.drawString("GAME OVER", x, y);

		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		metrics = g.getFontMetrics();
		x = rect.x + (rect.width - metrics.stringWidth("Press 'Q' to Quit")) / 2;
		y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		
		g.drawString("Press 'Q' to Quit" , x, y +100 );
		
		bs.show();
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	/** Win */
	public void drawWin(){
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		
		g.setColor(new Color(0f,0f,0f,0.0020f));
		g.fillRect(GAME_WIDTH/4, GAME_HEIGHT/4, GAME_WIDTH/2, GAME_HEIGHT/2);

		g.setColor(Color.green);
		Rectangle rect = new Rectangle(GAME_WIDTH, GAME_HEIGHT);
		Font strFont = new Font("TimesRoman", Font.PLAIN, 100);
		g.setFont(strFont);
		FontMetrics metrics = g.getFontMetrics();
		int x = rect.x + (rect.width - metrics.stringWidth("You Win!")) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		
		g.drawString("You Win!", x, y);
		
		bs.show();
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
}
