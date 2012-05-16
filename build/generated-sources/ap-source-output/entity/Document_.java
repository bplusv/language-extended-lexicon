package entity;

import entity.Concept;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-15T19:34:13")
@StaticMetamodel(Document.class)
public class Document_ { 

    public static volatile SingularAttribute<Document, Integer> id;
    public static volatile CollectionAttribute<Document, Concept> conceptCollection;
    public static volatile SingularAttribute<Document, String> name;

}