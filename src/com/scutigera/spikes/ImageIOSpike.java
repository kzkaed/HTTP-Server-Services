package com.scutigera.spikes;


import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

import server.Context;
import server.helpers.Utility;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;



public class ImageIOSpike {    

    ImageIOSpike(){
       
    }
    
    
    void write(){
    	BufferedImage image = null;
        try {

              //use URL or File for reading image using ImageIO
        	String absolutePath = Utility.findServerAbsolutePath();//have a webroot path
        	StringBuilder sb = new StringBuilder();//not synchronized, immutable
        	sb.append(absolutePath);
        	sb.append("/");
        	sb.append(server.constants.Constant.PUBLIC_DIR_DEFAULT);
        	sb.append("/");
        	String webroot = sb.toString();
        	
        	System.out.println(webroot);
        	
            File imagefile = new File(webroot + "image.png");
            image = ImageIO.read(imagefile);

            ImageIO.write(image, "jpg",new File(webroot + "x.jpg"));
            ImageIO.write(image, "bmp",new File(webroot + "y.bmp"));
            ImageIO.write(image, "gif",new File(webroot + "z.gif"));
            ImageIO.write(image, "png",new File(webroot + "t.png"));

        } catch (IOException e) {
              e.printStackTrace();
        }
        System.out.println("Success");
    }
    
    public boolean checkMime(String filename) {
        String filepath = "/the/file/path/image.jpg";
        File f = new File(filepath);
        String mimetype= new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        return type.equals("image");
            
    }

    
}
