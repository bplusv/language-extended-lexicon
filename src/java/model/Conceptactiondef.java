/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lu
 */
@Entity
@Table(name = "conceptactiondef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conceptactiondef.findAll", query = "SELECT c FROM Conceptactiondef c"),
    @NamedQuery(name = "Conceptactiondef.findById", query = "SELECT c FROM Conceptactiondef c WHERE c.id = :id")})
public class Conceptactiondef implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptActionDefid")
    private Collection<Conceptlog> conceptlogCollection;

    public Conceptactiondef() {
    }

    public Conceptactiondef(Integer id) {
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

    @XmlTransient
    public Collection<Conceptlog> getConceptlogCollection() {
        return conceptlogCollection;
    }

    public void setConceptlogCollection(Collection<Conceptlog> conceptlogCollection) {
        this.conceptlogCollection = conceptlogCollection;
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
        if (!(object instanceof Conceptactiondef)) {
            return false;
        }
        Conceptactiondef other = (Conceptactiondef) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Conceptactiondef[ id=" + id + " ]";
    }
    
}
