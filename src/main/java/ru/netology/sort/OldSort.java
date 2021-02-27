package ru.netology.sort;

import ru.netology.domain.Issue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class OldSort implements Comparator<Issue> {
    Date date1;
    Date date2;

    @Override
    public int compare(Issue o1, Issue o2) {
        String dat1 = o1.getCreationDate();
        String dat2 = o2.getCreationDate();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            date1 = format.parse(dat1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date2 = format.parse(dat2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date1.compareTo(date2);
    }
}
