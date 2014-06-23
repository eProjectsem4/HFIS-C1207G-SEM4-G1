package wtw.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wtw.entities.Comment;
import wtw.entities.FeedCustomer;
import wtw.entities.FeedWorker;
import wtw.entities.OrderProject;
import wtw.entities.Project;
import wtw.entities.RateCustomer;
import wtw.entities.RateWorker;
import wtw.entities.Topic;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-23T16:35:16")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile CollectionAttribute<Account, RateWorker> rateWorkerCollection;
    public static volatile CollectionAttribute<Account, FeedWorker> feedWorkerCollection1;
    public static volatile SingularAttribute<Account, String> phone;
    public static volatile CollectionAttribute<Account, FeedCustomer> feedCustomerCollection1;
    public static volatile CollectionAttribute<Account, Comment> commentCollection;
    public static volatile CollectionAttribute<Account, FeedCustomer> feedCustomerCollection;
    public static volatile CollectionAttribute<Account, RateWorker> rateWorkerCollection1;
    public static volatile CollectionAttribute<Account, Project> projectCollection;
    public static volatile CollectionAttribute<Account, FeedWorker> feedWorkerCollection;
    public static volatile SingularAttribute<Account, String> pass;
    public static volatile SingularAttribute<Account, String> addresss;
    public static volatile SingularAttribute<Account, String> country;
    public static volatile SingularAttribute<Account, Integer> id;
    public static volatile CollectionAttribute<Account, OrderProject> orderProjectCollection;
    public static volatile SingularAttribute<Account, String> imgProfile;
    public static volatile SingularAttribute<Account, String> email;
    public static volatile CollectionAttribute<Account, Project> projectCollection1;
    public static volatile CollectionAttribute<Account, RateCustomer> rateCustomerCollection1;
    public static volatile SingularAttribute<Account, String> company;
    public static volatile SingularAttribute<Account, String> role;
    public static volatile CollectionAttribute<Account, RateCustomer> rateCustomerCollection;
    public static volatile CollectionAttribute<Account, Topic> topicCollection;
    public static volatile SingularAttribute<Account, String> fullname;

}