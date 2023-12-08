package domain_model;

import datasource.FileHandler;
import domain_model.members.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class MemberDatabase {

    private ArrayList<Member> clubMembers;
    File administratorFile = new File("ListOfMembers.csv");
    File subscriptionFile = new File("Subscriptions.csv");
    File resultsFile = new File("CompetitiveResults.csv");

    private Team juniorTeam;
    private Team seniorTeam;

    public MemberDatabase() {
        clubMembers = new ArrayList<>();

        juniorTeam = new Team();
        seniorTeam = new Team();
    }

    public void loadMemberDatabase() {
        try {
            clubMembers = FileHandler.clubMembersLoad(administratorFile, subscriptionFile, resultsFile);
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

        addsToTeamBasedOnAge(newMember);

    }

    private void addsToTeamBasedOnAge(Member newMember) {
        if (newMember.getType() == MemberType.COMPETITIVE) {
            int age = newMember.getAge();
            if (age < 18) {
                juniorTeam.addMember(newMember);
            } else {
                seniorTeam.addMember(newMember);
            }
        }
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

    public void changePaymentStatus(int accountantChoise) {
        Member member = getClubMembers().get(accountantChoise - 1);
        System.out.println(member.getName());
        member.pay();
        try {
            FileHandler.saveAll(clubMembers, administratorFile, subscriptionFile, resultsFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String showIncomeForecast() {
        double totalExpectedIncome = 0;
        double duePaymentTotal = 0;
        int notPaid = 0;
        for (Member paymentStatus : clubMembers) {

            if (paymentStatus.getSubscription().isPaid()) {
                totalExpectedIncome += paymentStatus.getSubscriptionCost();

            } else {
                notPaid++;
                duePaymentTotal += paymentStatus.getSubscriptionCost();
            }
        }
        return "Expected 1 year revenue: " + totalExpectedIncome + "kr\n" +
                (notPaid) + " are due for payment totaling " + duePaymentTotal + "kr";
    }


    public Team getJuniorTeam() {
        return juniorTeam;
    }

    public Team getSeniorTeam() {
        return seniorTeam;
    }

    public void addResultToMemberByIndex (int memberIndex, double time, Discipline discipline, LocalDate date) {
        ArrayList<CompetitiveMember> compMembers = getCompetitiveMembers();
        CompetitiveMember compMember = compMembers.get(memberIndex);
        compMember.addResult(
                new Result(compMember.getEmail(), time, discipline, date));

    }


    public ArrayList<CompetitiveMember> getCompetitiveMembers() {
        ArrayList<CompetitiveMember> compMembers = new ArrayList<>();
        for (Member member : clubMembers) {
            if (member instanceof CompetitiveMember compMember) {
                compMembers.add(compMember);
            }
        }
        return compMembers;
    }

    public int getSizeOfAllMembers() {
        return clubMembers.size();
    }

    public int getSizeOfCompMembers() {
        return getCompetitiveMembers().size();
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

    public void editMember(int index, Member member) {
        clubMembers.set(index, member);
        try {
            FileHandler.saveAll(clubMembers, administratorFile, subscriptionFile, resultsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String showLeaderBoard(int chosenTeam, Discipline disciplinType) {
        if (chosenTeam == 1) {       //1 == Junior Team
            return buildsLeaderBoardForJuniorTeam(disciplinType);

        } else                       //2 == Senior Team
            return buildsLeaderBoardForSeniorTeam(disciplinType);
    }

    public String buildsLeaderBoardForJuniorTeam(Discipline disciplinType) {
        String leaderBoardTop5 = "";
        int count = 1;
        ArrayList<Result> allResultsCombined = combineAllResults();

        if (allResultsCombined.isEmpty()) {
            return "\nNo results have been added to the leaderboard";

        } else
            Collections.sort(allResultsCombined, new ResultTimeComparator());

        leaderBoardTop5 += findsTopFiveJuniorResults(allResultsCombined, disciplinType, count, leaderBoardTop5);
        return leaderBoardTop5;
    }

    private String findsTopFiveJuniorResults(ArrayList<Result> allResultsCombined,
                                             Discipline disciplinType,
                                             int count,
                                             String leaderBoardTop5) {

        ArrayList<String> isMemberAlreadyOnLeaderboard = new ArrayList<>();

        for (Result result : allResultsCombined) {
            for (CompetitiveMember competitiveMember : getCompetitiveMembers()) {

                if (result.getDiscipline() == disciplinType &&
                        result.getMemberEmail().equals(competitiveMember.getEmail()) &&
                        competitiveMember.getAge() < 18 && !isMemberAlreadyOnLeaderboard.contains(competitiveMember.getEmail())) {

                    isMemberAlreadyOnLeaderboard.add(competitiveMember.getEmail());
                    leaderBoardTop5 += buildLeaderBoard(count, competitiveMember, result);
                    count++;

                    if (count > 5) {
                        return leaderBoardTop5;
                    }
                }
            }
        }
        if (leaderBoardTop5.equalsIgnoreCase("")){
            return "No junior competitive swimmers where found for the discipline";
        }
        return leaderBoardTop5;
    }

    public String buildsLeaderBoardForSeniorTeam(Discipline disciplinType) {
        String leaderBoardTop5 = "";
        int count = 1;

        ArrayList<Result> allResultsCombined = combineAllResults();
        if (allResultsCombined.isEmpty()) {
            return "\nNo results have been added to the leaderboard";

        } else
            Collections.sort(allResultsCombined, new ResultTimeComparator());

        leaderBoardTop5 += findsTopFiveSeniorResults(
                allResultsCombined,
                disciplinType,
                leaderBoardTop5,
                count);

        return leaderBoardTop5;
    }

    private String findsTopFiveSeniorResults(ArrayList<Result> allResultsCombined,
                                             Discipline disciplinType,
                                             String leaderBoardTop5,
                                             int count) {

        ArrayList<String> isMemberAlreadyOnLeaderboard = new ArrayList<>();

        for (Result result : allResultsCombined) {
            for (CompetitiveMember competitiveMember : getCompetitiveMembers()) {

                if (result.getDiscipline() == disciplinType) {
                    if (result.getMemberEmail().equals(competitiveMember.getEmail()) && !isMemberAlreadyOnLeaderboard.contains(competitiveMember.getEmail())) {
                        isMemberAlreadyOnLeaderboard.add(competitiveMember.getEmail());
                        leaderBoardTop5 += buildLeaderBoard(count, competitiveMember, result);

                        count++;

                        if (count > 5) {
                            return leaderBoardTop5;
                        }
                    }
                }
            }
        }
        if (leaderBoardTop5.equalsIgnoreCase("")){
            return "No senior competitive swimmers where found for the discipline";
        }
        return leaderBoardTop5;
    }

    private ArrayList<Result> combineAllResults() {
        ArrayList<Result> allResultsCombined = new ArrayList<>();
        for (CompetitiveMember member : getCompetitiveMembers()) {
            allResultsCombined.addAll(member.getAllResults());

        }
        return allResultsCombined;
    }

    public String buildLeaderBoard(int count, Member competitiveMember, Result result) {

        int convertsDecimalToWholeNumber = 100;
        double competitiveResultInMillisecondsIsolated = (result.getTime() - (int) result.getTime()) * convertsDecimalToWholeNumber;
        int competitiveResultInSecondsIsolated = (int) result.getTime() % 60;
        int competitiveResultInMinutesIsolated = (int) result.getTime() / 60;

        return isSecondsIsolatedLessThanTenSeconds(competitiveResultInMillisecondsIsolated,
                competitiveResultInSecondsIsolated,
                competitiveResultInMinutesIsolated,
                count,
                competitiveMember,
                result);

    }

    private String isSecondsIsolatedLessThanTenSeconds(double milliseconds, int seconds, int minutes, int count, Member competitiveMember, Result result) {
        StringBuilder sb = new StringBuilder();

        if (seconds < 10) {
            sb.append(count + ": " + competitiveMember.getName() + ": " +
                    minutes + ":0" + seconds);
            sb.append(isResultsInMillisecondsIsolatedLessThanTen(milliseconds));
            sb.append(" - " + result.getDate() + "\n");

        } else {
            sb.append(count + ": " + competitiveMember.getName() + ": " +
                    minutes + ":" + seconds);
            sb.append(isResultsInMillisecondsIsolatedLessThanTen(milliseconds));
            sb.append(" - " + result.getDate() + "\n");
        }

        return sb.toString();
    }

    public String isResultsInMillisecondsIsolatedLessThanTen(double competitiveResultInMilliseconds) {
        if (competitiveResultInMilliseconds < 10) {
            return (".0" + (int) competitiveResultInMilliseconds);
        } else
            return "." + (int) competitiveResultInMilliseconds;
    }
}