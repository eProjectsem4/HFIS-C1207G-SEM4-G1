package wtw.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wtw.entities.Account;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-23T16:35:16")
@StaticMetamodel(FeedCustomer.class)
public class FeedCustomer_ { 

    public static volatile SingularAttribute<FeedCustomer, Integer> id;
    public static volatile SingularAttribute<FeedCustomer, String> content;
    public static volatile SingularAttribute<FeedCustomer, Account> idCustomer;
    public static volatile SingularAttribute<FeedCustomer, Account> idWorker;

}