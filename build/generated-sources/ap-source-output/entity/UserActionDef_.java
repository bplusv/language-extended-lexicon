package entity;

import entity.UserLog;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-09T22:47:04")
@StaticMetamodel(UserActionDef.class)
public class UserActionDef_ { 

    public static volatile SingularAttribute<UserActionDef, Integer> id;
    public static volatile SingularAttribute<UserActionDef, String> name;
    public static volatile CollectionAttribute<UserActionDef, UserLog> userLogCollection;

}