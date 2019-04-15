package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "feedback", catalog = "assignment3", uniqueConstraints = @UniqueConstraint(columnNames = "FEEDBACK_ID"))
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEEDBACK_ID", unique = true, nullable = false)
    private int feedbackId;

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

    public Feedback() {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
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
}
