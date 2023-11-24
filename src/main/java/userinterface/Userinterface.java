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
                    "Choise: ");

            administratorChosenOption = scanner.nextInt();

            switch (administratorChosenOption) {
                case 1 -> addNewMember();
                case 2 -> System.out.println(controller.showListOfMembers());
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

    public void addNewMember() {

        StringBuilder sb = new StringBuilder();
        sb.append("Member Type: ").
                append("1. Passiv").
                append("2. Junior Member").
                append("3. Senior Member").
                append("4. Exercise Member").
                append("5. Competitive Member").
                append("Type: ");
        System.out.println(sb);
        int memberType = scanner.nextInt();
        while (!scanner.hasNextInt()){
            System.out.println("Not a valid member type. Please enter a valid type.\nMember type: ");
            scanner.nextLine();
        }

        System.out.print("\nName: ");
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
