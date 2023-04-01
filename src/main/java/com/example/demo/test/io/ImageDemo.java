package com.example.demo.test.io;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author raining_heavily
 * @date 2023/3/21 11:34
 */
public class ImageDemo {

    public static void addWatermark(BufferedImage image) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String watermark = dateFormat.format(date);
        int stringWidth = g2d.getFontMetrics().stringWidth(watermark);
        int x = image.getWidth() - stringWidth - 10;
        int y = image.getHeight() - 20;
        g2d.drawString(watermark, x, y);
        g2d.dispose();
    }

    public static void main(String[] args) throws IOException {

        File file = new File("C:/Users/Administrator/Pictures/微信图片_20220124103542.jpg");
        BufferedImage image = ImageIO.read(file);
        addWatermark(image);
        ImageIO.write(image,"jpg",new File(file.getParentFile(),"new_file.jpg"));
    }
}
