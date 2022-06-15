package afficheur;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadBufferedImage {
    public static BufferedImage img = null;

    public static BufferedImage loadImage(String filename) {
		try {
			img=ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("erreur lecture fichier");
		}
		return img;
	}
}
