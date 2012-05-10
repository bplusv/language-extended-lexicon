/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lu
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
    @JoinColumn(name = "concept_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Concept conceptId;
    @JoinColumn(name = "concept_action_def_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ConceptActionDef conceptActionDefId;

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

    public Concept getConceptId() {
        return conceptId;
    }

    public void setConceptId(Concept conceptId) {
        this.conceptId = conceptId;
    }

    public ConceptActionDef getConceptActionDefId() {
        return conceptActionDefId;
    }

    public void setConceptActionDefId(ConceptActionDef conceptActionDefId) {
        this.conceptActionDefId = conceptActionDefId;
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
