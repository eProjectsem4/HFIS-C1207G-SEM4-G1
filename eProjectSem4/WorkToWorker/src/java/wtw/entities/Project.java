/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Khanh
 */
@Entity
@Table(name = "Project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :id"),
    @NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name"),
    @NamedQuery(name = "Project.findByCategory", query = "SELECT p FROM Project p WHERE p.category = :category"),
    @NamedQuery(name = "Project.findByNameSkills", query = "SELECT p FROM Project p WHERE p.nameSkills = :nameSkills"),
    @NamedQuery(name = "Project.findByDescriptions", query = "SELECT p FROM Project p WHERE p.descriptions = :descriptions"),
    @NamedQuery(name = "Project.findByAttFile", query = "SELECT p FROM Project p WHERE p.attFile = :attFile"),
    @NamedQuery(name = "Project.findByPrice", query = "SELECT p FROM Project p WHERE p.price = :price"),
    @NamedQuery(name = "Project.findByStartDate", query = "SELECT p FROM Project p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Project.findByEndDate", query = "SELECT p FROM Project p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Project.findByStatus", query = "SELECT p FROM Project p WHERE p.status = :status")})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "category")
    private String category;
    @Size(max = 100)
    @Column(name = "nameSkills")
    private String nameSkills;
    @Size(max = 500)
    @Column(name = "descriptions")
    private String descriptions;
    @Size(max = 500)
    @Column(name = "attFile")
    private String attFile;
    @Column(name = "price")
    private Integer price;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Size(max = 10)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "idWorker", referencedColumnName = "id")
    @ManyToOne
    private Account idWorker;
    @JoinColumn(name = "idCustomer", referencedColumnName = "id")
    @ManyToOne
    private Account idCustomer;
    @OneToMany(mappedBy = "idProject")
    private Collection<OrderProject> orderProjectCollection;

    public Project() {
    }

    public Project(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNameSkills() {
        return nameSkills;
    }

    public void setNameSkills(String nameSkills) {
        this.nameSkills = nameSkills;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getAttFile() {
        return attFile;
    }

    public void setAttFile(String attFile) {
        this.attFile = attFile;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(Account idWorker) {
        this.idWorker = idWorker;
    }

    public Account getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Account idCustomer) {
        this.idCustomer = idCustomer;
    }

    @XmlTransient
    public Collection<OrderProject> getOrderProjectCollection() {
        return orderProjectCollection;
    }

    public void setOrderProjectCollection(Collection<OrderProject> orderProjectCollection) {
        this.orderProjectCollection = orderProjectCollection;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wtw.entities.Project[ id=" + id + " ]";
    }
    
}
