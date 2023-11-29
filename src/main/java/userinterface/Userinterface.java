package userinterface;

import domain_model.Controller;
import domain_model.Result;
import domain_model.members.Member;

import java.time.LocalDate;
import java.util.Scanner;

public class Userinterface {

    Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);


    public void startProgram() {
        buildMenuForStartProgram();
        menuOptionsForStartProgram();
    }

    public void buildMenuForStartProgram() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nUser\n").append("1. Administrator\n" +
                "2. Accountant\n" +
                "3. Coach\n" +
                "4. Close program\n" +
                "Enter your credentials: ");
        System.out.print(sb);
    }

    public void menuOptionsForStartProgram() {
        int choice;
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
            buildMenuForAdministratorProgram();
            administratorChosenOption = menuOptionsForAdministratorProgram();

        } while (administratorChosenOption != 5);
    }

    public void buildMenuForAdministratorProgram() {
        System.out.print("\nAdministrator - Options\n" +
                "1. Add new member\n" +
                "2. Show list of members\n" +
                "3. Edit member information(not valid yet)\n" +
                "4. Delete member(not valid yet)\n" +
                "5. Sign out\n" +
                "Choice: ");
    }

    public int menuOptionsForAdministratorProgram() {
        int administratorChosenOption;
        administratorChosenOption = Input.scannerInt(scanner, 1, 5);

        switch (administratorChosenOption) {
            case 1 -> addNewMember();
            case 2 -> showListOfMembers();
//                case 3 -> editMemberInformation(); //TODO - add edit
//                case 4 -> deleteMember(); //TODO - add delete
            case 5 -> signOut();
        }
        return administratorChosenOption;
    }

    public void showListOfMembers() {
        System.out.println(controller.showListOfMembers());
    }

    public void addNewMember() {
        buildMenuForAddMember();

        int memberType = Input.scannerInt(scanner, 1, 5);

        System.out.print("Name: ");

        String memberName = scanner.nextLine();


        System.out.print("Birth date in YYYY-MM-DD: ");
        String memberBirthDate = scanner.nextLine();
        memberBirthDate = errorHandlingBirthDateFormat(memberBirthDate);

        System.out.print("Email: ");
        String memberEmail = scanner.nextLine();
        memberEmail = errorHandlingEmailFormat(memberEmail);

        String memberDiscipline = "None";
        if (memberType == 5) {
            System.out.print("Discipline: \n1. Backstroke\n2. Breaststroke\n3. Butterfly\n4. Crawl\nChoice: ");

            memberDiscipline = buildMenuMemberDisciplin();
        }


        //TODO - make automated
        System.out.print("Subscription value: ");
        double memberSubscriptionValue = Input.scannerPositiveDouble(scanner);

        try {
            controller.addMemberToList(memberType,
                    memberName,
                    LocalDate.parse(memberBirthDate),
                    memberEmail,
                    memberDiscipline,
                    memberSubscriptionValue);

            System.out.println(memberName + " added to list of members");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildMenuForAddMember() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMember Type:\n").
                append("1. Passiv\n").
                append("2. Junior Member\n").
                append("3. Senior Member\n").
                append("4. Exercise Member\n").
                append("5. Competitive Member\n").
                append("Type: ");
        System.out.print(sb);
    }

    public String errorHandlingBirthDateFormat(String memberBirthDate) {
        while (!memberBirthDate.contains("/")) {
            System.out.print("\nPlease enter birthdate using the following format: day/month/year\nBirth date: ");
            memberBirthDate = scanner.nextLine();
        }
        return memberBirthDate;
    }

    public String errorHandlingEmailFormat(String memberEmail) {
        while (!memberEmail.contains("@") && !memberEmail.contains(".")) {
            System.out.print("Please enter a valid email address\nEmail: ");
            memberEmail = scanner.nextLine();
        }
        return memberEmail;
    }

    public String buildMenuMemberDisciplin() {
        return switch (Input.scannerInt(scanner, 1, 4)) {
            case 1 -> "Backstroke";
            case 2 -> "Breaststroke";
            case 3 -> "Butterfly";
            case 4 -> "Crawl";
            default -> "None";
        };
    }

    public void defaultScreen() {
        startProgram();
    }

    public void accountantProgram() {
        int accountantChosenOption;

        do {
            buildMenuForAccountantProgram();
            accountantChosenOption = menuOptionsForAccountantProgram();

        } while (accountantChosenOption != 3);
    }

    public void buildMenuForAccountantProgram() {
        System.out.print("\nAccountant - Options\n" +
                "1. Show list of subscriptions\n" +
                "2. Show income forecast\n" +
                "3. Sign out\n" +
                "Choice: ");
    }

    public int menuOptionsForAccountantProgram() {
        int accountantChosenOption;
        accountantChosenOption = Input.scannerInt(scanner, 1, 3);

        switch (accountantChosenOption) {
            case 1 -> showSubscriptionList();
            case 2 -> showIncomeForecast();
            case 3 -> signOut();
        }
        return accountantChosenOption;
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
            buildMenuForCoachProgram();
            coachChosenOption = menuOptionsForCoachProgram();
        }
        while (coachChosenOption != 4);
    }

    public void buildMenuForCoachProgram() {
        System.out.print("""
                                    
                Coach - Options
                1. Show Junior Team
                2. Show Senior Team
                3. Add New Result
                4. Sign Out
                Choice:""");
    }
    public int menuOptionsForCoachProgram() {
        int coachChosenOption;
        coachChosenOption = Input.scannerInt(scanner, 1, 4);

        switch (coachChosenOption) {
            case 1 -> showJuniorTeam();
            case 2 -> showSeniorTeam();
            case 3 -> addNewResult();
            case 4 -> signOut();
        }

        return coachChosenOption;
    }

    public void showJuniorTeam() {
        buildJuniorTeamList();
    }
    public void buildJuniorTeamList() {
        String juniorTeam = "\nJunior Team:\n--------------------";

        for (Member member : controller.getJuniorTeam().getMembers()) {
            juniorTeam += "\n" + member.getName() + " - " + member.getEmail() + " - " + member.getDiscipline() +
                    "\nLeaderboard Results:";

            for (Result result : controller.getJuniorTeam().getLeaderboard()) {
                if (result.getMemberEmail().equals(member.getEmail())) {
                    juniorTeam += "\n- " + result.getTime() + "s - " + result.getDate();
                }
            }
            juniorTeam += "\n--------------------";
        }

        System.out.println(juniorTeam);
    }

    public void showSeniorTeam() {
        buildSeniorTeamList();
    }
    public void buildSeniorTeamList() {
        String seniorTeam = "\nSenior Team:\n--------------------";

        for (Member member : controller.getSeniorTeam().getMembers()) {
            seniorTeam += "\n" + member.getName() + " - " + member.getEmail() + " - " + member.getDiscipline() +
                    "\nLeaderboard Results:";

            for (Result result : controller.getSeniorTeam().getLeaderboard()) {
                if (result.getMemberEmail().equals(member.getEmail())) {
                    seniorTeam += "\n- " + result.getTime() + "s - " + result.getDate();
                }
            }
            seniorTeam += "\n--------------------";
        }

        System.out.println(seniorTeam);
    }

    public void addNewResult() {
        System.out.print("\nAdding new result:\nEnter member email: ");
        String email = scanner.nextLine();
        errorHandlingEmailFormat(email);

        System.out.print("\nEnter time in seconds: ");
        double time = Input.scannerDouble(scanner);

        controller.addResultToTeam(email, time);
    }

    public void signOut() {
        System.out.println("Signing out...");
        defaultScreen();
    }
}
