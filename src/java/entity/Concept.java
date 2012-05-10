/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lu
 */
@Entity
@Table(name = "concept")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concept.findAll", query = "SELECT c FROM Concept c"),
    @NamedQuery(name = "Concept.findById", query = "SELECT c FROM Concept c WHERE c.id = :id"),
    @NamedQuery(name = "Concept.findByActive", query = "SELECT c FROM Concept c WHERE c.active = :active")})
public class Concept implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "concept_details_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConceptDetails conceptDetailsId;
    @JoinColumn(name = "document_def_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DocumentDef documentDefId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptId")
    private Collection<ConceptLog> conceptLogCollection;

    public Concept() {
    }

    public Concept(Integer id) {
        this.id = id;
    }

    public Concept(Integer id, boolean active, String name) {
        this.id = id;
        this.active = active;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConceptDetails getConceptDetailsId() {
        return conceptDetailsId;
    }

    public void setConceptDetailsId(ConceptDetails conceptDetailsId) {
        this.conceptDetailsId = conceptDetailsId;
    }

    public DocumentDef getDocumentDefId() {
        return documentDefId;
    }

    public void setDocumentDefId(DocumentDef documentDefId) {
        this.documentDefId = documentDefId;
    }

    @XmlTransient
    public Collection<ConceptLog> getConceptLogCollection() {
        return conceptLogCollection;
    }

    public void setConceptLogCollection(Collection<ConceptLog> conceptLogCollection) {
        this.conceptLogCollection = conceptLogCollection;
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
        if (!(object instanceof Concept)) {
            return false;
        }
        Concept other = (Concept) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Concept[ id=" + id + " ]";
    }
    
}
