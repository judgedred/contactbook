package com.raikiri.contactbook.domain;

import javax.persistence.*;

@Entity
@Table(name = "Phone")
public class Phone
{
    @Id
    @Column(name = "phone_id")
    @GeneratedValue
    private Integer phoneId;

    @Column(name = "phone_type")
    private String phoneType;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "default_flag")
    private Boolean defaultFlag;

    @ManyToOne
    @JoinColumn(name = "person_id")
        private Person person;
}
