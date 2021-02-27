package ru.netology.sort;

import ru.netology.domain.Issue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class RecentlyUpdated implements Comparator<Issue> {
    Date data1;
    Date data2;

    @Override
    public int compare(Issue o1, Issue o2) {
        String dat1 = o1.getUpdateDate();
        String dat2 = o2.getUpdateDate();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            data1 = format.parse(dat1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            data2 = format.parse(dat2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return data2.compareTo(data1);
    }
}