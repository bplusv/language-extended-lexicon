/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lu
 */
@Entity
@Table(name = "concept_class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptClass.findAll", query = "SELECT c FROM ConceptClass c"),
    @NamedQuery(name = "ConceptClass.findById", query = "SELECT c FROM ConceptClass c WHERE c.id = :id"),
    @NamedQuery(name = "ConceptClass.findByConceptId", query = "SELECT c FROM ConceptClass c WHERE c.conceptId = :conceptId")})
public class ConceptClass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "concept_id")
    private int conceptId;
    @JoinColumn(name = "concept_class_def_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConceptClassDef conceptClassDefId;

    public ConceptClass() {
    }

    public ConceptClass(Integer id) {
        this.id = id;
    }

    public ConceptClass(Integer id, int conceptId) {
        this.id = id;
        this.conceptId = conceptId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getConceptId() {
        return conceptId;
    }

    public void setConceptId(int conceptId) {
        this.conceptId = conceptId;
    }

    public ConceptClassDef getConceptClassDefId() {
        return conceptClassDefId;
    }

    public void setConceptClassDefId(ConceptClassDef conceptClassDefId) {
        this.conceptClassDefId = conceptClassDefId;
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
        if (!(object instanceof ConceptClass)) {
            return false;
        }
        ConceptClass other = (ConceptClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ConceptClass[ id=" + id + " ]";
    }
    
}
