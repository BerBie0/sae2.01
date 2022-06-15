package afficheur;
import java.io.IOException;

import physique.Monde;
import physique.ObjetLava;

import java.awt.Color;

public class Level extends DecorVariable {
    Monde m;

    public Level(Monde m){
        this.m = m;
        im = LoadBufferedImage.loadImage("asset/LevelTwo.png");
        //construct objects
        //FIXME Uncomment
        //LoadLevel();
    }

    //afficheur de decor
	//decale en x seulement
	public void LoadLevel(){
		int w = im.getWidth();
		int h = im.getHeight();
		
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				Color color = new Color(im.getRGB(i, j));
				int pixelRed = color.getRed();
				if(pixelRed == 0){
                    m.addMur(i*32 - Repere.VIEW_OFFSET, j*32 - Repere.VIEW_OFFSET, Afficheur.TILES_SIZE, Afficheur.TILES_SIZE);
                }
                if(pixelRed == 1){
                    try{
                        m.addMonstre(0, 0, i*32 - Repere.VIEW_OFFSET, j*32 - Repere.VIEW_OFFSET);
                    }catch(IOException e){
                        e.printStackTrace();
                        System.out.println("Error addMonstre"); 
                    }

                }
                if (pixelRed == 50) {
                    ObjetLava o = new ObjetLava(i*32 - Repere.VIEW_OFFSET, j*32 - Repere.VIEW_OFFSET, Afficheur.TILES_DEFAULT_SIZE, Afficheur.TILES_SIZE);
                    m.addObjet(o);
                }
			}
		}
	}
}
