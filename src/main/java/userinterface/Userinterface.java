package userinterface;

import domain_model.Controller;
import domain_model.MemberType;
import domain_model.members.CompetitiveMember;
import domain_model.members.ExerciseMember;
import domain_model.members.Member;
import domain_model.members.PassiveMember;
import userinterface.table.Row;
import userinterface.table.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Userinterface {

    Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);
    private boolean run = true;
    
    public void startProgram() {
        mainProgram();
    }
    
    private void mainProgram () {
        while (run) {
            buildMainMenu();
            mainMenuOptions();
        }
    }
    private void buildMainMenu() {
        ArrayList<String> columns = new ArrayList<>(List.of("#","User"));
        Table menuTable = new Table("Delfinen",columns,true);
        menuTable.addRow(new Row().addCell("1").addCell("Adminstrator"));
        menuTable.addRow(new Row().addCell("2").addCell("Accountant"));
        menuTable.addRow(new Row().addCell("3").addCell("Coach"));
        menuTable.addRow(new Row().addCell("0").addCell("Close program"));
        System.out.println(menuTable);
        System.out.print("> ");

    }
    private void mainMenuOptions() {

        int choice = Input.scannerInt(scanner, 0, 3);

        switch (choice) {
            case 1 -> administratorProgram();
            case 2 -> accountantProgram();
            case 3 -> coachProgram();
            case 0 -> shutDown();

        }
    }


    private void administratorProgram() {
            buildAdministratorMenu();
            administratorMenuOptions();
    }
    private void buildAdministratorMenu() {
        ArrayList<String> columns = new ArrayList<>(List.of("#","Option"));
        Table tableMenu = new Table("Adminstrator",columns,true);
        tableMenu.addRow(new Row().addCell("1").addCell("Add new member"));
        tableMenu.addRow(new Row().addCell("2").addCell("Show list of members"));
        tableMenu.addRow(new Row().addCell("3").addCell("Edit member information"));
        tableMenu.addRow(new Row().addCell("4").addCell("Delete member"));
        tableMenu.addRow(new Row().addCell("0").addCell("Sign out"));
        System.out.println(tableMenu);
        System.out.print("> ");
    }
    private void administratorMenuOptions() {
        int input = Input.scannerInt(scanner, 0, 4);
        switch (input) {
            case 1 -> addNewMember();
            case 2 -> showListOfMembers(false);
            case 3 -> editMemberInformation();
            case 4 -> deleteMember();
            case 0 -> signOutToMainProgram();
        }
    }


    private void accountantProgram() {
            buildAccountantMenu();
            accountantMenuOptions();
    }
    private void buildAccountantMenu() {
        ArrayList<String> columns = new ArrayList<>(List.of("#","Option"));
        Table tableMenu = new Table("Accountant",columns,true);
        tableMenu.addRow(new Row().addCell("1").addCell("Show list of subscriptions"));
        tableMenu.addRow(new Row().addCell("2").addCell("Change payment status"));
        tableMenu.addRow(new Row().addCell("3").addCell("Show income forecast"));
        tableMenu.addRow(new Row().addCell("0").addCell("Sign out"));
        System.out.println(tableMenu);
        System.out.print("> ");

    }
    private void accountantMenuOptions() {
        int input = Input.scannerInt(scanner, 0, 3);
        switch (input) {
            case 1 -> showSubscriptionList();
            case 2 -> changePaymentStatus();
            case 3 -> showIncomeForecast();
            case 0 -> signOutToMainProgram();
        }
    }


    private void coachProgram() {
            buildCoachMenu();
            coachMenuOptions();
    }
    private void buildCoachMenu() {
        ArrayList<String> columns = new ArrayList<>(List.of("#", "Option"));
        Table tableMenu = new Table("Coach",columns,true);
        tableMenu.addRow(new Row().addCell("1").addCell("Show Junior Team"));
        tableMenu.addRow(new Row().addCell("2").addCell("Show Senior Team"));
        tableMenu.addRow(new Row().addCell("3").addCell("Add new result"));
        tableMenu.addRow(new Row().addCell("0").addCell("Sign out"));
        System.out.println(tableMenu);
        System.out.print("> ");
    }
    private void coachMenuOptions() {
        int input = Input.scannerInt(scanner, 0, 3);
        switch (input) {
            case 1 -> showJuniorTeam();
            case 2 -> showSeniorTeam();
            case 3 -> addNewResult();
            case 0 -> signOutToMainProgram();
        }
    }


    private void signOutToMainProgram() {
        System.out.println("Signing out...");
        mainProgram();
    }
    
    private void editMemberInformation(){
        showListOfMembers(true);
        System.out.println("Choose member to edit:");
        System.out.println("0. Cancel");
        int input = Input.scannerInt(scanner, 0, sizeOfMemberDatabase());
        if (input == 0) {
            System.out.println("Cancelling...");
            administratorProgram();
        } else {
            Member chosenMember = controller.getClubMembers().get(input-1);
            System.out.print("""
                    
                    Chose the information that you wish to change:
                    1. Name
                    2. Birth Date
                    3. Email
                    4. Membership Type
                    0. Cancel
                    """);
            int choice = Input.scannerInt(scanner,0,4);
            switch (choice){
                case 1 -> {
                    System.out.print("\nName: ");
                    chosenMember.setName(scanner.nextLine());
                }
                case 2 -> {
                    System.out.print("Birth date in DD-MM-YYYY: ");
                    chosenMember.setBirthDate(LocalDate.parse(
                            flipDateFormat(Input.scannerDate(scanner))));
                }
                case 3 -> {
                    System.out.print("Email: ");
                    String memberEmail = Input.scannerEmail(scanner);
                    for (Member member: controller.getClubMembers()) {
                        //returns to menu if email already exists
                        if (member.getEmail().equalsIgnoreCase(memberEmail)){
                            System.out.println(Color.red("Email already exists!\nNo changes made!"));
                            administratorProgram();
                        }
                    }
                    chosenMember.setEmail(memberEmail);
                }
                case 4 -> {
                    buildMenuForAddMember();
                    int menuChoiceMemberType = Input.scannerInt(scanner, 1, 3);

                    if (menuChoiceMemberType == 1) {
                        chosenMember = new PassiveMember(chosenMember.getName(),
                                chosenMember.getBirthDate(), chosenMember.getEmail());
                    }
                    if (menuChoiceMemberType == 2) {
                        chosenMember = new ExerciseMember(chosenMember.getName(),
                                chosenMember.getBirthDate(), chosenMember.getEmail());
                    }
                    if (menuChoiceMemberType == 3) {
                        chosenMember = new CompetitiveMember(chosenMember.getName(),
                                chosenMember.getBirthDate(), chosenMember.getEmail());
                    }
                }
                case 0 -> {
                    System.out.println("Cancelling...");
                    administratorProgram();
                }
            }

            editMemberByIndex(input - 1,chosenMember);
        }
    }

    private void editMemberByIndex(int index, Member member){
        controller.editMember(index,member);
    }

    private void deleteMember() {
        showListOfMembers(true);
        System.out.println("Choose member to delete:");
        System.out.println("0. Cancel");
        int input = Input.scannerInt(scanner, 0, sizeOfMemberDatabase());
        if (input == 0) {
            System.out.println("Cancelling...");
            administratorProgram();
        } else {
            System.out.println(memberNameFromIndex(input - 1) + " deleted");
            deleteMemberByIndex(input - 1);
        }
    }
    private void deleteMemberByIndex(int memberIndex) {
        controller.deleteMember(memberIndex);
    }

    private void showListOfMembers(boolean withNumbers) {
        if (withNumbers) {
            System.out.println(createMemberListWithNumbers());
        }
        else {
            System.out.println(createMemberList());
        }
    }

    private void addNewMember() {
        System.out.print("\nName: ");

        String memberName = scanner.nextLine();

        //TODO more robust date validation
        System.out.print("Birth date in DD-MM-YYYY: ");
        String inputDate = Input.scannerDate(scanner);
        //changes from DD-MM-YYYY to YYYY-MM-DD format for LocalDate
        String memberBirthDate = flipDateFormat(inputDate);



        String memberEmail = "";
        boolean uniqueEmail = false;
        while(!uniqueEmail) {
            System.out.print("Email: ");
            uniqueEmail = true;
            memberEmail = Input.scannerEmail(scanner);
            for (Member member: controller.getClubMembers()) {
                if (member.getEmail().equalsIgnoreCase(memberEmail)){
                    uniqueEmail = false;
                    System.out.println(Color.red("Email already exists!"));
                }
            }
        }

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



    

    private void showSubscriptionList() {
        //System.out.println(controller.showListOfSubscriptions());
        System.out.println(createSubscriptionTable());
    }
    private void changePaymentStatus() {
        showSubscriptionList();
        System.out.print("Cancel process by entering 0\nChange payment status for: ");
        int accountantChoise = Input.scannerInt(scanner, 0, controller.getClubMembers().size());

        if (accountantChoise == 0) {
            System.out.println("canceling process...");
        } else {
            controller.changePaymentStatus(accountantChoise);
            System.out.println("Payment status updated");
        }
    }

    private void showIncomeForecast() {
        System.out.println(controller.showIncomeForecast());
    }


    

    private void showJuniorTeam() {
        buildJuniorTeamList();
    }

    private void buildJuniorTeamList() {
        ArrayList<Member> juniorTeam = controller.getJuniorTeam().getMembers();
        Table compTable = createCompMemberTable("Junior Team", juniorTeam);
        System.out.println(compTable);
    }

    private void showSeniorTeam() {
        buildSeniorTeamList();
    }

    private void buildSeniorTeamList() {
        ArrayList<Member> seniorTeam = controller.getSeniorTeam().getMembers();
        Table compTable = createCompMemberTable("Senior Team", seniorTeam);
        System.out.println(compTable);
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


    private Table createSubscriptionTable() {
        ArrayList<Member> members = controller.getClubMembers();
        ArrayList<String> columns = new ArrayList<>(List.of(
                "Name",
                "Email",
                "Age",
                "Type",
                "Price",
                "Paid",
                "Debt",
                "Last Payment",
                "Next Payment"
        ));
        Table subscriptionTable = new Table("Subscriptions",columns,true);
        int count = 1;
        for(Member member : members) {
            String name = member.getName();
            String email = member.getEmail();
            int age = member.getAge();
            String type = member.getType().toString();
            double price = member.getSubscriptionCost();
            //ternary operators------------------------------------------------------------
            String paid = member.getSubscription().isPaid() ? "Yes" : "No";
            String paidColor = member.getSubscription().isPaid() ? Color.GREEN : Color.RED;
            //-----------------------------------------------------------------------------
            double debt = member.getSubscriptDebt();
            String lastPayment = member.getLastPaymentDate();
            String nextPayment = member.getNextPaymentDate();
            subscriptionTable.addRow(new Row()
                    .addCell(count + ": " + name)
                    .addCell(email)
                    .addCell(age)
                    .addCell(type)
                    .addCell(price)
                    .addCell(paid,paidColor)
                    .addCell(debt)
                    .addCell(lastPayment)
                    .addCell(nextPayment));

            //String paid = member.getSubscription().isPaid() ? "Yes" : "No";
            count++;
        }
        return subscriptionTable;
    }

    private Table createMemberList() {
        ArrayList<String> columns = new ArrayList<>(List.of(
                "Name",
                "Type",
                "Birthdate",
                "Email"
        ));
        Table memberTable = new Table("Members",columns,true);
        ArrayList<Member> members = controller.getClubMembers();
        for(Member member : members) {
            String name = member.getName();
            String type = member.getType().toString();
            String birthdate = member.getBirthDate().toString();
            String email = member.getEmail();
            memberTable.addRow(new Row()
                    .addCell(name)
                    .addCell(type)
                    .addCell(birthdate)
                    .addCell(email));
        }
        return memberTable;
    }

    private Table createMemberListWithNumbers() {
        ArrayList<String> columns = new ArrayList<>(List.of(
                "#",
                "Name",
                "Type",
                "Birthdate",
                "Email"
        ));
        Table memberTable = new Table("Members",columns,true);
        ArrayList<Member> members = controller.getClubMembers();
        int count = 1;
        for(Member member : members) {
            String name = member.getName();
            String type = member.getType().toString();
            String birthdate = member.getBirthDate().toString();
            String email = member.getEmail();
            memberTable.addRow(new Row()
                    .addCell(count)
                    .addCell(name)
                    .addCell(type)
                    .addCell(birthdate)
                    .addCell(email));
            count++;
        }
        return memberTable;
    }

    private Table createCompMemberTable(String header,ArrayList<Member> members) {
        ArrayList<String> columns = new ArrayList<>(List.of(
                "Name",
                "Email",
                "Backstroke",
                "Breaststroke",
                "Butterfly",
                "Crawl"
        ));
        Table compTable = new Table(header,columns,true);
        for(Member member : members) {
            CompetitiveMember compMember = (CompetitiveMember) member;
            String name = member.getName();
            String email = member.getEmail();
            String breast = "01:19.434"; // TODO: Format time.
            String back = "01:19.434"; // Format time, and get best time for dicipline
            String fly = "01:19.434"; // Format time, and get best time for dicipline
            String crawl = "01:19.434"; // Format time, and get best time for dicipline
            compTable.addRow(new Row()
                    .addCell(name)
                    .addCell(email)
                    .addCell(breast)
                    .addCell(back)
                    .addCell(fly)
                    .addCell(crawl));
        }
        return compTable;
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




    private void shutDown() {
        System.out.println("Shutting down...");
        run = false;
    }
}
