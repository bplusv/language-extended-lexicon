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
@Table(name = "concept_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptCategory.findAll", query = "SELECT c FROM ConceptCategory c"),
    @NamedQuery(name = "ConceptCategory.findById", query = "SELECT c FROM ConceptCategory c WHERE c.id = :id"),
    @NamedQuery(name = "ConceptCategory.findByConceptId", query = "SELECT c FROM ConceptCategory c WHERE c.conceptId = :conceptId")})
public class ConceptCategory implements Serializable {
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
    @JoinColumn(name = "concept_category_def_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConceptCategoryDef conceptCategoryDefId;

    public ConceptCategory() {
    }

    public ConceptCategory(Integer id) {
        this.id = id;
    }

    public ConceptCategory(Integer id, int conceptId) {
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

    public ConceptCategoryDef getConceptCategoryDefId() {
        return conceptCategoryDefId;
    }

    public void setConceptCategoryDefId(ConceptCategoryDef conceptCategoryDefId) {
        this.conceptCategoryDefId = conceptCategoryDefId;
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
        if (!(object instanceof ConceptCategory)) {
            return false;
        }
        ConceptCategory other = (ConceptCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ConceptCategory[ id=" + id + " ]";
    }
    
}
