package entity;

import entity.ConceptLog;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-12T21:03:23")
@StaticMetamodel(ConceptEvent.class)
public class ConceptEvent_ { 

    public static volatile SingularAttribute<ConceptEvent, Integer> id;
    public static volatile SingularAttribute<ConceptEvent, String> name;
    public static volatile CollectionAttribute<ConceptEvent, ConceptLog> conceptLogCollection;

}