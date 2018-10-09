package cn.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Encoder;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/27
 * Time 12:49
 */
public class UploadImageFile {
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public static void SaveImg(String path,MultipartFile image, int uid) throws IOException, NoSuchAlgorithmException {
        File imageFolder= new File(path);
        if(!imageFolder.exists())//如果这个目录还不存在，则先创建
            imageFolder.mkdirs();
        File file = new File(imageFolder,uid+".jpg");//创建一个jpg文件
        image.transferTo(file);//上传的图片数据传递给文件
        BufferedImage img = change2jpg(file);//转换为jpg二进制流
        ImageIO.write(img, "jpg", file);//写入数据
    }
    /**
     * 转换为jpg格式
     * @date 2018/3/2 18:10
     * @param [f]
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage change2jpg(File f) {
        try {
            Image i = Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
            PixelGrabber pg = new PixelGrabber(i, 0, 0, -1, -1, true);
            pg.grabPixels();
            int width = pg.getWidth(), height = pg.getHeight();
            final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF };
            final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            BufferedImage img = new BufferedImage(RGB_OPAQUE, raster, false, null);
            return img;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 改变图片大小
     * @date 2018/3/2 18:14
     * @param [srcFile, width, height, destFile]
     * @return void
     */
    public static void resizeImage(File srcFile, int width,int height, File destFile) {
        try {
            if(!destFile.getParentFile().exists())
                destFile.getParentFile().mkdirs();
            Image i = ImageIO.read(srcFile);
            i = resizeImage(i, width, height);
            ImageIO.write((RenderedImage) i, "jpg", destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 改变图片大小，直接返回改后图片
     * @date 2018/3/2 18:15
     * @param [srcImage, width, height]
     * @return java.awt.Image
     */
    public static Image resizeImage(Image srcImage, int width, int height) {
        try {

            BufferedImage buffImg = null;
            buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            buffImg.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            return buffImg;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
