package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Issue {
    public int id;
    private int countComment;
    private boolean status;
    private Set<Label> label;
    private Set<Author> assignee;
    private Author author;
    private String creationDate;
    private String updateDate;
    private String project;
    private String milestones;
}
