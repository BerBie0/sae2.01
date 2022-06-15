package physique;

import java.awt.Color;
import java.awt.Graphics;

import afficheur.Repere;

/** Just Testing, object that burns hero when touched, aka lava */
public class ObjetLava extends ObjetMur {
    
    public ObjetLava(){
        super();
    }

    public ObjetLava(int x, int y, int w, int h){
        super(x, y, w, h);
    }

    /**
     * surcharge l'affichage
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
		int[] tab = Repere.changeRepere(this);
		g.fillRect(tab[0], tab[1], tab[2], tab[3]);
	}
    
}
