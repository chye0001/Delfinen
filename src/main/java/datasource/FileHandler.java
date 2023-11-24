package datasource;

import domain_model.Members.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public static void save(ArrayList<Member> listOfMembers, File fileToSaveTo) throws FileNotFoundException {

        PrintStream printStream = new PrintStream(fileToSaveTo);

        for (Member member:listOfMembers) {
            printStream.println(member.getName() + ";" +
                    member.getBirthDate() + ";" +
                    member.getEmail() + ";" +
                    member.getDiscipline() + ";" +
                    member.getSubscriptionCost());
        }
    }

    public ArrayList<Member> load(File fileToLoadFrom) throws FileNotFoundException{

        Scanner readFile = new Scanner(fileToLoadFrom);
        ArrayList<Member> loadedFile = new ArrayList<>();

        while (readFile.hasNext()){
            String line = readFile.nextLine();
            String attributes[] = line.split(";");

            Member addMember;

            switch (attributes[0]){
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
}
