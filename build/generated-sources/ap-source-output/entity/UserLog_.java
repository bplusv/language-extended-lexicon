package entity;

import entity.User;
import entity.UserActionDef;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-09T22:47:04")
@StaticMetamodel(UserLog.class)
public class UserLog_ { 

    public static volatile SingularAttribute<UserLog, Integer> id;
    public static volatile SingularAttribute<UserLog, User> userId;
    public static volatile SingularAttribute<UserLog, Date> date;
    public static volatile SingularAttribute<UserLog, UserActionDef> userActionDefId;

}