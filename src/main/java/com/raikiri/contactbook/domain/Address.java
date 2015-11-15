package com.raikiri.contactbook.domain;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address
{
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Column(name = "address_value")
    private String addressValue;

    @Column(name = "default_flag")
    private Boolean defaultFlag;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Integer getAddressId()
    {
        return addressId;
    }

    public void setAddressId(Integer addressId)
    {
        this.addressId = addressId;
    }

    public String getAddressValue()
    {
        return addressValue;
    }

    public void setAddressValue(String addressValue)
    {
        this.addressValue = addressValue;
    }

    public Boolean getDefaultFlag()
    {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag)
    {
        this.defaultFlag = defaultFlag;
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

        Address address = (Address) o;

        if(!addressId.equals(address.addressId))
        {
            return false;
        }
        if(!addressValue.equals(address.addressValue))
        {
            return false;
        }
        if(!defaultFlag.equals(address.defaultFlag))
        {
            return false;
        }
        return person.equals(address.person);

    }

    @Override
    public int hashCode()
    {
        return addressId.hashCode();
    }
}
