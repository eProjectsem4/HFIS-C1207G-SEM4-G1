/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wtw.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Khanh
 */
@Entity
@Table(name = "RateCustomer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RateCustomer.findAll", query = "SELECT r FROM RateCustomer r"),
    @NamedQuery(name = "RateCustomer.findById", query = "SELECT r FROM RateCustomer r WHERE r.id = :id"),
    @NamedQuery(name = "RateCustomer.findByStar", query = "SELECT r FROM RateCustomer r WHERE r.star = :star")})
public class RateCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "star")
    private Integer star;
    @JoinColumn(name = "idWorker", referencedColumnName = "id")
    @ManyToOne
    private Account idWorker;
    @JoinColumn(name = "idCustomer", referencedColumnName = "id")
    @ManyToOne
    private Account idCustomer;

    public RateCustomer() {
    }

    public RateCustomer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RateCustomer)) {
            return false;
        }
        RateCustomer other = (RateCustomer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wtw.entities.RateCustomer[ id=" + id + " ]";
    }
    
}
