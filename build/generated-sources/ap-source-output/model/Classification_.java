package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Definition;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-14T12:18:25")
@StaticMetamodel(Classification.class)
public class Classification_ { 

    public static volatile SingularAttribute<Classification, Integer> id;
    public static volatile SingularAttribute<Classification, String> name;
    public static volatile CollectionAttribute<Classification, Definition> definitionCollection;

}