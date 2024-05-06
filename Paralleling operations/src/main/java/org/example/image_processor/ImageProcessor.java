// Resources
// https://www.baeldung.com/java-8-streams
// https://www.tutorialspoint.com/java_dip/java_buffered_image.htm
// https://www.baeldung.com/java-nio-2-file-api
// https://docs.oracle.com/javase/8/docs/api/javax/imageio/ImageIO.html
// https://www.baeldung.com/java-commons-lang-3

package org.example.image_processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import org.apache.commons.lang3.tuple.Pair;

public class ImageProcessor extends RecursiveAction {
    private final int filePortion;
    private final String outDirectory;
    private final String inDirectory;
    private final List<Path> files;


    public ImageProcessor(int filePortion, String outDirectory, List<Path> files, String inDirectory) {
        this.filePortion = filePortion;
        this.outDirectory = outDirectory;
        this.inDirectory = inDirectory;
        this.files = files;
    }

    private void processImages() {

        // parallelStream() on a collection enables processing of elements in parallel
        files.parallelStream()
            .map(path -> Pair.of(path.getFileName().toString(), readImage(path))) // creates pair <filename, image>
            .map(pair -> Pair.of(pair.getLeft(), processImage(pair.getRight()))) // process each image
            .forEach(pair -> saveImage(pair.getRight(), outDirectory, pair.getLeft())); // saves processed images
    }

    private static void saveImage(BufferedImage image, String outDirectory, String filename) {
        try {
            Files.createDirectories(Path.of(outDirectory));
            ImageIO.write(image, "jpg", new File(outDirectory + "/processed_" + filename ));
        } catch (IOException e) {
            System.out.println("Error while saving image: " + e);
            return;
        }
    }

    private static BufferedImage readImage(Path path) {
        try {
            return ImageIO.read(path.toFile());
        } catch (IOException e) {
            System.out.println("Error while reading image: " + path.toFile());
            return null;
        }
    }

    private static BufferedImage processImage(BufferedImage image) {
        if (image == null) {
            System.out.println("There is no image");
            return null;
        }
        int imgHeight = image.getHeight();
        int imgWidth = image.getWidth();
        int imgType = image.getType();

        // creates the image that will be processed
        BufferedImage processedImage = new BufferedImage(imgWidth, imgHeight, imgType);

        for (int i = 0; i < imgWidth; i++) {
            for(int j = 0; j < imgHeight; j++) {
                // RBG to BGR
                int rgb = image.getRGB(i, j);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                int newRgb = (green << 16) | (blue << 8) | red;
                processedImage.setRGB(i, j, newRgb);
            }
        }

        return processedImage;
    }

    @Override
    public void compute() {
        if (files.size() <= filePortion) {
            processImages();
        } else {
            int middle = files.size() / 2;
            List<Path> left = files.subList(0, middle);
            List<Path> right = files.subList(middle, files.size());

            ImageProcessor leftTask = new ImageProcessor(filePortion, outDirectory, left, inDirectory);
            ImageProcessor rightTask = new ImageProcessor(filePortion, outDirectory, right, inDirectory);

            invokeAll(leftTask, rightTask);
        }
    }

}
