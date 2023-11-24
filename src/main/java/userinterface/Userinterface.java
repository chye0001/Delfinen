package userinterface;

import domain_model.Controller;

import java.util.Scanner;

public class Userinterface {

    Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);

    public void startProgram() {
        int choise = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("\nUser\n").append("1. Administrator\n" +
                "2. Accountant\n" +
                "3. Coach\n" +
                "4. Close program\n" +
                "Enter your credentials: ");
        System.out.print(sb);

        while (!scanner.hasNextInt()) {
            System.out.println("You must enter a number from the menu: ");
            scanner.nextLine();
        }
        choise = scanner.nextInt();

        switch (choise) {
            case 1 -> administratorProgram();
            case 2 -> accountantProgram();
            case 3 -> coachProgram();
            case 4 -> System.out.println("Program ended");
            default -> {
                System.out.println("You must enter a number from the menu");
                startProgram();
            }
        }
    }

    public void administratorProgram() {
        int administratorChosenOption = 0;

        while (administratorChosenOption != 4) {
            System.out.print("\nOptions\n" +
                    "1. Add new member\n" +
                    "2. Show list of members\n" +
                    "3. Edit member information(not valid yet)\n" +
                    "4. Delete member(not valid yet)\n" +
                    "5. Sign out\n" +
                    "Choice: ");

            administratorChosenOption = scanner.nextInt();

            switch (administratorChosenOption) {
                case 1 -> addNewMember();
                case 2 -> showListOfMembers(); //TODO - add showList
//                case 3 -> editMemberInformation(); //TODO - add edit
//                case 4 -> deleteMember(); //TODO - add delete
                case 5 -> {
                    System.out.println("Signing out...");
                    defaultScreen();
                }
                default -> {
                    System.out.println("you must enter a number from the menu.");
                    administratorProgram();
                }
            }
        }
    }

    public void showListOfMembers(){
        System.out.println(controller.showListOfMembers());
    }

    public void addNewMember() {

        StringBuilder sb = new StringBuilder();
        sb.append("\nMember Type:\n").
                append("1. Passiv\n").
                append("2. Junior Member\n").
                append("3. Senior Member\n").
                append("4. Exercise Member\n").
                append("5. Competitive Member\n").
                append("Type: ");
        System.out.print(sb);
        //TODO try-catch
        int memberType = scanner.nextInt();


        System.out.print("Name: ");
        scanner.nextLine();
        String memberName = scanner.nextLine();


        System.out.print("Birth date: ");
        String memberBirthDate = scanner.nextLine();
        while (!memberBirthDate.contains("/")) {
            System.out.print("\nPlease enter birthdate using the following format: day/month/year\nBirth date: ");
            memberBirthDate = scanner.nextLine();
        }


        System.out.print("Email: ");
        String memberEmail = scanner.nextLine();
        while (!memberEmail.contains("@") && !memberEmail.contains(".")) {
            System.out.print("Please enter a valid email address\nEmail: ");
            memberEmail = scanner.nextLine();
        }

        String memberDiscipline = "None";
        if (memberType == 4){
            System.out.print("Discipline: ");
            memberDiscipline = scanner.nextLine().toLowerCase();
        }


        //TODO - make automated
        System.out.print("Subscription value: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("\nYou muster enter a number.\nSubscription value: ");
            scanner.nextLine();
        }
        double memberSubscriptionValue = scanner.nextDouble();


        try {
            if (controller.addMemberToList(memberType, memberName, memberBirthDate, memberEmail, memberDiscipline, memberSubscriptionValue)) {
                System.out.println(memberName + " added to list of members");
            } else System.out.println("Something went wrong - Error: 401");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void defaultScreen(){
        startProgram();
    }

    public void accountantProgram() {

    }

    public void coachProgram() {

    }
}
