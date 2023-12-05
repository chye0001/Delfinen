package domain_model;

import datasource.FileHandler;
import domain_model.members.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberDatabase {

    private ArrayList<Member> clubMembers;
    File administratorFile = new File("ListOfMembers.csv");
    File subscriptionFile = new File("Subscriptions.csv");
    File resultsFile = new File("CompetitiveResults.csv");

    private Team juniorTeam;
    private Team seniorTeam;
    File competitiveResultsFile = new File("CompetitiveResults.csv");

    public MemberDatabase() {
        clubMembers = new ArrayList<>();

        juniorTeam = new Team();
        seniorTeam = new Team();
    }

    public void loadMemberDatabase() {
        try {
            clubMembers = FileHandler.clubMembersLoad(administratorFile, subscriptionFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < clubMembers.size(); i++) {
            //TODO make member type ENUM
            if (clubMembers.get(i).getType() == MemberType.COMPETITIVE) {
                int age = clubMembers.get(i).getAge();
                if (age < 18) {
                    juniorTeam.addMember(clubMembers.get(i));
                } else {
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

    public void addMemberToList(MemberType type,
                                String name,
                                LocalDate birthDate,
                                String email) {

        Member newMember;

        switch (type) {
            case PASSIVE -> newMember = new PassiveMember(name, birthDate, email);

            case EXERCISE -> newMember = new ExerciseMember(name, birthDate, email);

            case COMPETITIVE -> newMember = new CompetitiveMember(name, birthDate, email);
            default -> newMember = null;
        }
        clubMembers.add(newMember);
        try {
            FileHandler.saveAll(clubMembers, administratorFile, subscriptionFile, resultsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //adds to competitive teams based on age
        if (newMember.getType() == MemberType.COMPETITIVE) {
            int age = newMember.getAge();
            if (age < 18) {
                juniorTeam.addMember(newMember);
            } else {
                seniorTeam.addMember(newMember);
            }
        }
    }

    public String showListOfMembers(boolean withNumbers) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Member member : clubMembers) {
            if (member != null) {

                if (withNumbers) {
                    sb.append(count).append(". Name: ").append(member.getName()).append(" - Membership type: ").
                            append(member.getType()).append(" - Date of birth: ").append(member.getBirthDate()).
                            append(" - Email address: ").append(member.getEmail()).append("\n");
                    count++;
                } else {
                    sb.append("Name: ").append(member.getName()).append(" - Membership type: ").
                            append(member.getType()).append(" - Date of birth: ").append(member.getBirthDate()).
                            append(" - Email address: ").append(member.getEmail()).append("\n");
                }
            } else sb.append("Empty");
        }
        return sb.toString();
    }

    public String showListOfSubscription() {
        StringBuilder sb = new StringBuilder();

        for (Member member : clubMembers) {
            int age = member.getAge();

            sb.append("Name: ").append(member.getName()).append(" - ").
                    append("Age: ").append(age).append(" - ").
                    append("Activity type: ").append(member.getType()).append(" - ").
                    append(member.getSubscription()).append("\n");
        }
        return sb.toString();
    }


    public double showIncomeForecast() {
        double totalExpectedIncome = 0;
        for (Member income : clubMembers) {
            totalExpectedIncome += income.getSubscriptionCost();
        }
        return totalExpectedIncome;
    }


    public Team getJuniorTeam() {
        return juniorTeam;
    }

    public Team getSeniorTeam() {
        return seniorTeam;
    }

    public void loadCompetitiveResults() {
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
            if (member != null) {
                //takes birthdate of member with matching email and uses it to figure out which of the two teams
                //that the result should be added to
                //TODO make age comparison better, since it is currently just based on year comparison
                int age = member.getAge();
                if (age < 18) {
                    juniorTeam.addResultToLeaderboard(loadedResults.get(i));
                } else {
                    seniorTeam.addResultToLeaderboard(loadedResults.get(i));
                }
            }
        }
    }

    public void addResultToMemberByIndex (int memberIndex, double time, Discipline discipline, LocalDate date) {
        ArrayList<CompetitiveMember> compMembers = getCompetitiveMembers();
        CompetitiveMember compMember = compMembers.get(memberIndex);
        compMember.addResult(
                new Result(compMember.getEmail(), time, discipline, date),
                discipline);

    }


    private ArrayList<CompetitiveMember> getCompetitiveMembers() {
        ArrayList<CompetitiveMember> compMembers = new ArrayList<>();
        for (Member member: clubMembers) {
            if (member instanceof CompetitiveMember compMember) {
                compMembers.add(compMember);
            }
        }
        return compMembers;
    }
    public int getSizeOfClubMembers() {
        return clubMembers.size();
    }

    public String getMemberName(int memberIndex) {
        return clubMembers.get(memberIndex).getName();
    }

    public void deleteMember(int memberIndex) {
        clubMembers.remove(memberIndex);
        try {
            FileHandler.saveAll(clubMembers, administratorFile, subscriptionFile, resultsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
