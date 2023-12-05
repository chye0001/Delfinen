package domain_model;

import java.util.Comparator;

public class ResultTimeComparator implements Comparator<Result> {
    @Override
    public int compare(Result result1, Result result2){
        return Double.compare(result1.getTime(), result2.getTime());
    }
}
