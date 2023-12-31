package economic.main.ultils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import economic.main.constants.TypeFileImage;
import jakarta.servlet.http.HttpServletRequest;

public class ImageUntils {


    public static byte[] compressImage(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] tmp = new byte[4*1024];

        while(!deflater.finished()){
            int size =  deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);

        }

        try {
            outputStream.close();
        } catch (Exception e) {
            System.out.println("in images compressImage untils" + e.getMessage());
        }
        return outputStream.toByteArray();
    }


    public static byte[] decompressImage(byte[] data){
        Inflater inflater = new Inflater();

        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while(!inflater.finished()){
                int conut = inflater.inflate(tmp);
                outputStream.write(tmp, 0, conut);
            }
            outputStream.close();
        } catch (Exception e) {
            System.out.println("in images decompressImage untils" + e.getMessage());
        }

        return outputStream.toByteArray();
    }

    
	

    // public static String getURLImage(String nameImage, TypeFileImage typeFileImage){
    //     HttpServletRequest request = 
    //     ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
    //             .getRequest();

    //     String domain = request.getHeader("Host").contains("http://") ? request.getHeader("Host") : "http://" + request.getHeader("Host");
    //     String prefixImageUrl = domain + "/images/" + typeFileImage.value() + "/";

	// 	return prefixImageUrl + nameImage;
    // }
}
