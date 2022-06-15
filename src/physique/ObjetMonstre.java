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
import java.awt.geom.Rectangle2D;
import afficheur.Afficheur;
import afficheur.Repere;
import afficheur.SpritesMonstre;

/**
 * gere l'IA (monstres)
 * @author Pierre-Frederic Villard
 */

public class ObjetMonstre extends Objet {

	/** etat interne 
	 *  TODO Etat - IDLE
	 * */  
	enum Etat {
		PROMENE, ATTAQUE, COLLISION, IDLE;
	}
	/** distributeur de sprites */
	SpritesMonstre sprite;

	/** son etat */
	Etat etat = Etat.PROMENE;

	/** lien vers le monde */
	Monde m;

	// Dégâts des monstres
	public int DEGAT = 33;

	public static final double VITESSE_MONSTRE_DEFAUT = 0.4;

	/** boite pour identifier l'attaque monstre*/ 
	private Rectangle2D.Double attackBox;
 
	/**
     * constructeur d'un objet monstre par defaut
     * @throws IOException
     */
	public ObjetMonstre() throws IOException {
		sprite=new SpritesMonstre(this);
		// depart
		ax = 0;
		ay = 0;
		px = 200;
		py = 0;
		vx = VITESSE_MONSTRE_DEFAUT;
		vy = 0;
		//hitbox fierce tooth
		height=22*Afficheur.SCALE;
		width=22*Afficheur.SCALE;
		attackBox = new Rectangle2D.Double(px, py, width, height);
		
	}
	/**
	 * constructeur d'un objet monstre au coordonnees (x,y)
	 * @param x 
	 * @param y
	 */
	public ObjetMonstre(int x , int y){
		px = x;
		py = y;
		height=22*Afficheur.SCALE;
		width=22*Afficheur.SCALE;
		attackBox = new Rectangle2D.Double(px, py, width, height);
	}

    /**
     * dessine un monstre
     * @param g
     */
	@Override
	public void draw(Graphics g) {
		
		// change de repere
		int[] tab = Repere.changeRepere(this);
		
		if (etat == Etat.ATTAQUE)
			g.setColor(Color.red);
		if (etat == Etat.PROMENE)
			g.setColor(Color.green);
		if (etat == Etat.COLLISION)
			g.setColor(Color.blue);

		//g.fillOval(tab[0], tab[1], tab[2], tab[3]);	
		//g.setColor(Color.black);
		//g.fillOval(tab[0]+3, tab[1]+5, 5, 5);
		//g.fillOval(tab[0]+12, 5+tab[1], 5, 5);
		//g.drawLine(tab[0]+ 8, 15 + tab[1], tab[0]+12, 15 + tab[1]);
		
		sprite.affiche(tab[0],tab[1],g);
		sprite.anime();
	}

    /**
     * gere le comportement de monstre
     */
    public void evolue() {
		// en fonction de l'etat interne
		switch (etat) {
			// si se promene
			case PROMENE:
				// mise a jour
				update();
				// si rencontre un mur, change vitesse
				boolean collision = false;
				for (Objet objet : m.objets) {
					// si collision
					if (Collision.collision(this, objet)) {
						collision = true;
						break;
					}
				}
				// si collision inverse vitesse
				if (collision) {
					px = px - vx;
					vx = -vx;
				}
				sprite.changeEtape("Run");
				// test si passe en attaque
				int n = 0;
				double diffY = py - (m.balle.py + m.balle.width);
				if ((px - (m.balle.px + m.balle.width) < 50) && (px + width - m.balle.px  > -50)) {
					if (diffY  < 150 && (diffY > -150)) {
						sprite.changeEtape("Anticipation");
						n++;
						if (n > 10) {
							n = 0;
							etat = Etat.IDLE;
						}
					}
					if (((px - m.balle.px < 10) && (px - m.balle.px > -10)) && ((py - m.balle.py < 10) && (py - m.balle.py > -10))) {
						etat = Etat.ATTAQUE;
						sprite.changeEtape("Attack");
					}
				}

				break;

			case ATTAQUE:
				// chaneg direction
				if (px < m.balle.px) {
					vx = 0.2;
					if (ovx != vx) {
						sprite.changeEtape("Attack");
					}
				} else {
					vx = -0.2;
					if (ovx != vx) {
						sprite.changeEtape("Attack");
					}
				}
				// update
				update();
				// si rencontre un mur, change vitesse
				collision = false;
				for (Objet objet : m.objets) {
					// si collision
					if (Collision.collision(this, objet)) {
						collision = true;
						break;
					}
				}
				// si collision inverse vitesse
				if (collision) {
					px = px - vx;
				}

				// si loin, promene
				if ((px - m.balle.px > 150) || (px - m.balle.px < -150)) {
					etat = Etat.PROMENE;
					vx = VITESSE_MONSTRE_DEFAUT;
				}

				break;
			case COLLISION:
				if (Collision.collision(m.balle, this))
					etat = Etat.ATTAQUE;
				break;
			case IDLE:
				sprite.changeEtape("Idle");
				vx = 0;
				// test si passe en promene
				if ((px - m.balle.px > 100) || (px - m.balle.px < -100)) {
					etat = Etat.PROMENE;
					vx = VITESSE_MONSTRE_DEFAUT;
					break;
				}
				
				break;
		}
		if (Collision.collision(m.balle, this) && etat != Etat.ATTAQUE)
			etat = Etat.COLLISION;
	}

	/**
	 * @return the attackBox
	 */
	public Rectangle2D.Double getAttackBox() {
		return attackBox;
	}
	/**
	 * @param attackBox the attackBox to set
	 */
	public void setAttackBox(Rectangle2D.Double attackBox) {
		this.attackBox = attackBox;
	}
}
