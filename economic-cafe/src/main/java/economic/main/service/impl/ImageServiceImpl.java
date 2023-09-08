package economic.main.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Service;

import economic.main.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

    @Override
    public byte[] downloadImage(String fileName, String type) throws IOException {
        String filePath = "images/" + type + "/" + fileName;

        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
    
}
