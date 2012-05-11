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
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Salazar <bp.lusv@gmail.com>
 */
@Entity
@Table(name = "concept_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptLog.findAll", query = "SELECT c FROM ConceptLog c"),
    @NamedQuery(name = "ConceptLog.findById", query = "SELECT c FROM ConceptLog c WHERE c.id = :id"),
    @NamedQuery(name = "ConceptLog.findByDate", query = "SELECT c FROM ConceptLog c WHERE c.date = :date"),
    @NamedQuery(name = "ConceptLog.findByUserId", query = "SELECT c FROM ConceptLog c WHERE c.userId = :userId")})
public class ConceptLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @JoinColumn(name = "concept_action_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConceptAction conceptActionId;
    @JoinColumn(name = "concept_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Concept conceptId;

    public ConceptLog() {
    }

    public ConceptLog(Integer id) {
        this.id = id;
    }

    public ConceptLog(Integer id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ConceptAction getConceptActionId() {
        return conceptActionId;
    }

    public void setConceptActionId(ConceptAction conceptActionId) {
        this.conceptActionId = conceptActionId;
    }

    public Concept getConceptId() {
        return conceptId;
    }

    public void setConceptId(Concept conceptId) {
        this.conceptId = conceptId;
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
        if (!(object instanceof ConceptLog)) {
            return false;
        }
        ConceptLog other = (ConceptLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ConceptLog[ id=" + id + " ]";
    }

}