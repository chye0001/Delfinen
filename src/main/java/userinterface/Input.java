package userinterface;


import java.util.Scanner;

public class Input {


    public static int scannerInt(Scanner scanner) { // Input skal være en integer
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Undgå scanner bug

                return input; // Hvis input er en integer
            } catch (java.util.InputMismatchException e) { // hvis input ikke er en integer
                System.out.println(Farve.ANSI_RED + "Input must be a whole number" + Farve.ANSI_RESET);
                scanner.nextLine(); // Undgå scanner bug
            }
        }
    }

    public static int scannerInt(Scanner scanner, int min, int max) { // Input skal være en integer med min. og maks. værdi:
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Undgå scanner bug

                if (input >= min && input <= max) return input; // Hvis input er en integer
                else System.out.println(Farve.ANSI_RED + "Input must be a whole number between " + min + " and " + max + Farve.ANSI_RESET);
            } catch (java.util.InputMismatchException e) { // hvis input ikke er en integer
                System.out.println(Farve.ANSI_RED + "Input must be a whole number" + Farve.ANSI_RESET);
                scanner.nextLine(); // Undgå scanner bug
            }
        }
    }

    public static int scannerPositiveInt(Scanner scanner) { // Input skal være en positiv integer
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Undgå scanner bug

                if (input >= 0) return input; // Hvis input er en positiv integer
                else System.out.println(Farve.ANSI_RED + "Input must be a whole number equal to 0 or above" + Farve.ANSI_RESET);
            } catch (java.util.InputMismatchException e) { // hvis input ikke er en integer
                System.out.println(Farve.ANSI_RED + "Input must be a whole number equal to 0 or above" + Farve.ANSI_RESET);
                scanner.nextLine(); // Undgå scanner bug
            }
        }
    }



    public static double scannerDouble(Scanner scanner) { // Input skal være en double
        while (true) {
            try {
                double input = scanner.nextDouble();
                scanner.nextLine(); // Undgå scanner bug

                return input; // Hvis input er en double
            } catch (java.util.InputMismatchException e) { // hvis input ikke er en double
                System.out.println(Farve.ANSI_RED + "Input must be a number" + Farve.ANSI_RESET);
                scanner.nextLine(); // Undgå scanner bug
            }
        }
    }

    public static double scannerDouble(Scanner scanner, int min, int max) { // Input skal være en integer med min. og maks. værdi:
        while (true) {
            try {
                double input = scanner.nextDouble();
                scanner.nextLine(); // Undgå scanner bug

                if (input >= min && input <= max) return input; // Hvis input er en double mellem min og max
                else System.out.println(Farve.ANSI_RED + "Input must be a number between " + min + " and " + max + Farve.ANSI_RESET);
            } catch (java.util.InputMismatchException e) { // hvis input ikke er en double
                System.out.println(Farve.ANSI_RED + "Input must be a number" + Farve.ANSI_RESET);
                scanner.nextLine(); // Undgå scanner bug
            }
        }
    }

    public static double scannerPositiveDouble(Scanner scanner) { // Input skal være en positiv double
        while (true) {
            try {
                double input = scanner.nextDouble();
                scanner.nextLine(); // Undgå scanner bug
                if (input >= 0) return input; // Hvis input er en positiv double
                else System.out.println(Farve.ANSI_RED + "Input must be a number equal to 0 or above" + Farve.ANSI_RESET);
            } catch (java.util.InputMismatchException e) { // hvis input ikke er en double
                System.out.println(Farve.ANSI_RED + "Input must be a number equal to 0 or above" + Farve.ANSI_RESET);
                scanner.nextLine(); // Undgå scanner bug
            }
        }
    }



    public static boolean scannerBoolean(Scanner scanner) { // Input skal være en boolean
        System.out.print(Farve.ANSI_GREEN + "Y" + Farve.ANSI_RESET + "es or " + Farve.ANSI_GREEN + "N" + Farve.ANSI_RESET + "o: \n");
        while (true) {
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println(Farve.ANSI_RED + "Input must be \"Y\" or \"N\"" + Farve.ANSI_RESET);
            }
        }
    }

    public static String scannerDate (Scanner scanner) { // Regex stjålet fra nettet
        while (true) {
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.matches("^(?:(?:31(-)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(-)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(-)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(-)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$"))
                return input;
            else {
                System.out.println(Farve.ANSI_RED + "Input must be a date using the following format: DD-MM-YYYY" + Farve.ANSI_RESET);
            }
        }
    }

    public static String scannerEmail (Scanner scanner) { // Regex stjålet fra nettet
        while (true) {

            String input = scanner.nextLine().toLowerCase().trim();
            if (input.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
                return input;
            else {
                System.out.println(Farve.ANSI_RED + "Invalid email-address" + Farve.ANSI_RESET);
            }
        }
    }
}
