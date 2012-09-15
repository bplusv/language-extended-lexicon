package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Definition;
import model.User;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-14T12:18:25")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Integer> id;
    public static volatile SingularAttribute<Comment, String> content;
    public static volatile CollectionAttribute<Comment, Definition> definitionCollection;
    public static volatile SingularAttribute<Comment, Date> date;
    public static volatile SingularAttribute<Comment, User> user;

}