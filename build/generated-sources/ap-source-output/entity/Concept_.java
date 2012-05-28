package entity;

import entity.Definition;
import entity.Document;
import entity.Log;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-28T12:37:11")
@StaticMetamodel(Concept.class)
public class Concept_ { 

    public static volatile SingularAttribute<Concept, Integer> id;
    public static volatile SingularAttribute<Concept, Document> document;
    public static volatile SingularAttribute<Concept, Definition> definition;
    public static volatile SingularAttribute<Concept, String> name;
    public static volatile SingularAttribute<Concept, Boolean> active;
    public static volatile CollectionAttribute<Concept, Log> logCollection;

}