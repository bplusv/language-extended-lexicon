package entity;

import entity.Concept;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-10T19:39:17")
@StaticMetamodel(DocumentDef.class)
public class DocumentDef_ { 

    public static volatile SingularAttribute<DocumentDef, Integer> id;
    public static volatile CollectionAttribute<DocumentDef, Concept> conceptCollection;
    public static volatile SingularAttribute<DocumentDef, String> name;

}