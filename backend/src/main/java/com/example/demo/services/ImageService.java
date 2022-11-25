package com.example.demo.services;

import com.example.demo.models.Image;
import com.example.demo.models.Product;
import com.example.demo.records.responses.ImageResponse;
import com.example.demo.repositories.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private ImageRepository repository;

    private ImageResponse imageResponse = new ImageResponse();

    public ImageService(ImageRepository imageRepository){
        this.repository = imageRepository;
    }

    public List<ImageResponse.ImageResponseRecord> getALl(){
        List<Image> images = repository.findAll();
        return images.stream().map(image -> imageResponse.create(image)).toList();
    }

    public ImageResponse.ImageResponseRecord getOne(long id){
        return imageResponse.create(repository.findById(id).get());
    }

    public void delete(long id){
        repository.deleteById(id);
    }
}

