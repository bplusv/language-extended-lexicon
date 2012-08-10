package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Definition;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-08-09T19:40:14")
@StaticMetamodel(Classification.class)
public class Classification_ { 

    public static volatile SingularAttribute<Classification, Integer> id;
    public static volatile SingularAttribute<Classification, String> name;
    public static volatile CollectionAttribute<Classification, Definition> definitionCollection;

}