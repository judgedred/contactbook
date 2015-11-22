package com.raikiri.contactbook.domain;

import javax.persistence.*;

@Entity
@Table(name = "Phone")
public class Phone
{
    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer phoneId;

    @Column(name = "phone_type")
    private String phoneType;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "default_flag")
    private Boolean phoneDefault;

    @ManyToOne
    @JoinColumn(name = "person_id")
        private Person person;

    public Integer getPhoneId()
    {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId)
    {
        this.phoneId = phoneId;
    }

    public String getPhoneType()
    {
        return phoneType;
    }

    public void setPhoneType(String phoneType)
    {
        this.phoneType = phoneType;
    }

    public Long getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getPhoneDefault()
    {
        return phoneDefault;
    }

    public void setPhoneDefault(Boolean phoneDefault)
    {
        this.phoneDefault = phoneDefault;
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

        Phone phone = (Phone) o;

        if(!phoneId.equals(phone.phoneId))
        {
            return false;
        }
        if(!phoneType.equals(phone.phoneType))
        {
            return false;
        }
        if(!phoneNumber.equals(phone.phoneNumber))
        {
            return false;
        }
        if(!phoneDefault.equals(phone.phoneDefault))
        {
            return false;
        }
        return person.equals(phone.person);

    }

    @Override
    public int hashCode()
    {
        int result = phoneId.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        return result;
    }
}
