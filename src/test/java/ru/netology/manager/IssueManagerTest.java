package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Author;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.repository.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    Repository repository = new Repository();
    IssueManager manager = new IssueManager(repository);
    private Author author1 = new Author(1, "Vasya");
    private Author author2 = new Author(2, "Petya");
    private Author author3 = new Author(3, "Katya");
    private Author author4 = new Author(4, "Masha");
    private Author author5 = new Author(5, "Barsik");
    private Label label1 = new Label(1, "bugs are everywhere!");
    private Label label2 = new Label(2, "I do not understand anything");
    private Label label3 = new Label(3, "Help me");
    private Label label4 = new Label(4, "brain is boiling");
    private Label label5 = new Label(5, "Where I am?");
    private Set<Label> labels = new HashSet<>();
    private Set<Author> assignees = new HashSet<>();
    private Issue issue1 = new Issue(1, 9, true, labels, assignees, author2, "12.12.20", "13.04.20", "something big", "General Backlog");
    private Issue issue2 = new Issue(2, 16, false, labels, assignees, author1, "22.12.20", "03.03.21", "something big", "General Backlog");
    private Issue issue3 = new Issue(3, 5, true, labels, assignees, author3, "03.02.19", "12.06.20", "something big", "General Backlog");
    private Issue issue4 = new Issue(4, 23, false, labels, assignees, author4, "11.02.19", "01.07.22", "something big", "General Backlog");
    private Issue issue5 = new Issue(5, 1, true, labels, assignees, author4, "15.06.19", "03.11.20", "something big", "General Backlog");

    @Nested
    class MultipleIssue {
        @BeforeEach
        public void setting() {
            labels.add(label1);
            labels.add(label2);
            labels.add(label4);
            labels.add(label5);
            assignees.add(author2);
            repository.add(issue1);
            repository.add(issue2);
            repository.add(issue3);
            repository.add(issue4);
            repository.add(issue5);
        }

        @Test
        void shouldFilterAuthorNo() {
            List<Issue> actual = manager.filterAuthor(author5);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAuthorYes() {
            List<Issue> actual = manager.filterAuthor(author2);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelNo() {
            List<Issue> actual = manager.filterLabel(label3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelYes() {
            List<Issue> actual = manager.filterLabel(label2);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);
            expected.add(issue4);
            expected.add(issue5);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeNo() {
            List<Issue> actual = manager.filterAssignee(author1);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeYes() {
            List<Issue> actual = manager.filterAssignee(author2);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);
            expected.add(issue4);
            expected.add(issue5);
            assertEquals(expected, actual);
        }
    }


    @Nested
    class Empty {
        @Test
        void shouldFilterAuthorNo() {
            List<Issue> actual = manager.filterAuthor(author5);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelNo() {
            List<Issue> actual = manager.filterLabel(label3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeNo() {
            List<Issue> actual = manager.filterAssignee(author1);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

    }

    @Nested
    class SingleIssue {
        @BeforeEach
        public void setting() {
            labels.add(label1);
            assignees.add(author5);
            manager.add(issue1);



        }

        @Test
        void shouldFilterAuthorNo() {
            List<Issue> actual = manager.filterAuthor(author4);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAuthorYes() {
            List<Issue> actual = manager.filterAuthor(author2);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelNo() {
            List<Issue> actual = manager.filterLabel(label3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelYes() {
            List<Issue> actual = manager.filterLabel(label1);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeNo() {
            List<Issue> actual = manager.filterAssignee(author2);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeYes() {
            List<Issue> actual = manager.filterAssignee(author5);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            assertEquals(expected, actual);
        }

    }
}