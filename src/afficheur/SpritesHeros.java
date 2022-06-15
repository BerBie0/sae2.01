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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import physique.ObjetHeros;

/**
 * Distributeur de sprites
 * @author Pierre-Frederic Villard
 */
public class SpritesHeros extends Sprites {

	ObjetHeros heros;
        BufferedImage image;
	
	private float CAPTAIN_X_DRAW_OFFSET = 21;
	private float CAPTAIN_Y_DRAW_OFFSET = 4;
	private float xDrawOffset = 21 *Afficheur.SCALE;
	private float yDrawOffset = 4 *Afficheur.SCALE;

    /**
     * constructeur de table de sprites
     * @param b
     * @throws IOException
     */
	public SpritesHeros(ObjetHeros b) throws IOException {
		imageFile = "asset/Captain Clown Nose"; //"hero.png";

		this.heros = b;
		sprites = new HashMap<String, Sprite>();
		//num = 0;
		loadImages();
		activite = "Idle";
		num = 0;
	}

	/**
	 *  afficheur de sprite
	 *  @param x position de la balle
	 *  @param y position de la balle
	 *  @param g 
	 */
	@Override
	public void affiche(int x, int y, Graphics g) {
		super.affiche(x, y, g);
		// regarde la direction du personnage
		g.setColor(Color.red);
		if (heros.vx >= 0) {    
			//affichage normal
			g.drawImage(s.spriteImage, (int) (x - xDrawOffset), (int) (y - yDrawOffset), (int) (s.tx*Afficheur.SCALE),(int) (s.ty*Afficheur.SCALE), null);

			//attackbox
			//g.drawRect((int)(x + heros.width), y, (int) heros.attackBox.width, (int) heros.attackBox.height);
		}
		else {
			//inverse gauche et droite
			g.drawImage(s.spriteImage,
			(int) (x + xDrawOffset + heros.width), (int) (y - yDrawOffset), -(int)(s.tx*Afficheur.SCALE), (int) (s.ty*Afficheur.SCALE), null);

			//attackbox
			//g.drawRect((int)(x - heros.width), y, (int) heros.attackBox.width, (int) heros.attackBox.height);
		}
		//g.drawRect(x, y, s.spriteImage.getWidth(), s.spriteImage.getHeight());
		g.setColor(Color.black);
		//hitbox
		//g.drawRect(x, y , (int) heros.width, (int) heros.height);
	}

	/** animation de la balle */
    @Override
	public void anime() {
		iteration++;
		if(sprites.get(chaine()) == null) {
			num = 0;
		}
		if(iteration%10 == 0) {
			if (activite.contains("Idle")) {
				num++;
			}
		}
		if (activite.contains("Run")) {
			if (iteration %15 == 0) {
				num++;
			}
		
			//if (num > 9)
				//num = 0;
		}
		
		if ( activite.contains("Jump")) {
			if (iteration %20 == 0) {
				num++;
			}
		}

		if ( activite.contains("Attack")) {
			if (iteration %20 == 0) {
				num++;
			}
		}
	}
}
