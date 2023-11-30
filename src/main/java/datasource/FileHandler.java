package datasource;

import domain_model.Result;
import domain_model.MemberType;
import domain_model.Subscription;
import domain_model.members.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public static void clubMembersSave(ArrayList<Member> listOfMembers, File fileToSaveTo) throws FileNotFoundException {
        //TODO change way that info is saved
        //why? -Kristoffer
        PrintStream printStream = new PrintStream(fileToSaveTo);

        for (Member member : listOfMembers) {
            printStream.println(
                    member.getType() + ";" +
                    member.getName() + ";" +
                    member.getBirthDate() + ";" +
                    member.getEmail() + ";" +
                    member.getDiscipline());
        }
    }

    public static void saveAll(ArrayList<Member> members,
                               File memberFile,
                               File subscirptionFile,
                               File resultsFile) throws FileNotFoundException {
        clubMembersSave(members,memberFile);
        subscirptionSave(members, subscirptionFile);
        //resultsSave(members, resultsFile);
        
    }

//    private static void resultsSave(ArrayList<Member> members,
//                                    File resultsFile) {
//        PrintStream printStream = new PrintStream(resultsFile);
//
//        for (Member member : members) {
//            if (member instanceof CompetitiveMember compMember) {
//                printStream.println(
//                    compMember.getEmail() + ";" +
//                    compMember.get
//            }
//
//
//
//        }
//    }

    private static void subscirptionSave(ArrayList<Member> members,
                                         File subscirptionFile) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(subscirptionFile);

        for (Member member : members) {
            printStream.println(
                    member.getEmail() + ";" +
                    member.getLastPaymentDate() + ";" +
                    member.getNextPaymentDate() + ";" +
                    member.getSubscriptionCost() + ";" +
                    member.getSubscriptDebt());

        }
        
    }

    public static ArrayList<Member> clubMembersLoad(File memberFile, File subscriptionFile) throws FileNotFoundException{

        Scanner readFile = new Scanner(memberFile);
        ArrayList<Member> loadedFile = new ArrayList<>();

        while (readFile.hasNext()){
            String line = readFile.nextLine();
            String[] attributes = line.split(";");

            Member addMember;
            MemberType memberType = MemberType.valueOf(attributes[0].toUpperCase());
            switch (memberType){
                case PASSIVE ->
                    addMember = new PassiveMember(attributes[1],
                            LocalDate.parse(attributes[2]),
                            attributes[3],
                            attributes[4],
                            loadSubscirption(attributes[3],subscriptionFile));

                case EXERCISE ->
                    addMember = new ExerciseMember(attributes[1],
                            LocalDate.parse(attributes[2]),
                            attributes[3],
                            attributes[4],
                            loadSubscirption(attributes[3],subscriptionFile));

                case COMPETITIVE ->
                    addMember = new CompetitiveMember(attributes[1],
                            LocalDate.parse(attributes[2]),
                            attributes[3],
                            attributes[4],
                            loadSubscirption(attributes[3],subscriptionFile));

                default -> addMember = null;
            }

            if (!loadedFile.contains(addMember)){
                loadedFile.add(addMember);
            }
        }
        readFile.close();
        return loadedFile;
    }

    private static Subscription loadSubscirption(String email,File subscriptionFile) throws FileNotFoundException {
        Scanner readFile = new Scanner(subscriptionFile);



        while (readFile.hasNext()){
            String line = readFile.nextLine();
            String[] attributes = line.split(";");

            if(attributes[0].equals(email)) {
                LocalDate lastPayment = LocalDate.parse(attributes[1]);
                LocalDate nextPayment = LocalDate.parse(attributes[2]);
                double cost = Double.parseDouble( attributes[3]);
                double debt = Double.parseDouble( attributes[4]);
                return new Subscription(lastPayment,nextPayment,cost,debt);
            }

        }
        return null;
    }

    public static void competitiveResultsSave(ArrayList<Result> resultsList,
                                              File fileToSaveTo) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(fileToSaveTo);

        for (Result result : resultsList){
            printStream.println(
                    result.getMemberEmail()+";"+
                            result.getTime()+";"+
                            result.getDate()+";"
            );
        }
    }

    public static ArrayList<Result> competitiveResultsLoad(File fileToLoadFrom) throws FileNotFoundException{
        Scanner fileReader = new Scanner(fileToLoadFrom);
        ArrayList<Result> loadedList = new ArrayList<>();

        while (fileReader.hasNext()){
            String[] attributes = fileReader.nextLine().split(";");

            Result loadedResult = new Result(
                    attributes[0],
                    Double.parseDouble(attributes[1]),
                    LocalDate.parse(attributes[2])
            );

            if(!loadedList.contains(loadedResult)){
                loadedList.add(loadedResult);
            }
        }

        fileReader.close();
        return loadedList;
    }
}
