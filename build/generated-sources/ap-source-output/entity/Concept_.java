package entity;

import entity.ConceptDetails;
import entity.ConceptLog;
import entity.DocumentDef;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-10T19:39:17")
@StaticMetamodel(Concept.class)
public class Concept_ { 

    public static volatile SingularAttribute<Concept, Integer> id;
    public static volatile SingularAttribute<Concept, String> name;
    public static volatile SingularAttribute<Concept, Boolean> active;
    public static volatile SingularAttribute<Concept, DocumentDef> documentDefId;
    public static volatile SingularAttribute<Concept, ConceptDetails> conceptDetailsId;
    public static volatile CollectionAttribute<Concept, ConceptLog> conceptLogCollection;

}