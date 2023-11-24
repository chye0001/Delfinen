package domain_model;

import datasource.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MemberDatabase {

    private ArrayList<Member> clubMembers;
    FileHandler fileHandler = new FileHandler();
    File administratorFile = new File("ListOfMembers.csv");

    public MemberDatabase() {
        this.clubMembers = new ArrayList<>();
    }

    public void loadMemberDatabase(){
        try {
            clubMembers = fileHandler.load(administratorFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean addMemberToList(String name,
                                   String birthDate,
                                   String email,
                                   String discipline,
                                   double subscription) throws FileNotFoundException {

        Member newMember = new Member(name, birthDate, email, discipline, subscription);
        clubMembers.add(newMember);
        FileHandler.save(clubMembers, administratorFile); //TODO - why does it have to be static and what does static methods do?
        return true;
    }
}
