package example;

import java.util.Scanner;
import java.util.Arrays;

public class Sum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split(" ");

        // The long but simple way
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }
        System.out.println("The sum is " + sum);

        // using streams!
        System.out.println("The sum is " + Arrays.asList(numbers).stream()
                                                  .mapToInt(Integer::parseInt)
                                                  .sum());
        scanner.close();
    }
}
