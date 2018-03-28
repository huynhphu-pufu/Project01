/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project01;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author huynh
 */
public class Project01 {

    /**
     * @param args the command line arguments
     */
    public static void join(String inputPath, String outputPath, int size) throws IOException {
        File directory = new File(inputPath);
        File[] files = directory.listFiles();

        BufferedImage sprite = ImageIO.read(files[0]);

        int maxWidthObj = sprite.getWidth();
        int maxHeightObj = sprite.getHeight();

        for (File file : files) {
            BufferedImage bufferedImage = ImageIO.read(file);
            if (bufferedImage.getWidth() > maxWidthObj) {
                maxWidthObj = bufferedImage.getWidth();
            }
            if (bufferedImage.getHeight() > maxHeightObj) {
                maxHeightObj = bufferedImage.getHeight();
            }
        }

        int width = maxWidthObj * size;
        int height = maxHeightObj * size;
        BufferedImage spritemap = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = spritemap.createGraphics();

        int x = 0;
        int y = 0;
        int i = 0;
        for (File file : files) {
            sprite = ImageIO.read(file);
            g2d.drawImage(sprite, null, x, y);
            x += maxWidthObj;
            i++;
            if (i % size == 0) {
                y += maxHeightObj;
                x = 0;
            }
            if (i > size * size) {
                break;
            }
        }

        ImageIO.write(spritemap, "png", new File(outputPath));
    }

    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Project01.join("C:\\Users\\huynh\\Desktop\\Project01\\src\\project01\\input", "C:\\Users\\huynh\\Desktop\\Project01\\src\\project01\\output\\output.png", 2);
        } catch (IOException ex) {
            Logger.getLogger(Project01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
