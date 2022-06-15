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

//un objet de type mur

/**
 *
 * @author Pierre-Frederic Villard
 */
public class ObjetMur extends Objet {

    /**
     * constructeur d'un mur par defaut
     */
    public ObjetMur() {
		// taille de mur differente
		height = 50;
		width = 50;
		px = 100;
		py = 20;
		sauveAnterieur();
	}

    /**
     * constructeur d'un objet mur
     * @param x position x du mur
     * @param y position y du mur
     * @param w largeur (width) du mur
     * @param h hauteur (height) du mur
     */
    public ObjetMur(int x, int y, int w, int h) {
		// taille de mur differente
		height = h;
		width = w;
		px = x;
		py = y;
	}
	
    /**
     * permet de dessiner un mur
     * @param g
     */
    public void draw(Graphics g) {
		Color myColor= new Color(255,140,40);
		if (collision==1)
			g.setColor(Color.red);
		else
			g.setColor(myColor);
		int[] tab =Repere.changeRepere(this);
		g.fillRect(tab[0], tab[1], tab[2], tab[3]);
	}

}
