package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Category;
import model.Classification;
import model.Symbol;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-08-09T19:40:14")
@StaticMetamodel(Definition.class)
public class Definition_ { 

    public static volatile SingularAttribute<Definition, Integer> id;
    public static volatile SingularAttribute<Definition, Category> category;
    public static volatile SingularAttribute<Definition, String> notion;
    public static volatile SingularAttribute<Definition, Classification> classification;
    public static volatile CollectionAttribute<Definition, Symbol> symbolCollection;
    public static volatile SingularAttribute<Definition, String> actualIntention;
    public static volatile SingularAttribute<Definition, String> futureIntention;
    public static volatile SingularAttribute<Definition, String> comments;

}