package com.example.demo.tool;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class TikaUtil {

    private static final Tika tika = new Tika();
    static String PATH = "C:/Users/Administrator/Pictures/Saved Pictures/64735764.txt";

    static String getType(File file) throws IOException {
        return tika.detect(file);
    }

    public static void main(String[] args) throws TikaException, IOException, SAXException {
//        System.out.println("==> " + getType(PATH));

        File folder = new File("E:/qiyuan_work/files/zlist");
        File[] array = folder.listFiles();
        assert array != null;
        for(File str: array) {
            System.out.println( str.getName()+ ": " + getType(str));
        }
    }


}
