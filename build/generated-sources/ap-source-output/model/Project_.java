package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Document;
import model.Symbol;
import model.User;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-26T15:52:13")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, Integer> id;
    public static volatile SingularAttribute<Project, String> description;
    public static volatile SingularAttribute<Project, String> name;
    public static volatile CollectionAttribute<Project, Symbol> symbolCollection;
    public static volatile CollectionAttribute<Project, User> userCollection;
    public static volatile CollectionAttribute<Project, Document> documentCollection;

}