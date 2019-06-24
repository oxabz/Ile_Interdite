package Project.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageBuffer {
    private static HashMap<String, BufferedImage> bufferedImages = new HashMap<>();

    public static BufferedImage getImage(String src) throws IOException {
        if(bufferedImages.containsKey(src)){
            return bufferedImages.get(src);
        }else {
            try {
                BufferedImage image = ImageIO.read(new File(src));
                bufferedImages.put(src,image);
                return image;
            } catch (IOException e) {
                System.err.println("Erreur chargement chergement de l'image "+src);
                throw e;
            }

        }
    }
}
