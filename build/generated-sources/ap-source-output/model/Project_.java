package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Document;
import model.Symbol;
import model.User;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-07-25T10:25:49")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, Integer> id;
    public static volatile SingularAttribute<Project, String> description;
    public static volatile SingularAttribute<Project, String> name;
    public static volatile CollectionAttribute<Project, Symbol> symbolCollection;
    public static volatile CollectionAttribute<Project, User> userCollection;
    public static volatile CollectionAttribute<Project, Document> documentCollection;

}