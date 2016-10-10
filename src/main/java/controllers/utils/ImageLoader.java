package controllers.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by kamilwrobel on 10.10.2016.
 */
public class ImageLoader {

    public static BufferedImage getImage(String name){
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File("resources/images/" + name));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return image;
    }
}
