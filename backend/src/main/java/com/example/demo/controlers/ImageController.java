package com.example.demo.controlers;

import com.example.demo.models.Image;
import com.example.demo.records.responses.ImageResponse;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.services.ImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @GetMapping("/show")
    public List<ImageResponse.ImageResponseRecord> all(){
        return imageService.getALl();
    }

    @GetMapping("/show/{id}")
    public ImageResponse.ImageResponseRecord one(@PathVariable long id){
        return imageService.getOne(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        imageService.delete(id);
    }
}
