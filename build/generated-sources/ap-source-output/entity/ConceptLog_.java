package entity;

import entity.Concept;
import entity.ConceptActionDef;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-09T22:47:04")
@StaticMetamodel(ConceptLog.class)
public class ConceptLog_ { 

    public static volatile SingularAttribute<ConceptLog, Integer> id;
    public static volatile SingularAttribute<ConceptLog, Integer> userId;
    public static volatile SingularAttribute<ConceptLog, Concept> conceptId;
    public static volatile SingularAttribute<ConceptLog, Date> date;
    public static volatile SingularAttribute<ConceptLog, ConceptActionDef> conceptActionDefId;

}