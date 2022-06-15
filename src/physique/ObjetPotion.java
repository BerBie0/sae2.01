package physique;

import java.awt.Graphics;
import java.awt.Color;

import afficheur.Afficheur;
import afficheur.Repere;
import afficheur.Sprites;
import afficheur.SpritesItem;

public class ObjetPotion extends Objet{
    /** Valeur de 1 a 100*/
    public int value = 50;
    /** distributeur de sprite */
	public Sprites sprites;
    
    public ObjetPotion(int x, int y){
        this.px = x;
        this.py = y;
        sprites= new SpritesItem(this);
        height = 15*Afficheur.SCALE;
        width = 15*Afficheur.SCALE;
    }

    /**
     * Permet de dessiner la Potion
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
}
