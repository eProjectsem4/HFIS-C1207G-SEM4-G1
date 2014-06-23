/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Khanh
 */
@Entity
@Table(name = "Account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
    @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
    @NamedQuery(name = "Account.findByPass", query = "SELECT a FROM Account a WHERE a.pass = :pass"),
    @NamedQuery(name = "Account.findByFullname", query = "SELECT a FROM Account a WHERE a.fullname = :fullname"),
    @NamedQuery(name = "Account.findByPhone", query = "SELECT a FROM Account a WHERE a.phone = :phone"),
    @NamedQuery(name = "Account.findByCompany", query = "SELECT a FROM Account a WHERE a.company = :company"),
    @NamedQuery(name = "Account.findByRole", query = "SELECT a FROM Account a WHERE a.role = :role"),
    @NamedQuery(name = "Account.findByAddresss", query = "SELECT a FROM Account a WHERE a.addresss = :addresss"),
    @NamedQuery(name = "Account.findByCountry", query = "SELECT a FROM Account a WHERE a.country = :country"),
    @NamedQuery(name = "Account.findByImgProfile", query = "SELECT a FROM Account a WHERE a.imgProfile = :imgProfile")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Size(max = 50)
    @Column(name = "pass")
    private String pass;
    @Size(max = 50)
    @Column(name = "fullname")
    private String fullname;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    @Size(max = 20)
    @Column(name = "company")
    private String company;
    @Size(max = 15)
    @Column(name = "role")
    private String role;
    @Size(max = 50)
    @Column(name = "addresss")
    private String addresss;
    @Size(max = 20)
    @Column(name = "country")
    private String country;
    @Size(max = 500)
    @Column(name = "imgProfile")
    private String imgProfile;
    @OneToMany(mappedBy = "idWorker")
    private Collection<FeedCustomer> feedCustomerCollection;
    @OneToMany(mappedBy = "idCustomer")
    private Collection<FeedCustomer> feedCustomerCollection1;
    @OneToMany(mappedBy = "idAccount")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "idAccount")
    private Collection<Topic> topicCollection;
    @OneToMany(mappedBy = "idWorker")
    private Collection<Project> projectCollection;
    @OneToMany(mappedBy = "idCustomer")
    private Collection<Project> projectCollection1;
    @OneToMany(mappedBy = "idWorker")
    private Collection<RateCustomer> rateCustomerCollection;
    @OneToMany(mappedBy = "idCustomer")
    private Collection<RateCustomer> rateCustomerCollection1;
    @OneToMany(mappedBy = "idWorker")
    private Collection<FeedWorker> feedWorkerCollection;
    @OneToMany(mappedBy = "idCustomer")
    private Collection<FeedWorker> feedWorkerCollection1;
    @OneToMany(mappedBy = "idAccount")
    private Collection<OrderProject> orderProjectCollection;
    @OneToMany(mappedBy = "idWorker")
    private Collection<RateWorker> rateWorkerCollection;
    @OneToMany(mappedBy = "idCustomer")
    private Collection<RateWorker> rateWorkerCollection1;

    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(String imgProfile) {
        this.imgProfile = imgProfile;
    }

    @XmlTransient
    public Collection<FeedCustomer> getFeedCustomerCollection() {
        return feedCustomerCollection;
    }

    public void setFeedCustomerCollection(Collection<FeedCustomer> feedCustomerCollection) {
        this.feedCustomerCollection = feedCustomerCollection;
    }

    @XmlTransient
    public Collection<FeedCustomer> getFeedCustomerCollection1() {
        return feedCustomerCollection1;
    }

    public void setFeedCustomerCollection1(Collection<FeedCustomer> feedCustomerCollection1) {
        this.feedCustomerCollection1 = feedCustomerCollection1;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Topic> getTopicCollection() {
        return topicCollection;
    }

    public void setTopicCollection(Collection<Topic> topicCollection) {
        this.topicCollection = topicCollection;
    }

    @XmlTransient
    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    @XmlTransient
    public Collection<Project> getProjectCollection1() {
        return projectCollection1;
    }

    public void setProjectCollection1(Collection<Project> projectCollection1) {
        this.projectCollection1 = projectCollection1;
    }

    @XmlTransient
    public Collection<RateCustomer> getRateCustomerCollection() {
        return rateCustomerCollection;
    }

    public void setRateCustomerCollection(Collection<RateCustomer> rateCustomerCollection) {
        this.rateCustomerCollection = rateCustomerCollection;
    }

    @XmlTransient
    public Collection<RateCustomer> getRateCustomerCollection1() {
        return rateCustomerCollection1;
    }

    public void setRateCustomerCollection1(Collection<RateCustomer> rateCustomerCollection1) {
        this.rateCustomerCollection1 = rateCustomerCollection1;
    }

    @XmlTransient
    public Collection<FeedWorker> getFeedWorkerCollection() {
        return feedWorkerCollection;
    }

    public void setFeedWorkerCollection(Collection<FeedWorker> feedWorkerCollection) {
        this.feedWorkerCollection = feedWorkerCollection;
    }

    @XmlTransient
    public Collection<FeedWorker> getFeedWorkerCollection1() {
        return feedWorkerCollection1;
    }

    public void setFeedWorkerCollection1(Collection<FeedWorker> feedWorkerCollection1) {
        this.feedWorkerCollection1 = feedWorkerCollection1;
    }

    @XmlTransient
    public Collection<OrderProject> getOrderProjectCollection() {
        return orderProjectCollection;
    }

    public void setOrderProjectCollection(Collection<OrderProject> orderProjectCollection) {
        this.orderProjectCollection = orderProjectCollection;
    }

    @XmlTransient
    public Collection<RateWorker> getRateWorkerCollection() {
        return rateWorkerCollection;
    }

    public void setRateWorkerCollection(Collection<RateWorker> rateWorkerCollection) {
        this.rateWorkerCollection = rateWorkerCollection;
    }

    @XmlTransient
    public Collection<RateWorker> getRateWorkerCollection1() {
        return rateWorkerCollection1;
    }

    public void setRateWorkerCollection1(Collection<RateWorker> rateWorkerCollection1) {
        this.rateWorkerCollection1 = rateWorkerCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wtw.entities.Account[ id=" + id + " ]";
    }
    
}
