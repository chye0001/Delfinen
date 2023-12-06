package domain_model;

import java.util.Comparator;

public class ResultTimeComparator implements Comparator<Result> {
    @Override
    public int compare(Result o1, Result o2) {
        if (o2 == null) return 1;
        int compareTime = Double.compare(o1.getTime(), o2.getTime());
        if (compareTime == 0) {
            return o1.getDate().compareTo(o2.getDate());
        } else return compareTime;
    }
}
