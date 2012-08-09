package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Event;
import model.Symbol;
import model.User;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-08-08T13:28:13")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, Integer> id;
    public static volatile SingularAttribute<Log, Symbol> symbol;
    public static volatile SingularAttribute<Log, Event> event;
    public static volatile SingularAttribute<Log, Date> date;
    public static volatile SingularAttribute<Log, User> user;

}