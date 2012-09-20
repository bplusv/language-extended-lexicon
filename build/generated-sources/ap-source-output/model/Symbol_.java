package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Definition;
import model.Document;
import model.Log;
import model.Project;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-20T12:04:32")
@StaticMetamodel(Symbol.class)
public class Symbol_ { 

    public static volatile SingularAttribute<Symbol, Integer> id;
    public static volatile SingularAttribute<Symbol, Project> project;
    public static volatile SingularAttribute<Symbol, Document> document;
    public static volatile SingularAttribute<Symbol, Definition> definition;
    public static volatile SingularAttribute<Symbol, String> name;
    public static volatile SingularAttribute<Symbol, Boolean> active;
    public static volatile CollectionAttribute<Symbol, Log> logCollection;

}