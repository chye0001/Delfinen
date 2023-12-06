package datasource;

import domain_model.Discipline;
import domain_model.Result;
import domain_model.MemberType;
import domain_model.Subscription;
import domain_model.members.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public static void clubMembersSave(ArrayList<Member> listOfMembers,
                                       File fileToSaveTo,
                                       File resultsFile) throws FileNotFoundException {
        //TODO change way that info is saved
        //why? -Kristoffer
        PrintStream printStream = new PrintStream(fileToSaveTo);

        for (Member member : listOfMembers) {
            printStream.println(
                    member.getType() + ";" +
                            member.getName() + ";" +
                            member.getBirthDate() + ";" +
                            member.getEmail());
            if (member instanceof CompetitiveMember compMember) {
                resultsSave(compMember,resultsFile);
            }
        }
    }

    public static void saveAll(ArrayList<Member> members,
                               File memberFile,
                               File subscriptionFile,
                               File resultsFile) throws FileNotFoundException {
        clubMembersSave(members, memberFile,resultsFile);
        subscriptionSave(members, subscriptionFile);
        //resultsSave(members, resultsFile);

    }

    private static void resultsSave(CompetitiveMember member,
                                    File resultsFile) {
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(resultsFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String email = member.getEmail();
        ArrayList<Result> results = member.getAllResults();
        for (Result result : results) {
            printStream.println(
                    member.getEmail() + ";" +
                    result.getTime() + ";" +
                    result.getDiscipline() + ";" +
                    result.getDate());
        }


    }

    public static void subscriptionSave(ArrayList<Member> members,
                                        File subscriptionFile) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(subscriptionFile);

        for (Member member : members) {
            printStream.println(
                    member.getEmail() + ";" +
                    member.getLastPaymentDate() + ";" +
                    member.getNextPaymentDate() + ";" +
                    member.getSubscriptionCost() + ";" +
                    member.getSubscriptDebt() + ";" +
                    member.getIsPaid());

        }

    }

    public static ArrayList<Member> clubMembersLoad(File memberFile, File subscriptionFile, File resultsFile) throws FileNotFoundException {

        Scanner readFile = new Scanner(memberFile);
        ArrayList<Member> loadedFile = new ArrayList<>();

        while (readFile.hasNext()) {
            String line = readFile.nextLine();
            String[] attributes = line.split(";");

            Member addMember;
            MemberType memberType = MemberType.valueOf(attributes[0].toUpperCase());
            switch (memberType) {
                case PASSIVE -> addMember = new PassiveMember(attributes[1],
                        LocalDate.parse(attributes[2]),
                        attributes[3],
                        loadSubscription(attributes[3], subscriptionFile));

                case EXERCISE -> addMember = new ExerciseMember(attributes[1],
                        LocalDate.parse(attributes[2]),
                        attributes[3],
                        loadSubscription(attributes[3], subscriptionFile));

                case COMPETITIVE -> { addMember = new CompetitiveMember(attributes[1],
                        LocalDate.parse(attributes[2]),
                        attributes[3],
                        loadSubscription(attributes[3], subscriptionFile));
                        ((CompetitiveMember) addMember).setResults(loadCompMemberResults(attributes[3],resultsFile));
                }

                default -> addMember = null;
            }

            if (!loadedFile.contains(addMember)) {
                loadedFile.add(addMember);
            }
        }
        readFile.close();
        return loadedFile;
    }

    private static ArrayList<Result> loadCompMemberResults(String email, File resultFile) {
        ArrayList<Result> results = new ArrayList<>();
        Scanner readFile;
        try {
           readFile = new Scanner(resultFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (readFile.hasNext()) {
            String line = readFile.nextLine();
            String[] attributes = line.split(";");

            if (attributes[0].equals(email)) {
                results.add(
                        new Result(attributes[0],
                                Double.parseDouble(attributes[1]),
                                Discipline.valueOf(attributes[2]),
                                LocalDate.parse(attributes[3]))
                );

            }

        }
        return results;
    }

    private static Subscription loadSubscription(String email, File subscriptionFile) throws FileNotFoundException {
        Scanner readFile = new Scanner(subscriptionFile);


        while (readFile.hasNext()) {
            String line = readFile.nextLine();
            String[] attributes = line.split(";");

            if(attributes[0].equals(email)) {
                LocalDate lastPayment;
                LocalDate nextPayment;
                double cost = Double.parseDouble(attributes[3]);
                double debt = Double.parseDouble(attributes[4]);
                boolean isPaid = Boolean.parseBoolean(attributes[5]);

                if (attributes[1].equalsIgnoreCase("-")) {
                    lastPayment = null;
                    nextPayment = null;

                } else {
                    lastPayment = LocalDate.parse(attributes[1]);
                    nextPayment = LocalDate.parse(attributes[2]);
                }
                return new Subscription(lastPayment, nextPayment, cost, debt, isPaid);
            }

        }
        return null;
    }

    public static void competitiveResultsSave(ArrayList<Result> resultsList,
                                              File fileToSaveTo) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(fileToSaveTo);

        for (Result result : resultsList) {
            printStream.println(
                    result.getMemberEmail() + ";" +
                            result.getTime() + ";" +
                            result.getDate() + ";"
            );
        }
    }

    public static ArrayList<Result> competitiveResultsLoad(File fileToLoadFrom) throws FileNotFoundException {
        Scanner fileReader = new Scanner(fileToLoadFrom);
        ArrayList<Result> loadedList = new ArrayList<>();

        while (fileReader.hasNext()) {
            String[] attributes = fileReader.nextLine().split(";");

            Result loadedResult = new Result(
                    attributes[0],
                    Double.parseDouble(attributes[1]),
                    Discipline.valueOf(attributes[2].toUpperCase()),
                    LocalDate.parse(attributes[3])
            );

            if (!loadedList.contains(loadedResult)) {
                loadedList.add(loadedResult);
            }
        }

        fileReader.close();
        return loadedList;
    }
}
