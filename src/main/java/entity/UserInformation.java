package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "UserInformation")
@Table(name = "userInformation", catalog = "assignment2")
public class UserInformation implements Serializable {
//    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "account"))
//    @Id
//    @GeneratedValue(generator = "generator")
//    @Column(name = "ACCOUNT_ID", unique = true, nullable = false)
//    private int accountId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Id
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "FULL_NAME", length = 200)
    private String fullName;

    @Column(name = "GENDER")
    private int gender;

    @Column(name = "BIRTHDAY")
    private long birthday;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "SALT")
    private String salt;

    @Column(name = "ADDRESS", length = 200)
    private String address;

    @Column(name = "UPDATED_AT")
    private long UpdatedAt;

    public UserInformation() {
    }

    public UserInformation( Account account, String email, String fullName, int gender, long birthday, String phone, String salt, String address, long updatedAt) {
        //this.accountId = accountId;
        this.account = account;
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.salt = salt;
        this.address = address;
        UpdatedAt = updatedAt;
    }

//    public int getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountId(int accountId) {
//        this.accountId = accountId;
//    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        UpdatedAt = updatedAt;
    }
}
