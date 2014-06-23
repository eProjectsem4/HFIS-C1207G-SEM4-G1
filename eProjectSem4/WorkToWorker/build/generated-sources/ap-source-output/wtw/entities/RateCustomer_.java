package wtw.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wtw.entities.Account;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-23T16:35:16")
@StaticMetamodel(RateCustomer.class)
public class RateCustomer_ { 

    public static volatile SingularAttribute<RateCustomer, Integer> id;
    public static volatile SingularAttribute<RateCustomer, Account> idCustomer;
    public static volatile SingularAttribute<RateCustomer, Account> idWorker;
    public static volatile SingularAttribute<RateCustomer, Integer> star;

}