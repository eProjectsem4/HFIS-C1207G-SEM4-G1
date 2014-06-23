package wtw.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wtw.entities.Account;
import wtw.entities.OrderProject;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-23T16:35:16")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, Integer> id;
    public static volatile CollectionAttribute<Project, OrderProject> orderProjectCollection;
    public static volatile SingularAttribute<Project, Date> startDate;
    public static volatile SingularAttribute<Project, String> category;
    public static volatile SingularAttribute<Project, Integer> price;
    public static volatile SingularAttribute<Project, Account> idCustomer;
    public static volatile SingularAttribute<Project, Account> idWorker;
    public static volatile SingularAttribute<Project, String> status;
    public static volatile SingularAttribute<Project, String> name;
    public static volatile SingularAttribute<Project, String> attFile;
    public static volatile SingularAttribute<Project, String> nameSkills;
    public static volatile SingularAttribute<Project, Date> endDate;
    public static volatile SingularAttribute<Project, String> descriptions;

}