package org.example;
import java.io.FileWriter;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileWriting {
    private static final Logger logger = Logger.getLogger(FileWriting.class.getName());
    private static final Random random = new Random();

    public static void main(String[] args) {
        int rowCount = generateRandomNumber(10, 100);
        int[][] randomArray = generateRandomArray(rowCount);

        // Запись в файл
        writeArrayToFile(randomArray);
    }

    private static int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private static int[][] generateRandomArray(int rowCount) {
        return IntStream.range(0, rowCount)
                .mapToObj(i -> IntStream.generate(() -> generateRandomNumber(1, 100)).limit(generateRandomNumber(1, 10)).toArray())
                .toArray(int[][]::new);
    }

    private static void writeArrayToFile(int[][] array) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            for (int[] row : array) {
                String line = IntStream.of(row).mapToObj(Integer::toString).collect(Collectors.joining(" "));
                writer.write(line + "\n");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Ошибка при записи в файл", e);
        }
    }
}