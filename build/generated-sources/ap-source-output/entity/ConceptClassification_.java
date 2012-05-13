package entity;

import entity.ConceptDetails;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-12T21:03:23")
@StaticMetamodel(ConceptClassification.class)
public class ConceptClassification_ { 

    public static volatile SingularAttribute<ConceptClassification, Integer> id;
    public static volatile SingularAttribute<ConceptClassification, String> name;
    public static volatile CollectionAttribute<ConceptClassification, ConceptDetails> conceptDetailsCollection;

}