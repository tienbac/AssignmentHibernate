package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL, optional = false)
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


    HashMap<String, ArrayList<String>> errors = new HashMap<String, ArrayList<String>>();

    public Account() {
        this.username = "";
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, UserInformation userInformation, Collection<Role> roles, Collection<Feedback> feedbacks, int status, long createdAt, long updatedAt) {
        this.username = username;
        this.password = password;
        this.userInformation = userInformation;
        this.roles = roles;
        this.feedbacks = feedbacks;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userInformation=" + userInformation +
                ", roles=" + roles +
                ", feedbacks=" + feedbacks +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", errors=" + errors +
                '}';
    }

    public boolean isValid(){
        validate();
        return this.errors.size() == 0;
    }

    private static boolean isNumber(String string) {
        return string.matches(".*[0-9].*");
    }
    private static boolean isString(String string) {
        return string.matches(".*[a-zA-Z].*");
    }

    private void validate() {
        if (this.errors == null) {
            this.errors = new HashMap<String, ArrayList<String>>();
        }
        ArrayList<String> userNameError = this.errors.get("username");
        ArrayList<String> passwordError = this.errors.get("password");

        if (userNameError == null) {
            userNameError = new ArrayList<String>();
        }
        if (this.username == null || this.username.length() == 0) {
            userNameError.add("Username is required!!!");
        }
        if (this.username.length() < 2 || this.username.length() > 30) {
            userNameError.add("Username must be between 2 and 30 character");
        }
        if (userNameError.size() > 0) {
            this.errors.put("username", userNameError);
        }

        if (passwordError == null) {
            passwordError = new ArrayList<String>();
        }
        if (this.password == null || this.password.length() == 0) {
            passwordError.add("Password is required!!!");

        }
        if (this.password.length() < 2 || this.password.length() > 30) {
            passwordError.add("Password must be between 6 and 8 character");
        }
        if (!isNumber(this.password) && !isString(this.password)) {
            passwordError.add("Password must have character and number");
        }

        if (passwordError.size() > 0) {
            this.errors.put("password", passwordError);
        }
    }

    public HashMap<String, ArrayList<String>> getErrors() {
        return this.errors;
    }
}
