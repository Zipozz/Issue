package ru.netology.sort;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class MostPopularComment implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o2.getCountComment() - o1.getCountComment();
    }
}
