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

package physique;

import java.awt.Color;
import java.awt.Graphics;

import afficheur.Repere;

/**
 * permet de modeliser un objet
 * @author Pierre-Frederic Villard
 */
public class Objet {
	// modele de l'objet

    /** position x de l'objet */
	public  double px = 0;

    /** position y de l'objet */
    public double py = 0;

    /** vitesse de l'objet */
	public double vx = 0;
	/** vitesse de l'objet */
	public double vy = 0;
	/* acceleration */
	public double ax = 0;
	/* acceleration */
	public double ay = 0;
	
	
	//positions precedentes (
	//utile pour colision

    /** position x precedente */
	public double opx;

	/** position y precedente */
	public double opy;

    /** vitesse x precedente */
	public double ovx;
    
    /** vitesse y precedente */
	public double ovy;

	/** acceleration x precedente */
	public double oax;
	
	/** acceleration y precedente */
	public double oay;

	// boundingbox

    /** Boundingbox :
     * largeur boite objet -
     * Cette boite identifie un objet 
     * */
	public double width = 10;

	 /** Boundingbox : 
	  * Cette boite identifie un objet
	  * hauteur boite objet*/
    public double height = 10;
	
	//collision
	//permet de savoir si un objet est en collision
	//TODO debug a supprimer

    /** collision: permet de savoir si un objet est en collision */
	public int collision=0;

	/* Permet de sauver les positions anterieures
	 * Index de l'element parmis son type (Monstre , Mur, etc...) */   
    public int index;
        
    /**
     * Sauve les positions anterieures
     */
	public void sauveAnterieur()
	{
		opx=px;
		opy=py;
		ovx=vx;
		ovy=vy;
		oax=ax;
		oay=ay;
	}
	
    /**
     * mise a jour avec des equations physiques
     */
	public void update() {
		sauveAnterieur();		
		px = px + vx;
		py = py + vy;
		vx = vx + ax;
		vy = vy + ay;
	}
    /**
     * permet de dessiner l'objet
     * @param g
     */
	public void draw(Graphics g)
	{
		g.setColor(Color.green);
		int[]tab=Repere.changeRepere(this);
		g.fillRect(tab[0], tab[1], tab[2], tab[3]);
	}

    /**
     * Renvoie la position x de l'objet
     * @return
     */
    public double getPx() {
		return px;
	}

    /**
     * Renvoie la position y de l'objet
     * @return
     */
    public double getPy() {
		return py;
	}

}
