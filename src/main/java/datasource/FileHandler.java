package datasource;

import domain_model.Member;

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

            Member addMember = new Member(attributes[0],
                    attributes[1],
                    attributes[2],
                    attributes[3],
                    Double.parseDouble(attributes[4].trim()));

            if (!loadedFile.contains(addMember)){
                loadedFile.add(addMember);
            }
        }
        readFile.close();
        return loadedFile;
    }
}
