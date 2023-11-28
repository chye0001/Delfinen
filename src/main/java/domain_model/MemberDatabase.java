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
    File administratorFile = new File("ListOfMembers.csv");

    private Team juniorTeam;
    private Team seniorTeam;
    File competitiveResultsFile = new File("CompetitiveResults.csv");

    public MemberDatabase() {
        clubMembers = new ArrayList<>();

        juniorTeam = new JuniorTeam();
        seniorTeam = new SeniorTeam();
    }

    public void loadMemberDatabase(){
        try {
            clubMembers = FileHandler.clubMembersLoad(administratorFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < clubMembers.size(); i++) {
            //TODO make member type ENUM
            if (clubMembers.get(i).getType().equals("Competitive")){
                int birthYear = Integer.parseInt(clubMembers.get(i).getBirthDate().split("/")[2]);
                if (LocalDate.now().getYear()-birthYear < 18){ //TODO make this comparison less primitive
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

    public void setAdministratorFile(File administratorFile) { //for testing purposes only
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
            FileHandler.clubMembersSave(clubMembers, administratorFile);
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
        return true; //why is this used? It will literally only be true.
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

    public int calculateAgeFromBirthDate(int count) {
        //TODO: make member class handle age, with getAge method.
        String[] birthDateSplit = clubMembers.get(count).getBirthDate().split("/");

        int age = LocalDate.now().getYear() - Integer.parseInt(birthDateSplit[2]) - 1;

        if (LocalDate.now().getDayOfMonth() >= Integer.parseInt(birthDateSplit[0]) && LocalDate.now().getMonthValue() >= Integer.parseInt(birthDateSplit[1])) {
            age = LocalDate.now().getYear() - Integer.parseInt(birthDateSplit[2]);
            return age;

        } else
            return age;
    }

    public double showIncomeForecast() {
        double totalExpectedIncome = 0;
        for (Member income : clubMembers) {
            totalExpectedIncome += income.getSubscriptionCost();
        }
        return totalExpectedIncome;
    }


    public Team getJuniorTeam(){
        return juniorTeam;
    }

    public Team getSeniorTeam(){
        return seniorTeam;
    }

    public void loadCompetitiveResults(){
        ArrayList<Result> loadedResults = new ArrayList<>();
        try {
            loadedResults = FileHandler.competitiveResultsLoad(competitiveResultsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < loadedResults.size(); i++) {
            //starts off by finding a member that has the same email as the result
            Member member = null;
            for (Member clubMember : clubMembers) {
                if (loadedResults.get(i).getMemberEmail().equals(clubMember.getEmail())) {
                    member = clubMember;
                }
            }
            if (member != null){
                //takes birthdate of member with matching email and uses it to figure out which of the two teams
                //that the result should be added to
                //TODO make age comparison better, since it is currently just based on year comparison
                int birthYear = Integer.parseInt(member.getBirthDate().split("/")[2]);
                if (LocalDate.now().getYear()-birthYear < 18){
                    juniorTeam.addResultToLeaderboard(loadedResults.get(i));
                }else{
                    seniorTeam.addResultToLeaderboard(loadedResults.get(i));
                }
            }
        }
    }

    public void addResultToTeam(String email, double time){
        //the LocalDate is set to the moment the entry is made
        Result newResult = new Result(email,time,LocalDate.now());

        //save to .csv file
        ArrayList<Result> combinedResultList = new ArrayList<>();
        //adds both teams' results first
        combinedResultList.addAll(juniorTeam.getLeaderboard());
        combinedResultList.addAll(seniorTeam.getLeaderboard());
        //then the new result is added
        combinedResultList.add(newResult);
        try {
            FileHandler.competitiveResultsSave(combinedResultList, competitiveResultsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Member member = null;
        for (Member clubMember : clubMembers) {
            if (newResult.getMemberEmail().equals(clubMember.getEmail())) {
                member = clubMember;
            }
        }
        if (member != null){
            //TODO make age comparison better, since it is currently just based on year comparison
            int birthYear = Integer.parseInt(member.getBirthDate().split("/")[2]);
            if (LocalDate.now().getYear()-birthYear < 18){
                juniorTeam.addResultToLeaderboard(newResult);
            }else{
                seniorTeam.addResultToLeaderboard(newResult);
            }
        }
    }

}
