package de.atricap.bankingdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @Column(name = "phonenumber")
    private long number;

    @Column(name = "purpose")
    private String purpose;

    public Phone() {
    }

    public Phone(long number, String purpose) {
        this.number = number;
        this.purpose = purpose;
    }

    public long getNumber() {
        return number;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "+%d (%s)".formatted(number, purpose);
    }
}
