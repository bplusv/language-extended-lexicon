package entity;

import entity.ConceptLog;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-09T22:47:04")
@StaticMetamodel(ConceptActionDef.class)
public class ConceptActionDef_ { 

    public static volatile SingularAttribute<ConceptActionDef, Integer> id;
    public static volatile SingularAttribute<ConceptActionDef, String> name;
    public static volatile CollectionAttribute<ConceptActionDef, ConceptLog> conceptLogCollection;

}