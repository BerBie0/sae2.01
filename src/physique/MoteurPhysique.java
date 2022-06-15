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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import static main.JeuPhysique.*;
/**
 * permet de gerer la physique
 * @author Pierre-Frederic Villard
 */
public class MoteurPhysique {
	/** la liste des objets dans le monde */
	public Monde monde;
	/** permet de gerer gravity */
	public boolean gravity = true;
	/** valeur par défaut */
	public float gravityValue = -0.04f;
	/** identifie le monstre courant  */
	public int current_monster_index = 0;
	/** identifie le mur */
	public int current_wall_index = 0;
	/** permet de savoir si le jeu est termine */
	public boolean isGameOver = false;
	public boolean playerWin = false;
	/**
	 * Construit un moteur par defaut
	 * 
	 * @throws IOException
	 */
	public MoteurPhysique() {
	}

	/**
	 * met a jour le monde
	 */
	public void update() {
		monde.balle.collision = 0;

		// mise a jour des objets
		for (Objet o : monde.objets) {
			o.update();
			o.collision = 0;
		}

		// mise a jour des monstres
		// for (ObjetMonstre monstre : monde.monstres) {
		// monstre.evolue();
		// if (monde.balle.collision==MONSTRE)
		// {
		// monde.balle.collision=MONSTRE;
		// if (monde.balle.vie - monstre.DEGAT > 0 && monde.balle.invincible == 0){
		// //monde.balle.updateVie(monstre.DEGAT);
		// monde.balle.invincible = 200;
		// }
		// else if (monde.balle.invincible > 0) monde.balle.invincible --;
		// else ControleurClavier.fin = true;
		// current_monster_index=monstre.index;
		// }
		// if (Collision.collision(monde.balle, monstre) && monde.balle.c.attack){
		// monde.monstres.remove(monstre);
		// }
		// }
		
		if (monde.monstres.isEmpty()) {
			playerWin = true;
		}
		Iterator<ObjetMonstre> monstreIterator = monde.monstres.iterator();
		while(monstreIterator.hasNext()) {
			ObjetMonstre monstre = monstreIterator.next();
			monstre.evolue();
			if (monde.balle.collision == MONSTRE) {
				monde.balle.collision = MONSTRE;
				if (monde.balle.vie - monstre.DEGAT > 0 && monde.balle.invincible == 0) {
					monde.balle.updateVie(monde.balle.vie - monstre.DEGAT);
					monde.balle.invincible = 700;
				} else if (monde.balle.invincible > 0)
					monde.balle.invincible--;
				else{
					monde.balle.sprites.changeEtape("Dead Ground");
					isGameOver = true;
				}
				current_monster_index = monstre.index;
			}
			if (monde.balle.hitEnnemy(monstre)) {
				monstreIterator.remove();
			}
		}

		if (monde.balle.invincible > 0)
			monde.balle.invincible--;

		// gestion du controleur
		testControl();

		// gestion des sauts
		testGravity();

		// mise a jour de la balle
		// test pour savoir si le heros est tombe
		if (!(monde.balle.py < -115)) {
			monde.balle.update();
			if (monde.balle.vie <= 0)
				isGameOver = true;
		}
		else { 	// sinon on remonte jusqu'a la derniere position ou le heros etait sur un mur
			monde.balle.updateVie(monde.balle.vie - 33);
			monde.balle.invincible = 700;
			if (monde.balle.vie <= 0){
				monde.balle.sprites.changeEtape("Dead Ground");
				isGameOver = true;
				return;
			}
			int i = monde.balle.posAnt.size() - 1;
			while (monde.balle.posAnt.get(i).air || monde.balle.posAnt.get(i).ovy != -1) {
				i--;
			}
			monde.balle.py = monde.balle.posAnt.get(i).opy;
			monde.balle.px = monde.balle.posAnt.get(i).opx;
			monde.balle.vy = 0;
			monde.balle.vx = 0;
			monde.balle.posAnt.clear();
		}

		// test de collision pour chaque mur, objets
		testCollision();
		
		// Assign the last collision type if not a monster
		if (monde.balle.collision == 0)
			monde.balle.collision = Collision.typeOfCollision;

	}
	/** gestion du controleur */
	public void testControl(){
		if (monde.c.droite && monde.c.gauche) {
			monde.balle.sprites.changeEtape("Idle Sword");
			return;
		} else if (monde.c.gauche) {
			if (!monde.c.enAir) {
				monde.balle.sprites.changeEtape("Run Sword");
			}
			monde.balle.ax = -0.1;
			if (monde.balle.vx < -2)
				monde.balle.vx = -2;

		} else if (monde.c.droite) {
			if (!monde.c.enAir) {
				monde.balle.sprites.changeEtape("Run Sword");
			}
			monde.balle.ax = 0.1;
			if (monde.balle.vx > 2)
				monde.balle.vx = 2;

		} else if (monde.c.attack) {
			if (!monde.c.enAir) {
				monde.balle.sprites.changeEtape("Attack");
			}
			if (monde.balle.vx < 0)
				monde.balle.vx = -0.00000000000000001;
			if (monde.balle.vx >= 0)
				monde.balle.vx = 0;
		} else {
			if ((monde.balle.vx < 0.2) && (monde.balle.vx > -0.2)) {
				// direction de vue de la balle
				if (monde.balle.vx < 0) monde.balle.vx = -0.00000000000000001;
				if (monde.balle.vx > 0) monde.balle.vx = 0;
				monde.balle.ax = 0;
				if (!monde.c.enAir) monde.balle.sprites.changeEtape("Idle Sword");
			} else if (monde.balle.vx > 0)
		monde.balle.ax = -0.1;
			else if (monde.balle.vx < 0)
				monde.balle.ax = +0.1;

		}
	}

	/** Gestion de gravity/sauts */
	private void testGravity() {
		if (gravity) {
			if ((monde.c.haut) && (!monde.c.enAir)) {
				monde.balle.sprites.changeEtape("Jump Sword");
				monde.balle.vy = 3;
				monde.balle.ay = gravityValue;
				monde.c.enAir = true;
			}
			if (monde.balle.vy < 0 && monde.c.enAir)
				monde.balle.sprites.changeEtape("Fall Sword");
		} else {
			monde.balle.ay = 0;
			monde.balle.vy = 0;
			if (monde.c.haut) {
				monde.balle.vy = 1;
			}
			if (monde.c.bas) {
				monde.balle.vy = -1;
			}
		}
	}

	/** Gestion de collision de la balle avec des objets */
	private void testCollision() {
		Iterator<Objet> objIterator = monde.objets.iterator();
		while (objIterator.hasNext()) {
			// mur, potion, item,...
			Objet obj = objIterator.next();

			if (Collision.collision(monde.balle, obj)) {
				// si collision potion
				if (Collision.typeOfCollision == POTION) {
					ObjetPotion potion = (ObjetPotion) obj;
					monde.balle.updateVie(monde.balle.vie + potion.value);
					objIterator.remove();
					return;
				}
				// si collision vient du haut
				if (Collision.collisionHaut(monde.balle, obj)) {
					monde.balle.py = monde.balle.py - monde.balle.vy;
					monde.balle.vy = -1;
					if (monde.c.enAir) {
						monde.c.enAir = false;
						monde.balle.sprites.changeEtape("Fall Sword");
					} else {
						// balle sur un mur(sol)
						//monde.balle.posAnt.clear();
					}
				}
				// si collision vient du Bas
				if (Collision.collisionBas(monde.balle, obj)) {

					monde.balle.py = monde.balle.py - monde.balle.vy;
					monde.balle.vy = -0.7;
				}
				// si collision vient de la gauche ou droite
				if (Collision.collisionGauche(monde.balle, obj) || (Collision.collisionDroite(monde.balle, obj))) {
					monde.balle.px = monde.balle.opx; // - monde.balle.vx;
					// monde.balle.vx-=monde.balle.ax;
					if (monde.balle.vx < 0)
						monde.balle.vx = -0.00000000000000001;
					if (monde.balle.vx > 0)
						monde.balle.vx = 0;
				}
				current_wall_index = obj.index;
			}
		}
	}
}
