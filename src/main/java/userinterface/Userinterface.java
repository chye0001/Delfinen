package userinterface;

import domain_model.Controller;
import domain_model.Result;
import domain_model.members.Member;

import java.util.Scanner;

public class Userinterface {

    Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);

    public void startProgram() {
        //extract method.
        int choice;
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
            case 4 -> System.out.println("Shutting down...");

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
        //TODO: extract method
        StringBuilder sb = new StringBuilder();
        sb.append("\nMember Type:\n").
                append("1. Passiv\n").
                append("2. Exercise Member\n").
                append("3. Competitive Member\n").
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

        String memberDiscipline = "None";
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

        //TODO - Fix this portion, because I dont think it makes sense (Kristoffer)
        try {
            if (controller.addMemberToList(memberType, memberName, memberBirthDate,
                    memberEmail, memberDiscipline, memberSubscriptionValue)) {
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
                    3. Add New Result
                    4. Sign Out
                    Choice:""");

            coachChosenOption = Input.scannerInt(scanner,1,4);

            switch (coachChosenOption){

                case 1 -> {
                    String juniorTeam = "\nJunior Team:\n--------------------";

                    for (Member member: controller.getJuniorTeam().getMembers()) {
                        juniorTeam += "\n"+member.getName()+" - "+member.getEmail()+" - "+member.getDiscipline()+
                                "\nLeaderboard Results:";

                        for (Result result : controller.getJuniorTeam().getLeaderboard()) {
                            if(result.getMemberEmail().equals(member.getEmail())){
                                juniorTeam += "\n- "+result.getTime()+"s - "+result.getDate();
                            }
                        }
                        juniorTeam += "\n--------------------";
                    }

                    System.out.println(juniorTeam);
                }
                case 2 -> {
                    String seniorTeam = "\nSenior Team:\n--------------------";

                    for (Member member: controller.getSeniorTeam().getMembers()) {
                        seniorTeam += "\n"+member.getName()+" - "+member.getEmail()+" - "+member.getDiscipline()+
                                      "\nLeaderboard Results:";

                        for (Result result : controller.getSeniorTeam().getLeaderboard()) {
                            if(result.getMemberEmail().equals(member.getEmail())){
                                seniorTeam += "\n- "+result.getTime()+"s - "+result.getDate();
                            }
                        }
                        seniorTeam += "\n--------------------";
                    }

                    System.out.println(seniorTeam);
                }
                case 3 -> {
                    System.out.println("Adding new result:");
                    System.out.print("Enter member email: ");
                    String email = scanner.next();
                    System.out.print("Enter time in seconds: ");
                    double time = Input.scannerPositiveDouble(scanner);
                    controller.addResultToTeam(email,time);
                }
                case 4 -> {
                    System.out.println("Signing out...");
                    defaultScreen();
                }
            }
        }
        while (coachChosenOption != 4);
    }
}
