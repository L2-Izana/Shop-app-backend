package com.project.shopapp.services.helper.file_management;

import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.models.product_management.CategoryThumbnail;
import com.project.shopapp.repositories.product_management.CategoryThumbnailRepository;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class ImageManipulator {

    public static byte[] resizeImage(byte[] imageData, String imageType) throws IOException {
        int targetWidth, targetHeight;
        if (Objects.equals(imageType, "thumbnail")) {
            targetWidth = 100;
            targetHeight = 100;
        }
        else {
            targetWidth = 300;
            targetHeight = 300;
        }
        ByteArrayInputStream inputImageDataStream = new ByteArrayInputStream(imageData);
        BufferedImage originalImage = ImageIO.read(inputImageDataStream);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        ByteArrayOutputStream outputImageDataStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", outputImageDataStream);
        return outputImageDataStream.toByteArray();
    }
}
