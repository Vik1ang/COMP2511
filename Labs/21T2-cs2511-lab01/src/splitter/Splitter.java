package splitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Splitter {

    public static void main(String[] args) {
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        for (String s : inputArray) {
            System.out.println(s);
        }
    }
}
