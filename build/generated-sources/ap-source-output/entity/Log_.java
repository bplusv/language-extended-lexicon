package entity;

import entity.Concept;
import entity.Event;
import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-21T10:45:10")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, Integer> id;
    public static volatile SingularAttribute<Log, Concept> concept;
    public static volatile SingularAttribute<Log, Event> event;
    public static volatile SingularAttribute<Log, Date> date;
    public static volatile SingularAttribute<Log, User> user;

}