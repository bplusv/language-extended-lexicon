package entity;

import entity.ConceptClass;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-10T19:39:17")
@StaticMetamodel(ConceptClassDef.class)
public class ConceptClassDef_ { 

    public static volatile SingularAttribute<ConceptClassDef, Integer> id;
    public static volatile CollectionAttribute<ConceptClassDef, ConceptClass> conceptClassCollection;
    public static volatile SingularAttribute<ConceptClassDef, String> name;

}