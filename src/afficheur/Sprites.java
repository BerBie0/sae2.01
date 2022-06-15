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

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;



/**
 * distributeur de sprites
 * @author Pierre-Frederic Villard
 */
public abstract class Sprites {
	
	/** l'activite du sprite*/
	protected String activite;
	/* compteur interne */
	protected int iteration;
    /** numero de la frame */
	protected int num;
	String imageFile;
	
    /** lie chaine et sprite */
	public HashMap<String,Sprite> sprites;
	/** Current sprite */
	protected Sprite s;
	/** Image */
	public BufferedImage im;
	Random generator = new Random();
    /**
     * construit le sprite "{@link #activite}+{@link #num}"
     * @return
     */
	public String chaine()
	{
		return(activite+num);
	}
	/**
	 * returns random sprite from HashMap, null if HashMap empty
	 * @return random sprite
	 */
	public Sprite getRandomSprite() {
		
		Object[] values = sprites.values().toArray();
		return (Sprite) values[generator.nextInt(values.length)];
	}

    /**
     * afficheur de sprite
     * @param x position x du sprite
     * @param y position y du sprite
     * @param g
     */
	public void affiche(int x,int y,Graphics g)
	{
		s=sprites.get(chaine());
        //Sprite s=sprites.get("Idle");
		if (s==null) {
			num = 0;
			s = sprites.get(chaine());
			if (s == null) {
				s = getRandomSprite();
			}
		}
		//if (s==null) s=sprites.get("erreur");
		//g.drawImage(s.im, x, y, x+s.tx, y+s.ty, s.xmin, s.ymin, s.xmax, s.ymax,null);
	}
	
    /**
     * permet de changer le type de sprite
     * Ex: "Idle", "Run", "Jump"... 
     * @param activite l'activite du sprite
     */
	public void changeEtape(String activite)
	{
		this.activite=activite;
		//iteration=0;
		//num=0;
	}
	
    /**
     * permet d'animer le sprite
     */
    public abstract void anime();
	
    /**
     * permet de changer l'image
     * @param fileName le nom du fichier
     * @throws IOException
     */
    public void assignNewImage(String fileName) throws IOException
    {
		imageFile = fileName;
    	loadImages();
      //im = ImageIO.read(new File(fileName));
    }

	/**
	 * Loads resources using imageFile
	 * TODO Move method to super class
	 */
	public void loadImages() {
		File fileFolder = new File(imageFile); // directory or file
		listOfFiles(fileFolder);
	}

	/**
	 * recursive
	 * @param string
	 */
	private void listOfFiles(File dirName) {
		File[] filesList = dirName.listFiles();
		
		if(filesList == null) {
			activite = dirName.getName().split("( -?\\d+(\\.\\d+)?|\\.)")[0];
      	 	try {
       	 		im = ImageIO.read(new File(dirName.getAbsolutePath()));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
			sprites.put(chaine(), new Sprite(0, 0, im));
          
           	num++;
           	return;
		}
	    num = 0;
		for(File file : filesList) {
			listOfFiles(file);
		} 	
	}

}
