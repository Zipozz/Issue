package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Author;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.sort.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private Repository repository = new Repository();
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
    private Issue issue5 = new Issue(5, 1, true, labels, assignees, author5, "15.06.19", "03.11.20", "something big", "General Backlog");


    @Nested
    class MultipleIssue {
        @BeforeEach
        public void setting() {
            labels.add(label1);
            labels.add(label2);
            labels.add(label3);
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
        void shouldOutputListAllOpenIssue() {
            List<Issue> actual = repository.findOpen(repository.getAll());
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue3);
            expected.add(issue5);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOutputListAllCloseIssue() {
            List<Issue> actual = repository.findClose(repository.getAll());
            List<Issue> expected = new ArrayList<>();
            expected.add(issue2);
            expected.add(issue4);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpenIssue() {
            repository.openById(repository.getAll(), 2);
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);
            expected.add(issue4);
            expected.add(issue5);
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseIssue() {
            repository.closeById(repository.getAll(), 1);
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);
            expected.add(issue4);
            expected.add(issue5);
            assertEquals(expected, actual);
        }

        @Test
        void shouldMostComment() {
            Collections.sort(repository.getAll(), new MostPopularComment());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue4);
            expected.add(issue2);
            expected.add(issue1);
            expected.add(issue3);
            expected.add(issue5);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortLeastComment() {
            Collections.sort(repository.getAll(), new LeastComment());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue5);
            expected.add(issue3);
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue4);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationNew() {
            Collections.sort(repository.getAll(), new NewSort());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue2);
            expected.add(issue1);
            expected.add(issue5);
            expected.add(issue4);
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationOld() {
            Collections.sort(repository.getAll(), new OldSort());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            expected.add(issue4);
            expected.add(issue5);
            expected.add(issue1);
            expected.add(issue2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateNew() {
            Collections.sort(repository.getAll(), new RecentlyUpdated());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue4);
            expected.add(issue2);
            expected.add(issue5);
            expected.add(issue3);
            expected.add(issue1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateOld() {
            Collections.sort(repository.getAll(), new LastUpdate());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue3);
            expected.add(issue5);
            expected.add(issue2);
            expected.add(issue4);
            assertEquals(expected, actual);
        }

    }

    @Nested
    class Empty {
        @Test
        void shouldOutputListAllOpenIssue() {
            List<Issue> actual = repository.findOpen(repository.getAll());
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOutputListAllCloseIssue() {
            List<Issue> actual = repository.findClose(repository.getAll());
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpenIssue() {
            repository.openById(repository.getAll(), 2);
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseIssue() {
            repository.closeById(repository.getAll(), 1);
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortMostComment() {
            Collections.sort(repository.getAll(), new MostPopularComment());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortLeastComment() {
            Collections.sort(repository.getAll(), new LeastComment());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationNew() {
            Collections.sort(repository.getAll(), new NewSort());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationOld() {
            Collections.sort(repository.getAll(), new OldSort());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateNew() {
            Collections.sort(repository.getAll(), new RecentlyUpdated());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateOld() {
            Collections.sort(repository.getAll(), new LastUpdate());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class SingleIssue {
        @BeforeEach
        public void setting() {
            repository.add(issue3);
        }

        @Test
        void shouldOutputListAllOpenIssue() {
            List<Issue> actual = repository.findOpen(repository.getAll());
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOutputListAllCloseIssue() {
            List<Issue> actual = repository.findClose(repository.getAll());
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpenIssue() {
            repository.openById(repository.getAll(), 3);
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseIssue() {
            repository.closeById(repository.getAll(), 3);
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortMostComment() {
            Collections.sort(repository.getAll(), new MostPopularComment());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortLeastComment() {
            Collections.sort(repository.getAll(), new LeastComment());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationNew() {
            Collections.sort(repository.getAll(), new NewSort());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationOld() {
            Collections.sort(repository.getAll(), new OldSort());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateNew() {
            Collections.sort(repository.getAll(), new RecentlyUpdated());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateOld() {
            Collections.sort(repository.getAll(), new LastUpdate());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            assertEquals(expected, actual);
        }
    }
}
