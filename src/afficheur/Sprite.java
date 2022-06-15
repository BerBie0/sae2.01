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

import java.awt.image.BufferedImage;

/**
 * modele d'un sprite
 * @author Pierre-Frederic Villard
 */
public class Sprite {

	/** xmin du spite */
	int xmin;
	/** xmax du spite */
	int xmax;
	/** ymin du spite */
	int ymin;
	/** ymax du spite */
	int ymax;

    /** taille largeur (width) du sprite */
    public int tx;

    /** taille hauteur (height) du sprite */
    public int ty;
    
    /** Image */
	public BufferedImage spriteImage;
	
    /**
     * constructeur d'un sprite a partir des 4 points d'extremite
     * @param xmin
     * @param ymin 
     * @param xmax
     * @param ymax
     */
    public Sprite(int xmin, int ymin, int xmax, int ymax) {
		super();
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
		this.tx=xmax-xmin;
		this.ty=ymax-ymin;
		
	}
    
    public Sprite(int xmin, int ymin, BufferedImage im) {
		super();
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = im.getWidth();
		this.ymax = im.getHeight();
		this.tx=xmax-xmin;
		this.ty=ymax-ymin;
		this.spriteImage = im;
	}
    
}
