package userinterface;

import domain_model.Controller;
import domain_model.members.Member;

import java.util.Scanner;

public class Userinterface {

    Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);

    public void startProgram() {
        int choice = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("\nUser\n").append("1. Administrator\n" +
                "2. Accountant\n" +
                "3. Coach\n" +
                "4. Close program\n" +
                "Enter your credentials: ");
        System.out.print(sb);


        choice = Input.scannerInt(scanner, 1, 4);

        switch (choice) {
            case 1 -> administratorProgram();
            case 2 -> accountantProgram();
            case 3 -> coachProgram();
            case 4 -> System.out.println("Program ended");

        }
    }

    public void administratorProgram() {
        int administratorChosenOption;

        do {
            System.out.print("\nAdministrator - Options\n" +
                    "1. Add new member\n" +
                    "2. Show list of members\n" +
                    "3. Edit member information(not valid yet)\n" +
                    "4. Delete member(not valid yet)\n" +
                    "5. Sign out\n" +
                    "Choice: ");

            administratorChosenOption = Input.scannerInt(scanner, 1, 5);

            switch (administratorChosenOption) {
                case 1 -> addNewMember();
                case 2 -> showListOfMembers();
//                case 3 -> editMemberInformation(); //TODO - add edit
//                case 4 -> deleteMember(); //TODO - add delete
                case 5 -> {
                    System.out.println("Signing out...");
                    defaultScreen();
                }
            }
        } while (administratorChosenOption != 5);
    }

    public void showListOfMembers() {
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

        int memberType = Input.scannerInt(scanner, 1, 5);


        System.out.print("Name: ");

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

        String memberDiscipline = "none";
        if (memberType == 5) {
            System.out.print("Discipline: \n1. Backstroke\n2. Breaststroke\n3. Butterfly\n4. Crawl\nChoice: ");

            memberDiscipline = switch (Input.scannerInt(scanner, 1, 4)) {
                case 1 -> "Backstroke";
                case 2 -> "Breaststroke";
                case 3 -> "Butterfly";
                case 4 -> "Crawl";
                default -> "None";
            };
        }


        //TODO - make automated
        System.out.print("Subscription value: ");
        double memberSubscriptionValue = Input.scannerPositiveDouble(scanner);


        try {
            if (controller.addMemberToList(memberType, memberName, memberBirthDate, memberEmail, memberDiscipline, memberSubscriptionValue)) {
                System.out.println(memberName + " added to list of members");
            } else System.out.println("Something went wrong - Error: 401");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void defaultScreen() {
        startProgram();
    }

    public void accountantProgram() {
        int accountantChosenOption;

        do {
            System.out.print("\nAccountant - Options\n" +
                    "1. Show list of subscriptions\n" +
                    "2. Show income forecast\n" +
                    "3. Sign out\n" +
                    "Choice: ");

            accountantChosenOption = Input.scannerInt(scanner, 1, 3);

            switch (accountantChosenOption) {
                case 1 -> showSubscriptionList();
                case 2 -> showIncomeForecast();
                case 3 -> {
                    System.out.println("Signing out...");
                    defaultScreen();
                }
            }
        } while (accountantChosenOption != 3);
    }

    public void showSubscriptionList() {
        System.out.println(controller.showListOfSubscriptions());
    }
    public void showIncomeForecast() {
        System.out.println("\nExpected 1 year revenue: " + controller.showIncomeForecast() + "kr.");
    }


    public void coachProgram() {
        int coachChosenOption;

        do {
            System.out.print("""
                    Coach - Options
                    1. Show Junior Team
                    2. Show Senior Team
                    3. Add New Result (unfinished)
                    4. Sign Out
                    Choice:""");

            coachChosenOption = Input.scannerInt(scanner,1,4);

            switch (coachChosenOption){
                case 1 -> {
                    String juniorTeam = "\nJunior Team:";
                    for (Member member: controller.getJuniorTeam()) {
                        juniorTeam += "\n"+member.getName()+" - "+member.getEmail()+" - "+member.getDiscipline();
                    }
                    System.out.println(juniorTeam+"\n");
                }
                case 2 -> {
                    String seniorTeam = "\nSenior Team:";
                    for (Member member: controller.getSeniorTeam()) {
                        seniorTeam += "\n"+member.getName()+" - "+member.getEmail()+" - "+member.getDiscipline();
                    }
                    System.out.println(seniorTeam+"\n");
                }
                case 3 -> {}
                case 4 -> {
                    System.out.println("Signing out...");
                    defaultScreen();
                }
            }
        }
        while (coachChosenOption != 4);
    }
}
