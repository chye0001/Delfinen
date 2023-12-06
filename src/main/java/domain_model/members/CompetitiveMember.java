package domain_model.members;

import domain_model.Discipline;
import domain_model.MemberType;

import domain_model.Result;
import domain_model.Subscription;

import java.time.LocalDate;
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


    public void addResult(Result result, Discipline discipline) {
        Result[] results = switch (discipline) {
            case BACKSTROKE -> resultsBackstroke;
            case BREASTSTROKE -> resultsBreaststroke;
            case BUTTERFLY -> resultsButterfly;
            case CRAWL -> resultsCrawl;
        };


        for (int i = 4; i >= 0; i--) {
            if (results[i].compareTo(result) < 0) {
                results[i] = result;
                Arrays.sort(results);
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


    @Override
    public MemberType getType() {
        return type;
    }

}
