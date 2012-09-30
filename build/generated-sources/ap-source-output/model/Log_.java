package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Event;
import model.Symbol;
import model.User;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-30T13:38:28")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, Integer> id;
    public static volatile SingularAttribute<Log, Symbol> symbol;
    public static volatile SingularAttribute<Log, Event> event;
    public static volatile SingularAttribute<Log, Date> date;
    public static volatile SingularAttribute<Log, User> user;

}