package com.raikiri.contactbook.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Person")
public class Person
{
    @Id
    @Column(name = "person_id")
    @GeneratedValue
    private Integer personId;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "person_surname")
    private String personSurname;

    @Column(name = "person_patronymic")
    private String personPatronymic;

    @Column(name = "birthday")
    private Date birthday;

    public Integer getPersonId()
    {
        return personId;
    }

    public void setPersonId(Integer personId)
    {
        this.personId = personId;
    }

    public String getPersonName()
    {
        return personName;
    }

    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

    public String getPersonSurname()
    {
        return personSurname;
    }

    public void setPersonSurname(String personSurname)
    {
        this.personSurname = personSurname;
    }

    public String getPersonPatronymic()
    {
        return personPatronymic;
    }

    public void setPersonPatronymic(String personPatronymic)
    {
        this.personPatronymic = personPatronymic;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
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

        Person person = (Person) o;

        if(!personId.equals(person.personId))
        {
            return false;
        }
        if(!personName.equals(person.personName))
        {
            return false;
        }
        if(!personSurname.equals(person.personSurname))
        {
            return false;
        }
        if(!personPatronymic.equals(person.personPatronymic))
        {
            return false;
        }
        return birthday.equals(person.birthday);

    }

    @Override
    public int hashCode()
    {
        return personId.hashCode();
    }
}
