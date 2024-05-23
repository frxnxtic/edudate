package umb.khh.edudate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umb.khh.edudate.entity.Image;
import umb.khh.edudate.repositories.ImageRepository;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    // Добавьте другие методы для работы с изображениями, если необходимо
}
