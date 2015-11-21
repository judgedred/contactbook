package com.raikiri.contactbook.web;


import com.raikiri.contactbook.domain.Person;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


public class ContactApplication extends Application
{
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>(2);
        set.add(org.glassfish.jersey.moxy.json.MoxyJsonFeature.class);
        set.add(com.raikiri.contactbook.web.JsonMoxyConfigurationContextResolver.class);
        set.add(MOXyJsonProvider.class);
        set.add(PersonController.class);
        return set;
    }
}
