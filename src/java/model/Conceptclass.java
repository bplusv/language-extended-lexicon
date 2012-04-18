/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lu
 */
@Entity
@Table(name = "conceptclass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conceptclass.findAll", query = "SELECT c FROM Conceptclass c"),
    @NamedQuery(name = "Conceptclass.findById", query = "SELECT c FROM Conceptclass c WHERE c.id = :id"),
    @NamedQuery(name = "Conceptclass.findByConceptId", query = "SELECT c FROM Conceptclass c WHERE c.conceptId = :conceptId")})
public class Conceptclass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "conceptId")
    private Integer conceptId;
    @JoinColumn(name = "ConceptClassDef_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conceptclassdef conceptClassDefid;

    public Conceptclass() {
    }

    public Conceptclass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConceptId() {
        return conceptId;
    }

    public void setConceptId(Integer conceptId) {
        this.conceptId = conceptId;
    }

    public Conceptclassdef getConceptClassDefid() {
        return conceptClassDefid;
    }

    public void setConceptClassDefid(Conceptclassdef conceptClassDefid) {
        this.conceptClassDefid = conceptClassDefid;
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
        if (!(object instanceof Conceptclass)) {
            return false;
        }
        Conceptclass other = (Conceptclass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Conceptclass[ id=" + id + " ]";
    }
    
}
