package entity;

import entity.Concept;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-09T22:47:04")
@StaticMetamodel(ConceptDetails.class)
public class ConceptDetails_ { 

    public static volatile SingularAttribute<ConceptDetails, Integer> id;
    public static volatile CollectionAttribute<ConceptDetails, Concept> conceptCollection;
    public static volatile SingularAttribute<ConceptDetails, String> notion;
    public static volatile SingularAttribute<ConceptDetails, String> actualIntention;
    public static volatile SingularAttribute<ConceptDetails, String> futureIntention;
    public static volatile SingularAttribute<ConceptDetails, String> comments;

}