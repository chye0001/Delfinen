package domain_model;

import datasource.FileHandler;
import domain_model.Members.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberDatabase {

    private ArrayList<Member> clubMembers;
    File administratorFile = new File("ListOfMembers.csv");
    File accountantFile = new File("listOfSubscription.csv");

    public MemberDatabase() {
        this.clubMembers = new ArrayList<>();
    }

    public void loadMemberDatabase(){
        try {
            clubMembers = FileHandler.load(administratorFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean addMemberToList(int type,
                                   String name,
                                   String birthDate,
                                   String email,
                                   String discipline,
                                   double subscription ) throws FileNotFoundException {


                Member newMember;

                switch (type){
                    case 1 ->
                            newMember = new PassiveMember(name, birthDate, email, discipline, subscription);

                    case 2 ->
                            newMember = new ExerciseMember(name, birthDate, email, discipline, subscription);

                    case 3 ->
                            newMember = new CompetitiveMember(name, birthDate, email, discipline, subscription);

                    case 4 ->
                            newMember = new JuniorMember(name, birthDate, email, discipline, subscription);

                    case 5 ->
                            newMember = new SeniorMember(name, birthDate, email, discipline, subscription);
                    default -> newMember = null;
                }
        clubMembers.add(newMember);
        FileHandler.save(clubMembers, administratorFile);
        return true;
    }

    public String showListOfMembers() {
        StringBuilder sb = new StringBuilder();
        for (Member member : clubMembers) {
            if(member != null) {
                sb.append("Name: ").append(member.getName()).append(" - Membership type: ").
                        append(member.getType()).append(" - Date of birth: ").append(member.getBirthDate()).
                        append(" - Email address: ").append(member.getEmail()).append("\n");
            }
        }return sb.toString();
    }

    public String showListOfSubscription(){
        StringBuilder sb = new StringBuilder();

        for (Member subscription : clubMembers) {
            String[] birthDateSplit = subscription.getBirthDate().split("/");
            int age = LocalDate.now().getYear() - Integer.parseInt(birthDateSplit[2]);

            sb.append("Name: ").append(subscription.getName()).append(" / ").
                    append("Age: ").append(age).append(" / ").
                    append("Activity type: ").append(subscription.getType()).append(" / ").
                    append("Subscription: ").append(subscription.getSubscription()).append("\n");
        }
        return sb.toString();
    }
}
