package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReading {
    private static final Logger logger = Logger.getLogger(FileReading.class.getName());

    public static void main(String[] args) {
        String inputFileName = "output.txt"; // Предполагаем, что используем файл сгенерированный FileWriting
        String outputFileName = "sums.txt";

        // Вычисление сумм и запись в файл
        calculateAndWriteSums(inputFileName, outputFileName);
    }

    private static void calculateAndWriteSums(String inputFileName, String outputFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             FileWriter writer = new FileWriter(outputFileName)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");
                int[] row = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
                int sum = Arrays.stream(row).sum();
                writer.write(sum + "\n");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Ошибка при обработке файла", e);
        }
    }
}
