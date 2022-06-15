package afficheur;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
//distributeur de sprites
public class DecorVariable {
	
	//IMage
	BufferedImage im = null;
	
	//taille
	int imW,imH;
	
	//construit le sprite
	public DecorVariable() 
	{
		im = LoadBufferedImage.loadImage("background.jpg");
		imW = im.getWidth();
		imH = im.getHeight();
	}

    public void changeImage(String image) 
	{
		im = LoadBufferedImage.loadImage(image);
	}

	//afficheur de decor
	//decale en x seulement
	public void affiche(int x,Graphics g)
	{
        if (Repere.isSubjective)
        {
			//on se ramene au repere du plan
			x=(x%imW);
			
			if (x>0){
				
				//on affiche sur l'ecran 0 ==> wx-x  image source de x � wx 
				//bug quand on va à gauche
				g.drawImage(im, 
							0, 0, (int) ((imW-x)*Afficheur.SCALE),(int) (imH*Afficheur.SCALE), 
							x, 0, imW, imH,
							null);
				
				//on affiche sur l'ecran wx-x ==> wx  image source de 0 � x   
				g.drawImage(im, 
							(int)((imW-x)*Afficheur.SCALE), 0, (int) (imW*Afficheur.SCALE), (int)(imH*Afficheur.SCALE), 
							0, 0, x, imH,
							null);
			}
			else{
				//personnage va a gauche	
			}	
		}else{
			g.drawImage(im,0 ,0 , imW, imH, 0, 0, imW, imH,null);
		}	
	}
}
