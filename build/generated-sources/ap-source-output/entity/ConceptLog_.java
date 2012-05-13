package entity;

import entity.Concept;
import entity.ConceptEvent;
import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-12T21:03:23")
@StaticMetamodel(ConceptLog.class)
public class ConceptLog_ { 

    public static volatile SingularAttribute<ConceptLog, Integer> id;
    public static volatile SingularAttribute<ConceptLog, ConceptEvent> conceptEventId;
    public static volatile SingularAttribute<ConceptLog, User> userId;
    public static volatile SingularAttribute<ConceptLog, Concept> conceptId;
    public static volatile SingularAttribute<ConceptLog, Date> date;

}