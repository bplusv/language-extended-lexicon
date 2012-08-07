package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Log;
import model.Project;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-08-07T12:36:00")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, Boolean> admin;
    public static volatile SingularAttribute<User, String> name;
    public static volatile CollectionAttribute<User, Log> logCollection;
    public static volatile CollectionAttribute<User, Project> projectCollection;
    public static volatile SingularAttribute<User, String> password;

}