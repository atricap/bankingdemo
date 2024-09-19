package de.atricap.bankingdemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Phone> phones;

    @OneToMany(fetch = LAZY, cascade = ALL, orphanRemoval = true, mappedBy = "customer")
    private List<Account> accounts;

    @Column(name = "business_customer")
    private boolean businessCustomer;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    public Customer() {
    }

    public Customer(String fullName, String email, boolean businessCustomer, String userName, String password, boolean enabled) {
        this.fullName = fullName;
        this.email = email;
        this.businessCustomer = businessCustomer;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        return Collections.unmodifiableList(phones != null ? phones : Collections.emptyList());
    }

    public void addPhone(Phone phone) {
        if (phones == null) {
            phones = new ArrayList<>();
        }
        phones.add(phone);
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts != null ? accounts : Collections.emptyList());
    }

    public void addAccount(Account account) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        accounts.add(account);
    }

    public boolean isBusinessCustomer() {
        return businessCustomer;
    }

    public void setBusinessCustomer(boolean businessCustomer) {
        this.businessCustomer = businessCustomer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", businessCustomer=" + businessCustomer +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
