package entity;

import entity.ConceptDetails;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-12T21:03:23")
@StaticMetamodel(Document.class)
public class Document_ { 

    public static volatile SingularAttribute<Document, Integer> id;
    public static volatile SingularAttribute<Document, String> name;
    public static volatile CollectionAttribute<Document, ConceptDetails> conceptDetailsCollection;

}