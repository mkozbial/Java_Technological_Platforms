// Rouserces
// https://www.baeldung.com/java-fork-join

package org.example;
import org.example.image_processor.ImageProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String inDirectory = args[0];
        String outDirectory = args[1];
        int numberOfThreads = 8;
        try (Stream<Path> stream = Files.list(Path.of(inDirectory))) {
            List<Path> files = stream.collect(Collectors.toList());
            ImageProcessor processor = new ImageProcessor(numberOfThreads, outDirectory, files, inDirectory);
            ForkJoinPool pool = new ForkJoinPool();
            long startTime = System.currentTimeMillis();
            pool.invoke(processor);
            long endTime = System.currentTimeMillis();
            System.out.println("Time: " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            System.out.println("Error while getting files" + e);
        }
    }
}