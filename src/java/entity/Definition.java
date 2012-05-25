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
@Table(name = "definition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Definition.findAll", query = "SELECT d FROM Definition d"),
    @NamedQuery(name = "Definition.findById", query = "SELECT d FROM Definition d WHERE d.id = :id")})
public class Definition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "comments")
    private String comments;
    @Lob
    @Size(max = 65535)
    @Column(name = "notion")
    private String notion;
    @Lob
    @Size(max = 65535)
    @Column(name = "actual_intention")
    private String actualIntention;
    @Lob
    @Size(max = 65535)
    @Column(name = "future_intention")
    private String futureIntention;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "definition")
    private Collection<Concept> conceptCollection;
    @JoinColumn(name = "classification", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classification classification;
    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category category;

    public Definition() {
    }

    public Definition(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNotion() {
        return notion;
    }

    public void setNotion(String notion) {
        this.notion = notion;
    }

    public String getActualIntention() {
        return actualIntention;
    }

    public void setActualIntention(String actualIntention) {
        this.actualIntention = actualIntention;
    }

    public String getFutureIntention() {
        return futureIntention;
    }

    public void setFutureIntention(String futureIntention) {
        this.futureIntention = futureIntention;
    }

    @XmlTransient
    public Collection<Concept> getConceptCollection() {
        return conceptCollection;
    }

    public void setConceptCollection(Collection<Concept> conceptCollection) {
        this.conceptCollection = conceptCollection;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        if (!(object instanceof Definition)) {
            return false;
        }
        Definition other = (Definition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Definition[ id=" + id + " ]";
    }

}