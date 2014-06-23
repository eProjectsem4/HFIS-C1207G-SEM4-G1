package wtw.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wtw.entities.Account;
import wtw.entities.Project;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-23T16:35:16")
@StaticMetamodel(OrderProject.class)
public class OrderProject_ { 

    public static volatile SingularAttribute<OrderProject, Integer> id;
    public static volatile SingularAttribute<OrderProject, Account> idAccount;
    public static volatile SingularAttribute<OrderProject, Project> idProject;

}