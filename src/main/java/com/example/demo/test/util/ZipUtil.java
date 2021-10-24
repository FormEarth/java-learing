package com.example.demo.test.util;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class ZipUtil {

    /**
     * 解压tar.gz 文件
     *
     * @param file      要解压的tar.gz文件对象
     * @param outputDir 要解压到某个指定的目录下
     * @throws IOException
     */
    public static void unzipTarGz(String file, String outputDir) throws IOException {
        TarInputStream tarIn = null;
        try {
            tarIn = new TarInputStream(new GZIPInputStream(
                    new BufferedInputStream(new FileInputStream(file))),
                    1024 * 2);

            createDirectory(outputDir, null);//创建输出目录

            TarEntry entry;
            while ((entry = tarIn.getNextEntry()) != null) {

                if (entry.isDirectory()) {//是目录
                    createDirectory(outputDir, entry.getName());//创建目录
                } else {//是文件
                    File tmpFile = new File(outputDir + "/" + entry.getName());
                    tmpFile.createNewFile();//创建
                    OutputStream out = null;
                    try {
                        out = new FileOutputStream(tmpFile);
                        int length = 0;
                        byte[] b = new byte[2048];
                        while ((length = tarIn.read(b)) != -1) {
                            out.write(b, 0, length);
                        }
                    } catch (IOException ex) {
                        throw ex;
                    } finally {
                        if (out != null)
                            out.close();
                    }
                }
            }
        } catch (IOException ex) {
            throw new IOException("解压归档文件出现异常", ex);
        } finally {
            try {
                if (tarIn != null) {
                    tarIn.close();
                }
            } catch (IOException ex) {
                throw new IOException("关闭tarFile出现异常", ex);
            }
        }
    }

    /**
     * 构建目录
     * @param outputDir
     * @param subDir
     */
    public static void createDirectory(String outputDir,String subDir){
        File file = new File(outputDir);
        if(!(subDir == null || subDir.trim().equals(""))){//子目录不为空
            file = new File(outputDir + "/" + subDir);
        }
        if(!file.exists()){
            if(!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            file.mkdirs();
        }
    }

    public static void main(String[] args) throws IOException {
        ZipUtil.unzipTarGz("F:/INS_CLAIM_REQUEST_DBBX_KCXB_20300714.tar.gz","F:/");
        System.out.println("done!");
    }

}