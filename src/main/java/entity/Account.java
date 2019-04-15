package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account", catalog = "assignment3")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID", unique = true, nullable = false)
    private int accountId;
    @Column(name = "USERNAME",unique = true, nullable = false, length = 50)
    private String username;
    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
    private UserInformation userInformation;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "acc_role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "accounts")
    private Collection<Role> roles = new ArrayList<Role>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Collection<Feedback> feedbacks = new ArrayList<Feedback>();

    //private Set<Role> roles = new HashSet<Role>(0);


    //private Set<Feedback> feedbacks = new HashSet<Feedback>(0);

    @Column(name = "STATUS", nullable = false)
    private int status;
    @Column(name = "CREATED_AT", nullable = false)
    private long createdAt;
    @Column(name = "UPDATED_AT", nullable = true)
    private long updatedAt;


    public Account() {
    }



    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "accounts")
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
//    public Set<Feedback> getFeedbacks() {
//        return feedbacks;
//    }
//
//    public void setFeedbacks(Set<Feedback> feedbacks) {
//        this.feedbacks = feedbacks;
//    }


    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Collection<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
