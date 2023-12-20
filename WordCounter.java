import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {
        // Step 1: Prompt user for input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a text or provide a file path to count words:");
        String userInput = scanner.nextLine();

        // Step 2: Read input text or file
        String content = readInput(userInput);

        if (content != null) {
            // Step 3: Split the string into an array of words
            String[] words = content.split("[\\s\\p{Punct}]+");

            // Step 4: Initialize counter variable
            int wordCount = 0;

            // Step 5: Iterate through the array of words and count
            for (String word : words) {
                wordCount++;
            }

            // Step 6: Display the total count of words to the user
            System.out.println("Total number of words: " + wordCount);
        }
    }

    private static String readInput(String userInput) {
        String content = null;

        try {
            // Check if the user input is a file path
            File file = new File(userInput);
            if (file.isFile()) {
                // Read content from file
                content = new String(Files.readAllBytes(file.toPath()));
            } else {
                // Treat input as plain text
                content = userInput;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return content;
    }
}
