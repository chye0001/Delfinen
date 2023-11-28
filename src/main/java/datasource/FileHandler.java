package datasource;

import domain_model.Result;
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
                    member.getDiscipline() + ";" +
                    member.getSubscriptionCost());
        }
    }

    public static ArrayList<Member> clubMembersLoad(File fileToLoadFrom) throws FileNotFoundException{

        Scanner readFile = new Scanner(fileToLoadFrom);
        ArrayList<Member> loadedFile = new ArrayList<>();

        while (readFile.hasNext()){
            String line = readFile.nextLine();
            String[] attributes = line.split(";");

            Member addMember;
            String memberType = attributes[0];
            switch (memberType){
                case "Passive" ->
                    addMember = new PassiveMember(attributes[1],
                            attributes[2],
                            attributes[3],
                            attributes[4],
                            Double.parseDouble(attributes[5].trim()));

                case "Exercise" ->
                    addMember = new ExerciseMember(attributes[1],
                            attributes[2],
                            attributes[3],
                            attributes[4],
                            Double.parseDouble(attributes[5].trim()));

                case "Competitive" ->
                    addMember = new CompetitiveMember(attributes[1],
                            attributes[2],
                            attributes[3],
                            attributes[4],
                            Double.parseDouble(attributes[5].trim()));

                case "Junior" ->
                    addMember = new JuniorMember(attributes[1],
                            attributes[2],
                            attributes[3],
                            attributes[4],
                            Double.parseDouble(attributes[5].trim()));

                case "Senior" ->
                    addMember = new SeniorMember(attributes[1],
                            attributes[2],
                            attributes[3],
                            attributes[4],
                            Double.parseDouble(attributes[5].trim()));
                default -> addMember = null;
            }

            if (!loadedFile.contains(addMember)){
                loadedFile.add(addMember);
            }
        }
        readFile.close();
        return loadedFile;
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
