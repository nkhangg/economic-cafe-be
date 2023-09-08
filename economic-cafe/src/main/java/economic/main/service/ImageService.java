package economic.main.service;

import java.io.IOException;

import economic.main.constants.TypeFileImage;

public interface ImageService {
   byte[] downloadImage(String fileName, String type) throws IOException;
}
