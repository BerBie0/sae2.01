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
import java.io.IOException;
import java.util.HashMap;
import java.awt.geom.Rectangle2D;

import physique.ObjetMonstre;

/**
 * distributeur de sprites
 * @author Pierre-Frederic Villard
 */
public class SpritesMonstre extends Sprites {

	ObjetMonstre monstre;
	private float FIERCE_X_DRAW_OFFSET = 7;
	private float FIERCE_Y_DRAW_OFFSET = 5;
	private float PINK_STAR_X_DRAW_OFFSET = 4;
	private float PINK_STAR_Y_DRAW_OFFSET = 4;
	private float xDrawOffset = 7*Afficheur.SCALE;
	private float yDrawOffset = 5*Afficheur.SCALE;

    /**
     * constructeur de table de sprites
     * @throws IOException
     */
	public SpritesMonstre(ObjetMonstre monstre) throws IOException
	{
		imageFile="asset/Fierce Tooth";//"monstre.png";
		
		this.monstre = monstre;
		String[] temp = {"fixe","volg","vold","courseDroite","courseGauche"};
		//im=ImageIO.read(new File(imageFile));
		sprites=new HashMap<String, Sprite>();
		loadImages();
		num = 0;
		activite = "Idle";
//		for (int i = 0; i < temp.length; i++) {
//			activite=temp[i];
//			sprites.put(this.chaine(),new Sprite(0,0, im));
//		}
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
		Rectangle2D.Double attackBox = monstre.getAttackBox();
		// regarde la direction du personnage
		if (monstre.vx >= 0) {
			// scales the input image to the output image
		    //affichage normal - monstre va a droite
			//g.drawImage(s.spriteImage,  x + s.tx, y, x, y + s.ty,  s.xmin, s.ymin, s.xmax, s.ymax, null);
			g.drawImage(s.spriteImage, (int) (x + xDrawOffset + monstre.width), (int) (y - yDrawOffset), (int) -(s.tx*Afficheur.SCALE), (int) (s.ty*Afficheur.SCALE), null);
			//g.drawRect((int) (x + monstre.width), y, (int) attackBox.width, (int)attackBox.height);
		}
		else {
			//inverse gauche et droite
			// monstre va a gauche
			//g.drawImage(s.spriteImage, x, y, x + s.tx, y + s.ty, s.xmin, s.ymin, s.xmax, s.ymax, null);
			g.drawImage(s.spriteImage, (int) (x - xDrawOffset), (int) (y - yDrawOffset),(int) (s.spriteImage.getWidth()*Afficheur.SCALE), (int) (s.spriteImage.getHeight()*Afficheur.SCALE), null);
			//g.drawRect((int) (x - monstre.width), y, (int) attackBox.width, (int)attackBox.height);
		}
		g.setColor(Color.RED);
		//hitbox
		//g.drawRect(x, y , (int) monstre.width, (int) monstre.height);
	}
	/**
	 * permet d'animer l'IA (monstres,...)
	 */
	@Override
	public void anime() {
		iteration++;
		if (iteration%12 == 0) {
				num++;
		}	
	}

	
}
