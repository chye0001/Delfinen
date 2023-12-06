package domain_model.members;

import domain_model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class CompetitiveMember extends Member {
    private MemberType type = MemberType.COMPETITIVE;
    private Result[] resultsBackstroke = new Result[5];
    private Result[] resultsBreaststroke = new Result[5];
    private Result[] resultsButterfly = new Result[5];
    private Result[] resultsCrawl = new Result[5];

    public CompetitiveMember(String name, LocalDate birthDate, String email) {
        super(name, birthDate, email);
        setSubscriptionByTypeAndAge();
    }

    public CompetitiveMember(String name, LocalDate birthDate, String email, Subscription subscription) {
        super(name, birthDate, email, subscription);
    }

    public CompetitiveMember(String name, LocalDate birthDate, String email, String discipline, double subscriptionValue) {
        super(name, birthDate, email, discipline, subscriptionValue);
    }


    public void addResult(Result result) {
        Discipline discipline = result.getDiscipline();
        Result[] results = switch (discipline) {
            case BACKSTROKE -> resultsBackstroke;
            case BREASTSTROKE -> resultsBreaststroke;
            case BUTTERFLY -> resultsButterfly;
            case CRAWL -> resultsCrawl;
        };

        for (int i = 4; i >= 0; i--) {
            if (results[i] == null) {
                results[i] = result;
                Arrays.sort(results, new ResultTimeComparator());
                break;
            } else {
                if (results[i].compareTo(result) < 0) {
                    results[i] = result;
                    Arrays.sort(results,new ResultTimeComparator());
                }
            }
        }
    }


    private boolean listContainsNull(Object[] list) {
        for (Object o : list) {
            if (o == null) {
                return true; // Array indeholder null
            }
        }
        return false; // Array indeholder ikke null
    }

    public ArrayList<Result> getAllResults() {
        ArrayList<Result> results = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Result back = resultsBackstroke[i];
            Result breast = resultsBreaststroke[i];
            Result fly = resultsButterfly[i];
            Result crawl = resultsCrawl[i];
            if (back != null) results.add(back);
            if (breast != null) results.add(breast);
            if (fly != null) results.add(fly);
            if (crawl != null) results.add(crawl);
        }
        return results;
    }
    @Override
    public MemberType getType() {
        return type;
    }

    public void setResults(ArrayList<Result> results) {
        for(Result result : results) {
            if (result != null) addResult(result);
        }
    }
}
