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
@Table(name = "conceptclassdef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conceptclassdef.findAll", query = "SELECT c FROM Conceptclassdef c"),
    @NamedQuery(name = "Conceptclassdef.findById", query = "SELECT c FROM Conceptclassdef c WHERE c.id = :id")})
public class Conceptclassdef implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptClassDefid")
    private Collection<Conceptclass> conceptclassCollection;

    public Conceptclassdef() {
    }

    public Conceptclassdef(Integer id) {
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
    public Collection<Conceptclass> getConceptclassCollection() {
        return conceptclassCollection;
    }

    public void setConceptclassCollection(Collection<Conceptclass> conceptclassCollection) {
        this.conceptclassCollection = conceptclassCollection;
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
        if (!(object instanceof Conceptclassdef)) {
            return false;
        }
        Conceptclassdef other = (Conceptclassdef) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Conceptclassdef[ id=" + id + " ]";
    }
    
}
