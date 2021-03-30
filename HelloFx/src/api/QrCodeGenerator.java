/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;

import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
/**
 *
 * @author aymen
 */
public class QrCodeGenerator {
    public static void generate(String message){
        try {
            
            ByteArrayOutputStream out = QRCode.from(message)
                    .to(ImageType.PNG).stream();
            
            String f_name = "QrCode";
            String Path_name = "C:\\Users\\aymen\\Desktop/" ;
            
            FileOutputStream fout = new FileOutputStream(new File(Path_name +(f_name + ".png")));
            fout.write(out.toByteArray());
            fout.flush();
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
}
