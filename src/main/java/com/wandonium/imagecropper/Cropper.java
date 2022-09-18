/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wandonium.imagecropper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Futuristic Ltd
 */
public class Cropper {
    
    public static void main(String []args){
        File imageFile = new File("C:/Test/image.jpeg");
        BufferedImage bufferedImage =null;
        try
            {
            bufferedImage = ImageIO.read(imageFile);
            BufferedImage image=bufferedImage.getSubimage(0,0,500,500);
            File pathFile = new File("C:/Test/image-crop.jpeg");
            ImageIO.write(image,"jpeg", pathFile);
            System.out.println("Image cropped successfully. Check it in folder: "
                    + "C:/Test/image-crop.jpeg");
          }
        catch (IOException e) 
            {
                System.out.println(e);
            }    
    }
    
}
