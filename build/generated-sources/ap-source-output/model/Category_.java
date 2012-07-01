package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Definition;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-30T12:22:46")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SingularAttribute<Category, Integer> id;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile CollectionAttribute<Category, Definition> definitionCollection;

}