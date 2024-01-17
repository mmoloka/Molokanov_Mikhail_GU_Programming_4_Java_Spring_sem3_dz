package org.example.sem3_dz.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.example.sem3_dz.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        issues.addAll(List.of(
                new Issue(1, 1),
                new Issue(3, 2),
                new Issue(2, 3),
                new Issue(3, 1)
        ));
    }

    public void save(Issue issue) {
        // insert into ....
        issues.add(issue);
    }

    public Issue getIssueById(long id) {
        return issues.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<Issue> getAllIssues() {
        return issues;
    }

}
