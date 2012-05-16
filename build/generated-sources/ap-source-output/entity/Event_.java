package entity;

import entity.Log;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-15T19:34:13")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, Integer> id;
    public static volatile SingularAttribute<Event, String> name;
    public static volatile CollectionAttribute<Event, Log> logCollection;

}