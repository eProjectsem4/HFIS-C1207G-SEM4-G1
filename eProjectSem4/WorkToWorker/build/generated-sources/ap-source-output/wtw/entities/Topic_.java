package wtw.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wtw.entities.Account;
import wtw.entities.Comment;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-23T16:35:16")
@StaticMetamodel(Topic.class)
public class Topic_ { 

    public static volatile SingularAttribute<Topic, Integer> id;
    public static volatile SingularAttribute<Topic, String> content;
    public static volatile SingularAttribute<Topic, String> title;
    public static volatile CollectionAttribute<Topic, Comment> commentCollection;
    public static volatile SingularAttribute<Topic, Date> postDate;
    public static volatile SingularAttribute<Topic, Account> idAccount;

}