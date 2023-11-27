package domain_model;

import datasource.FileHandler;
import domain_model.members.*;
import domain_model.teams.JuniorTeam;
import domain_model.teams.SeniorTeam;
import domain_model.teams.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberDatabase {

    private ArrayList<Member> clubMembers;
    private Team juniorTeam;
    private Team seniorTeam;
    File administratorFile = new File("ListOfMembers.csv");

    public MemberDatabase() {
        this.clubMembers = new ArrayList<>();
        this.juniorTeam = new JuniorTeam();
        this.seniorTeam = new SeniorTeam();
    }

    public void loadMemberDatabase(){
        try {
            clubMembers = FileHandler.load(administratorFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < clubMembers.size(); i++) {
            //TODO make member type ENUM
            if (clubMembers.get(i).getType().equals("Competitive")){
                int birthYear = Integer.parseInt(clubMembers.get(i).getBirthDate().split("/")[2]);
                if (LocalDate.now().getYear()-birthYear < 18){
                    juniorTeam.addMember(clubMembers.get(i));
                }else{
                    seniorTeam.addMember(clubMembers.get(i));
                }
            }
        }
    }

    public ArrayList<Member> getClubMembers() {
        return clubMembers;
    }

    public void setAdministratorFile(File administratorFile) {
        this.administratorFile = administratorFile;
    }

    public boolean addMemberToList(int type,
                                   String name,
                                   String birthDate,
                                   String email,
                                   String discipline,
                                   double subscription) {

        Member newMember;

        switch (type){
            case 1 ->
                    newMember = new PassiveMember(name, birthDate, email, discipline, subscription);

            case 2 ->
                    newMember = new ExerciseMember(name, birthDate, email, discipline, subscription);

            case 3 ->
                    newMember = new SeniorMember(name, birthDate, email, discipline, subscription);

            case 4 ->
                    newMember = new JuniorMember(name, birthDate, email, discipline, subscription);

            case 5 ->
                    newMember = new CompetitiveMember(name, birthDate, email, discipline, subscription);
            default -> newMember = null;
        }
        clubMembers.add(newMember);
        try {
            FileHandler.save(clubMembers, administratorFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //adds to competitive teams based on age
        //TODO make day-month-year comparison and perhaps reformat the way that birthdate is saved
        if (newMember.getType().equals("Competitive")){ //TODO make ENUM
            int birthYear = Integer.parseInt(birthDate.split("/")[2]);
            if (LocalDate.now().getYear()-birthYear < 18){
                juniorTeam.addMember(newMember);
            }else{
                seniorTeam.addMember(newMember);
            }
        }
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
        }
        return sb.toString();
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
    public double showIncomeForecast() {
        double totalExpectedIncome = 0;
        for (Member income:clubMembers) {
            totalExpectedIncome += income.getSubscriptionCost();
        }
        return totalExpectedIncome;
    }

    public ArrayList<Member> getJuniorTeam(){
        return juniorTeam.getMembers();
    }

    public ArrayList<Member> getSeniorTeam(){
        return seniorTeam.getMembers();
    }
}
