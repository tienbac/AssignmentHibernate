package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Role")
@Table(name = "role", catalog = "assignment2", uniqueConstraints = {@UniqueConstraint(columnNames = "ROLE_ID"), @UniqueConstraint(columnNames = "ROLE_NAME")})
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID", unique = true, nullable = false)
    private int roleId;
    @Column(name = "ROLE_NAME",unique = true, length = 20)
    private String name;
    @Column(name = "ROLE_DESC", length = 100, nullable = true)
    private String description;
//    @ManyToMany(mappedBy = "role")

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "role_account", joinColumns = {@JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "ACCOUNT_ID", nullable = false, updatable = false)})
    private Collection<Account> accounts = new ArrayList<Account>();

    //private Set<Account> accounts = new HashSet<Account>(0);


    @Column(name = "ROLE_STATUS", nullable = false)
    private int status;
    @Column(name = "CREATED_AT", nullable = false)
    private long createdAt;
    @Column(name = "UPDATED_AT", nullable = true)
    private long updatedAt;

    public Role() {
    }

    public Role(String name, String description, Collection<Account> accounts, int status, long createdAt, long updatedAt) {
        this.name = name;
        this.description = description;
        this.accounts = accounts;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    //    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "role_account", joinColumns = {@JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "ACCOUNT_ID", nullable = false, updatable = false)})
//    public Set<Account> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(Set<Account> accounts) {
//        this.accounts = accounts;
//    }

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
