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
@Table(name = "conceptcategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conceptcategory.findAll", query = "SELECT c FROM Conceptcategory c"),
    @NamedQuery(name = "Conceptcategory.findById", query = "SELECT c FROM Conceptcategory c WHERE c.id = :id"),
    @NamedQuery(name = "Conceptcategory.findByConceptId", query = "SELECT c FROM Conceptcategory c WHERE c.conceptId = :conceptId")})
public class Conceptcategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "conceptId")
    private Integer conceptId;
    @JoinColumn(name = "ConceptCategoryDef_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conceptcategorydef conceptCategoryDefid;

    public Conceptcategory() {
    }

    public Conceptcategory(Integer id) {
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

    public Conceptcategorydef getConceptCategoryDefid() {
        return conceptCategoryDefid;
    }

    public void setConceptCategoryDefid(Conceptcategorydef conceptCategoryDefid) {
        this.conceptCategoryDefid = conceptCategoryDefid;
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
        if (!(object instanceof Conceptcategory)) {
            return false;
        }
        Conceptcategory other = (Conceptcategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Conceptcategory[ id=" + id + " ]";
    }
    
}
