package com.example.demo;

import com.example.demo.models.Image;
import com.example.demo.records.responses.ImageResponse;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.services.ImageService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ImageTests {
    @Mock
    private ImageRepository repository;

    @InjectMocks
    private ImageService controller;

    private ImageResponse imageResponse = new ImageResponse();

    @Test
    public void get_all_Zdjecies_from_repository_data_in_repo(){
        // arange
        repository = mock(ImageRepository.class);
        controller = new ImageService(repository);
        List<Image> zdjecies = new ArrayList<>();
        zdjecies.add(new Image());
        zdjecies.add(new Image());
        when(repository.findAll()).thenReturn(zdjecies);
        List<ImageResponse.ImageResponseRecord> imageResponseRecords =
                zdjecies.stream().map(zdjecie -> imageResponse.create(zdjecie)).toList();

        // act
        List<ImageResponse.ImageResponseRecord> result = controller.getALl();

        // assert
        Assert.assertEquals(imageResponseRecords, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void get_all_Zdjecies_from_repository_without_data(){
        // arange
        repository = mock(ImageRepository.class);
        controller = new ImageService(repository);
        List<Image> zdjecies = new ArrayList<>();
        when(repository.findAll()).thenReturn(zdjecies);
        List<ImageResponse.ImageResponseRecord> imageResponseRecords =
                zdjecies.stream().map(zdjecie -> imageResponse.create(zdjecie)).toList();

        // act
        List<ImageResponse.ImageResponseRecord> result = controller.getALl();

        // assert
        Assert.assertEquals(imageResponseRecords, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void get_one_Zdjecie_from_repository(){
        // arange
        repository = mock(ImageRepository.class);
        controller = new ImageService(repository);
        Image image = new Image();
        when(repository.findById(0L)).thenReturn(Optional.of(image));
        ImageResponse.ImageResponseRecord expected = imageResponse.create(image);

        // act
        ImageResponse.ImageResponseRecord result = controller.getOne(0L);

        // assert
        Assert.assertEquals(expected, result);
        verify(repository, times(1)).findById(0L);
    }

    @Test
    public void delete_zdjecie() {
        // arange
        repository = mock(ImageRepository.class);
        controller = new ImageService(repository);
        Image image = new Image();
        image.setId(0L);
        when(repository.findById(image.getId())).thenReturn(Optional.of(image));

        // act
        controller.delete(image.getId());

        // assert
        verify(repository, times(1)).deleteById(image.getId());
    }

    @Test
    public void delete_not_existing_id() {
        // arange
        repository = mock(ImageRepository.class);
        controller = new ImageService(repository);
        when(repository.findById(1L)).thenReturn(null);

        // act
        controller.delete(1L);

        // assert
        verify(repository, times(1)).deleteById(1L);
    }


}
