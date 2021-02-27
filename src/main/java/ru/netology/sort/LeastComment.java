package ru.netology.sort;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class LeastComment implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getCountComment() - o2.getCountComment();
    }
}
