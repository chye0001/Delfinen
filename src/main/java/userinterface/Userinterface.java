package userinterface;

import domain_model.Controller;
import domain_model.Result;
import domain_model.MemberType;
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

    private void buildMenuForStartProgram() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nUser\n").append("1. Administrator\n" +
                "2. Accountant\n" +
                "3. Coach\n" +
                "0. Close program\n" +
                "Enter your credentials: ");
        System.out.print(sb);
    }

    private void menuOptionsForStartProgram() {

        int choice = Input.scannerInt(scanner, 0, 3);

        switch (choice) {
            case 1 -> administratorProgram();
            case 2 -> accountantProgram();
            case 3 -> coachProgram();
            case 0 -> shutDown();

        }
    }


    private void administratorProgram() {
        int administratorChosenOption;

        do {
            buildMenuForAdministratorProgram();
            administratorChosenOption = menuOptionsForAdministratorProgram();

        } while (administratorChosenOption != 0);
    }

    private void buildMenuForAdministratorProgram() {
        System.out.print("\nAdministrator - Options\n" +
                "1. Add new member\n" +
                "2. Show list of members\n" +
                "3. Edit member information(not valid yet)\n" +
                "4. Delete member(not valid yet)\n" +
                "0. Sign out\n" +
                "Choice: ");
    }

    private int menuOptionsForAdministratorProgram() {
        int administratorChosenOption;
        administratorChosenOption = Input.scannerInt(scanner, 0, 4);

        switch (administratorChosenOption) {
            case 1 -> addNewMember();
            case 2 -> showListOfMembers(false);
//                case 3 -> editMemberInformation(); //TODO - add edit
            case 4 -> deleteMember();
            case 0 -> signOut();
        }
        return administratorChosenOption;
    }

    private void showListOfMembers(boolean withNumbers) {
        System.out.println(controller.showListOfMembers(withNumbers));
    }

    private void addNewMember() {
        System.out.print("\nName: ");

        String memberName = scanner.nextLine();

        //TODO more robust date validation
        System.out.print("Birth date in DD-MM-YYYY: ");
        String inputDate = Input.scannerDate(scanner);
        //changes from DD-MM-YYYY to YYYY-MM-DD format for LocalDate
        String memberBirthDate = flipDateFormat(inputDate);


        System.out.print("Email: ");
        String memberEmail = Input.scannerEmail(scanner);


        buildMenuForAddMember();

        MemberType memberType = MemberType.NONE;
        int menuChoiceMemberType = Input.scannerInt(scanner, 1, 3);

        if (menuChoiceMemberType == 1) {
            memberType = MemberType.PASSIVE;
        }
        if (menuChoiceMemberType == 2) {
            memberType = MemberType.EXERCISE;
        }
        if (menuChoiceMemberType == 3) {
            memberType = MemberType.COMPETITIVE;
        }

        /*
        String memberDiscipline = "None";
        if (menuChoiceMemberType == 3) {
            System.out.print("Discipline: \n1. Backstroke\n2. Breaststroke\n3. Butterfly\n4. Crawl\nChoice: ");

            memberDiscipline = menuOptionsForMemberDisciplin();
        }
        */

        try {
            controller.addMemberToList(memberType,
                    memberName,
                    LocalDate.parse(memberBirthDate),
                    memberEmail);

            System.out.println(memberName + " added to list of members");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteMember() {
        System.out.println("Choose member to delete:");
        System.out.println("0. Cancel");
        showListOfMembers(true);
        int input = Input.scannerInt(scanner, 0, sizeOfMemberDatabase());
        if (input == 0) {
            System.out.println("Exiting...");
            administratorProgram();
        } else {
            System.out.println(memberNameFromIndex(input - 1) + " deleted");
            deleteMemberByIndex(input - 1);
        }

    }

    private void buildMenuForAddMember() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMember Type:\n").
                append("1. Passiv\n").
                append("2. Exercise Member\n").
                append("3. Competitive Member\n").
                append("Type: ");
        System.out.print(sb);
    }


    private String menuOptionsForMemberDisciplin() {
        return switch (Input.scannerInt(scanner, 1, 4)) {
            case 1 -> "Backstroke";
            case 2 -> "Breaststroke";
            case 3 -> "Butterfly";
            case 4 -> "Crawl";
            default -> "None";
        };
    }

    private void defaultScreen() {
        startProgram();
    }

    private void accountantProgram() {
        int accountantChosenOption;

        do {
            buildMenuForAccountantProgram();
            accountantChosenOption = menuOptionsForAccountantProgram();

        } while (accountantChosenOption != 0);
    }

    private void buildMenuForAccountantProgram() {
        System.out.print("\nAccountant - Options\n" +
                "1. Show list of subscriptions\n" +
                "2. Show income forecast\n" +
                "0. Sign out\n" +
                "Choice: ");
    }

    private int menuOptionsForAccountantProgram() {
        int accountantChosenOption;
        accountantChosenOption = Input.scannerInt(scanner, 0, 2);

        switch (accountantChosenOption) {
            case 1 -> showSubscriptionList();
            case 2 -> showIncomeForecast();
            case 0 -> signOut();
        }
        return accountantChosenOption;
    }

    private void showSubscriptionList() {
        System.out.println(controller.showListOfSubscriptions());
    }

    private void showIncomeForecast() {
        System.out.println("\nExpected 1 year revenue: " + controller.showIncomeForecast() + "kr.");
    }


    private void coachProgram() {
        int coachChosenOption;

        do {
            buildMenuForCoachProgram();
            coachChosenOption = menuOptionsForCoachProgram();
        }
        while (coachChosenOption != 0);
    }

    private void buildMenuForCoachProgram() {
        System.out.print("""
                                    
                Coach - Options
                1. Show Junior Team
                2. Show Senior Team
                3. Add New Result
                0. Sign Out
                Choice:""");
    }

    private int menuOptionsForCoachProgram() {
        int coachChosenOption;
        coachChosenOption = Input.scannerInt(scanner, 0, 3);

        switch (coachChosenOption) {
            case 1 -> showJuniorTeam();
            case 2 -> showSeniorTeam();
            case 3 -> addNewResult();
            case 0 -> signOut();
        }

        return coachChosenOption;
    }

    private void showJuniorTeam() {
        buildJuniorTeamList();
    }

    private void buildJuniorTeamList() {
        String juniorTeam = "\nJunior Team:\n--------------------";

        for (Member member : controller.getJuniorTeam().getMembers()) {
            juniorTeam += "\n" + member.getName() + " - " + member.getEmail() +
                    "\nLeaderboard Results:";

            for (Result result : controller.getJuniorTeam().getLeaderboard()) {
                if (result.getMemberEmail().equals(member.getEmail())) {
                    juniorTeam += "\n- " + result.getDiscipline() + " - " + result.getTime() + " - " + result.getDate();
                }
            }
            juniorTeam += "\n--------------------";
        }

        System.out.println(juniorTeam);
    }

    private void showSeniorTeam() {
        buildSeniorTeamList();
    }

    private void buildSeniorTeamList() {
        String seniorTeam = "\nSenior Team:\n--------------------";

        for (Member member : controller.getSeniorTeam().getMembers()) {
            seniorTeam += "\n" + member.getName() + " - " + member.getEmail() +
                    "\nLeaderboard Results:";

            for (Result result : controller.getSeniorTeam().getLeaderboard()) {
                if (result.getMemberEmail().equals(member.getEmail())) {
                    seniorTeam += "\n- " + result.getDiscipline() + " - " + result.getTime() + " - " + result.getDate();
                }
            }
            seniorTeam += "\n--------------------";
        }

        System.out.println(seniorTeam);
    }

    private void addNewResult() {
        System.out.print("\nAdding new result:\nEnter member email: ");
        String email = Input.scannerEmail(scanner);

        System.out.print("\nChoose discipline: ");
        System.out.print("""
                                
                1. Backstroke
                2. Breaststroke
                3. Butterfly
                4. Crawl
                """);
        int disciplineChoice = Input.scannerInt(scanner, 1, 4);
        String discipline = "";
        switch (disciplineChoice) {
            case 1 -> discipline = "Backstroke";
            case 2 -> discipline = "Breaststroke";
            case 3 -> discipline = "Butterfly";
            case 4 -> discipline = "Crawl";
        }

        System.out.print("\nEnter time in seconds: ");
        double time = Input.scannerDouble(scanner);

        controller.addResultToTeam(email, time, discipline);
    }



    private static String flipDateFormat(String date) {
        //changes from DD-MM-YYYY to YYYY-MM-DD format for LocalDate
        String[] splitInputDate = date.split("-");
        return splitInputDate[2] + "-" + splitInputDate[1] + "-" + splitInputDate[0];
    }

    private int sizeOfMemberDatabase() {
        return controller.getSizeOfMemberDatabase();
    }

    private String memberNameFromIndex(int memberIndex) {
        return controller.getMemberName(memberIndex);
    }

    private void deleteMemberByIndex(int memberIndex) {
        controller.deleteMember(memberIndex);
    }
    private void signOut() {
        System.out.println("Signing out...");
        defaultScreen();
    }

    private void shutDown() {
        System.out.println("Shutting down...");
    }
}
