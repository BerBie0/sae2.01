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
import java.io.IOException;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;
import controle.Controle;
import afficheur.Afficheur;
import afficheur.Repere;
import afficheur.Sprites;
import afficheur.SpritesHeros;

/**
 * un objet de type balle
 * @author Pierre-Frederic Villard
 */
public class ObjetHeros extends Objet{
	/* valeur par defaut */
	private static final  double VITESSE_X_DEFAUT = 0.5;
	private static final  double VITESSE_Y_DEFAUT = 3;
	private static final  double ACCELERATION_X_DEFAUT = 0;
	private static final  double ACCELERATION_Y_DEFAUT = -0.04;
	private static final  int VIE_DEFAUT = 100;
	/** Permet de savoir la direction d'heros */
	private boolean right = true;
	
	/* distributeur de sprite */
	public Sprites sprites;
	
	/* lien  vers son controleur */
	public Controle c;

	/* la vie de la balle */
	public int vie;

	/** invicibilité du joueur après avoir été touché */
	public int invincible = 0;

	/** tableau de toutes ses anciennes positions */
	public ArrayList<PosAnterieur> posAnt = new ArrayList<>();

	/** boite pour identifier l'attaque hero*/ 
	public Rectangle2D.Double attackBox;
	
    /**
     * fait une balle par defaut
     * @throws IOException
     */
	public ObjetHeros() throws IOException
	{
		px=100;
		py=100;
		sprites=new SpritesHeros(this);
		height=27*Afficheur.SCALE;
		width=20*Afficheur.SCALE;
		vx=VITESSE_X_DEFAUT;
		vy=VITESSE_Y_DEFAUT;
		ax=ACCELERATION_X_DEFAUT;
		ay=ACCELERATION_Y_DEFAUT;
		vie = VIE_DEFAUT;
		attackBox = new Rectangle2D.Double(px, py, width, height);
	}
    /**
     * Permet de creer la balle
     * @param x La position x de la balle
     * @param y La position y de la balle
     * @throws IOException
     */
	public ObjetHeros(int x, int y) throws IOException
	{
		sprites=new SpritesHeros(this);
		height=27*Afficheur.SCALE;
		width=18*Afficheur.SCALE;
                px=x;
                py=y;
        vx=VITESSE_X_DEFAUT;
		vy=VITESSE_Y_DEFAUT;
		ax=ACCELERATION_X_DEFAUT;
		ay=ACCELERATION_Y_DEFAUT;
		vie = VIE_DEFAUT;
		attackBox = new Rectangle2D.Double(px, py, width, height);
	}

	/**
	 * Met a jour la vie du héros
	 * @param i point de vie à enlever ou à ajouter
	 */
	public void updateVie(int i){
		vie = i;
		if(vie<=0) vie = 0;
		if(vie>=100) vie = 100;
	}

    /**
     * Permet de dessiner la balle
     * @param g
     */
    @Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		
		//change de repere
		int[]tab=Repere.changeRepere(this);
		sprites.affiche(tab[0],tab[1],g);
		sprites.anime();
	}

	/**
	 * Ajoute dans la liste de toutes les autres positions anterieures la nouvelle pos. ant.
	 */
	public void sauveAnterieur()
	{
		super.sauveAnterieur();
		posAnt.add(new PosAnterieur(px, py, vx, vy, ax, ay,c.enAir));
		updateAttackBox();
	}

	private void updateAttackBox() {
		if (c.droite){
			right = true;
			attackBox.x = px + width;
		}
		else if (c.gauche){
			attackBox.x = px - width;
			right = false;
		}
		attackBox.y = py;
	}

	public boolean hitEnnemy(ObjetMonstre o){ 
		if(c.attack){
			return attackBox.intersects(o.px, o.py, o.width, o.height);
		}
		return false;
	}


}
