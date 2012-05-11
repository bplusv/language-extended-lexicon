package entity;

import entity.ConceptCategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-10T19:39:17")
@StaticMetamodel(ConceptCategoryDef.class)
public class ConceptCategoryDef_ { 

    public static volatile SingularAttribute<ConceptCategoryDef, Integer> id;
    public static volatile CollectionAttribute<ConceptCategoryDef, ConceptCategory> conceptCategoryCollection;
    public static volatile SingularAttribute<ConceptCategoryDef, String> name;

}