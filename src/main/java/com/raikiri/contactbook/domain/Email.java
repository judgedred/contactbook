package com.raikiri.contactbook.domain;

import javax.persistence.*;

@Entity
@Table(name = "Email")
public class Email
{
    @Id
    @Column(name = "email_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer emailId;

    @Column(name = "email_type")
    private String emailType;

    @Column(name = "email_value")
    private String emailValue;

    @Column(name = "default_flag")
    private Boolean emailDefault;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Integer getEmailId()
    {
        return emailId;
    }

    public void setEmailId(Integer emailId)
    {
        this.emailId = emailId;
    }

    public String getEmailType()
    {
        return emailType;
    }

    public void setEmailType(String emailType)
    {
        this.emailType = emailType;
    }

    public String getEmailValue()
    {
        return emailValue;
    }

    public void setEmailValue(String emailValue)
    {
        this.emailValue = emailValue;
    }

    public Boolean getEmailDefault()
    {
        return emailDefault;
    }

    public void setEmailDefault(Boolean emailDefault)
    {
        this.emailDefault = emailDefault;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }

        Email email = (Email) o;

        if(!emailId.equals(email.emailId))
        {
            return false;
        }
        if(!emailType.equals(email.emailType))
        {
            return false;
        }
        if(!emailValue.equals(email.emailValue))
        {
            return false;
        }
        if(!emailDefault.equals(email.emailDefault))
        {
            return false;
        }
        return person.equals(email.person);

    }

    @Override
    public int hashCode()
    {
        return emailId.hashCode();
    }
}
