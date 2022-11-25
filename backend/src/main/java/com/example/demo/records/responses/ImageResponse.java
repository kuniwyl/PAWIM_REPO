package com.example.demo.records.responses;

import com.example.demo.models.Image;

public class ImageResponse {
    public record ImageResponseRecord (
            long id,
            String location,
            String name
    ){};

    public ImageResponse(){}

    public ImageResponseRecord create(Image image){
        return new ImageResponseRecord(
                image.getId(),
                image.getLocation(),
                image.getName()
        );
    }

}
