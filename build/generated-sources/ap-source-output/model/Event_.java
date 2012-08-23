package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Log;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-08-23T09:24:19")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, Integer> id;
    public static volatile SingularAttribute<Event, String> name;
    public static volatile CollectionAttribute<Event, Log> logCollection;

}