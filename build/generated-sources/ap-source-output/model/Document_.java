package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Project;
import model.Symbol;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-05T12:28:14")
@StaticMetamodel(Document.class)
public class Document_ { 

    public static volatile SingularAttribute<Document, Integer> id;
    public static volatile SingularAttribute<Document, String> content;
    public static volatile SingularAttribute<Document, Project> project;
    public static volatile SingularAttribute<Document, String> name;
    public static volatile CollectionAttribute<Document, Symbol> symbolCollection;

}