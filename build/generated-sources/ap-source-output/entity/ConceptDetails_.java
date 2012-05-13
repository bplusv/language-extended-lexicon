package entity;

import entity.Concept;
import entity.ConceptCategory;
import entity.ConceptClassification;
import entity.Document;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-12T21:03:23")
@StaticMetamodel(ConceptDetails.class)
public class ConceptDetails_ { 

    public static volatile SingularAttribute<ConceptDetails, Integer> id;
    public static volatile CollectionAttribute<ConceptDetails, Concept> conceptCollection;
    public static volatile SingularAttribute<ConceptDetails, String> notion;
    public static volatile SingularAttribute<ConceptDetails, ConceptCategory> conceptCategoryId;
    public static volatile SingularAttribute<ConceptDetails, String> actualIntention;
    public static volatile SingularAttribute<ConceptDetails, String> futureIntention;
    public static volatile SingularAttribute<ConceptDetails, String> comments;
    public static volatile SingularAttribute<ConceptDetails, Document> documentId;
    public static volatile SingularAttribute<ConceptDetails, ConceptClassification> conceptClassificationId;

}