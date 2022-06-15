package afficheur;

import java.awt.Graphics;
import java.util.HashMap;
import physique.ObjetPotion;

public class SpritesItem extends Sprites {


    public SpritesItem(ObjetPotion item){ //ObjetItem 
        imageFile="asset/Green Bottle";
        //this.item = item;
		//im=ImageIO.read(new File(imageFile));
		sprites=new HashMap<String, Sprite>();
		loadImages();
		num = 0;
		activite = "Idle";
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
        g.drawImage(s.spriteImage, x, y, (int) (s.tx*Afficheur.SCALE),(int) (s.ty*Afficheur.SCALE), null);
	}

    @Override
    public void anime() {
        iteration++;
        if(iteration%10==0){
			num++;
			if(num>7)
				num = 0;
		}
    }
    
}
