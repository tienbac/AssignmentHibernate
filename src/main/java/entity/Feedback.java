package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Entity(name = "Fedbacks")
@Table(name = "feedback", catalog = "assignment2", uniqueConstraints = @UniqueConstraint(columnNames = "FEEDBACK_ID"))
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEEDBACK_ID", unique = true, nullable = false)
    private int feedbackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;
    @Column(name = "CONTENT", nullable = false, length = 2000)
    private String content;
    @Column(name = "CREATED_AT", nullable = false)
    private long createdAt;
    @Column(name = "UPDATED_AT", nullable = true)
    private long updatedAt;
    @Column(name = "STATUS", nullable = false)
    private int status;

    HashMap<String, ArrayList<String>> errors = new HashMap<String, ArrayList<String>>();

    public Feedback() {
    }

    public Feedback(Account account, String title, String content) {
        this.account = account;
        this.title = title;
        this.content = content;
    }

    public Feedback(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Feedback(Account account, String title, String content, long createdAt, long updatedAt, int status) {
        this.account = account;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", account=" + account +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                ", errors=" + errors +
                '}';
    }

    public boolean isValid(){
        validate();
        return this.errors.size() == 0;
    }

    private void validate() {
        if (this.errors == null) {
            this.errors = new HashMap<String, ArrayList<String>>();
        }
        ArrayList<String> titleError = this.errors.get("title");
        if (titleError == null) {
            titleError = new ArrayList<String>();
        }
        if (this.content == null || this.content.length() == 0) {
            titleError.add("Title is required!!!");
        }
        if (titleError.size() > 0) {
            this.errors.put("title",titleError);
        }

        ArrayList<String> contentError = this.errors.get("content");

        if (contentError == null) {
            contentError = new ArrayList<String>();
        }
        if (this.content == null || this.content.length() == 0) {
            contentError.add("Content is required!!!");
        }
        if (contentError.size() > 0) {
            this.errors.put("content",contentError);
        }
    }

    public HashMap<String, ArrayList<String>> getErrors() {
        return errors;
    }
}
