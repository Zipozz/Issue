package ru.netology.sort;

import ru.netology.domain.Issue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class LastUpdate implements Comparator<Issue> {
    Date data1;
    Date data2;

    @Override
    public int compare(Issue o1, Issue o2) {
        String date1 = o1.getUpdateDate();
        String date2 = o2.getUpdateDate();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            data1 = format.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            data2 = format.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return data1.compareTo(data2);
    }

}
