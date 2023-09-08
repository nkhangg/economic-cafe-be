package economic.main.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import economic.main.constants.TypeFileImage;
import economic.main.service.ImageService;

@Controller
@RequestMapping("/images")
public class ImagesComtroller {

    @Autowired
    private ImageService imageService;
    
    @GetMapping("/{typeFile}/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName,@PathVariable String typeFile) throws IOException{
        byte[] imageData = imageService.downloadImage(fileName, typeFile);

        return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.valueOf("image/png"))
        .body(imageData);
    }
}
