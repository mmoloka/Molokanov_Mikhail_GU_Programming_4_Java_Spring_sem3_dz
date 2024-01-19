package org.example.sem3_dz.api;

import lombok.Getter;
import org.example.sem3_dz.model.Issue;
import org.example.sem3_dz.service.IssueService;

import java.time.LocalDateTime;

@Getter
public class IssueTable {
    private final String bookName;
    private final String readerName;
    private final LocalDateTime timestamp;


    public IssueTable (Issue issue, IssueService issueService){
        this.bookName = issueService.getNameOfBook(issue);
        this.readerName = issueService.getNameOfReader(issue);
        this.timestamp = issue.getTimestamp();
    }

}
