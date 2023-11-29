package domain_model;

public class Controller {

    MemberDatabase memberDatabase = new MemberDatabase();
    public Controller(){
        memberDatabase.loadMemberDatabase();
        memberDatabase.loadCompetitiveResults();
    }
    public boolean addMemberToList(int type,
                                   String name,
                                   String birthDate,
                                   String email,
                                   String discipline,
                                   double subscription) {

        return memberDatabase.addMemberToList(type, name, birthDate, email, discipline, subscription);
    }

    public void addResultToTeam(String email, double time){
        memberDatabase.addResultToTeam(email,time);
    }

    public String showListOfMembers () {
        return memberDatabase.showListOfMembers();
    }

    public String showListOfSubscriptions(){
        return memberDatabase.showListOfSubscription();
    }
    public double showIncomeForecast() {
        return memberDatabase.showIncomeForecast();
    }

    public Team getJuniorTeam(){
        return memberDatabase.getJuniorTeam();
    }

    public Team getSeniorTeam(){
        return memberDatabase.getSeniorTeam();
    }
}
