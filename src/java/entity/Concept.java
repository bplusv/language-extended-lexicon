/*
 * The MIT License
 *
 * Copyright 2012 Luis Salazar <bp.lusv@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
 * @author Luis Salazar <bp.lusv@gmail.com>
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
    @Column(name = "active")
    private Boolean active;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "concept_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConceptCategory conceptCategoryId;
    @JoinColumn(name = "concept_class_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConceptClass conceptClassId;
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Document documentId;
    @JoinColumn(name = "concept_details_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConceptDetails conceptDetailsId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptId")
    private Collection<ConceptLog> conceptLogCollection;

    public Concept() {
    }

    public Concept(Integer id) {
        this.id = id;
    }

    public Concept(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConceptCategory getConceptCategoryId() {
        return conceptCategoryId;
    }

    public void setConceptCategoryId(ConceptCategory conceptCategoryId) {
        this.conceptCategoryId = conceptCategoryId;
    }

    public ConceptClass getConceptClassId() {
        return conceptClassId;
    }

    public void setConceptClassId(ConceptClass conceptClassId) {
        this.conceptClassId = conceptClassId;
    }

    public Document getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Document documentId) {
        this.documentId = documentId;
    }

    public ConceptDetails getConceptDetailsId() {
        return conceptDetailsId;
    }

    public void setConceptDetailsId(ConceptDetails conceptDetailsId) {
        this.conceptDetailsId = conceptDetailsId;
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