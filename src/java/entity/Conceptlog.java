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
@Table(name = "conceptlog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conceptlog.findAll", query = "SELECT c FROM Conceptlog c"),
    @NamedQuery(name = "Conceptlog.findById", query = "SELECT c FROM Conceptlog c WHERE c.id = :id"),
    @NamedQuery(name = "Conceptlog.findByDate", query = "SELECT c FROM Conceptlog c WHERE c.date = :date"),
    @NamedQuery(name = "Conceptlog.findByUserId", query = "SELECT c FROM Conceptlog c WHERE c.userId = :userId")})
public class Conceptlog implements Serializable {
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
    @Column(name = "userId")
    private Integer userId;
    @JoinColumn(name = "ConceptActionDef_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conceptactiondef conceptActionDefid;
    @JoinColumn(name = "Concept_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Concept conceptid;

    public Conceptlog() {
    }

    public Conceptlog(Integer id) {
        this.id = id;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Conceptactiondef getConceptActionDefid() {
        return conceptActionDefid;
    }

    public void setConceptActionDefid(Conceptactiondef conceptActionDefid) {
        this.conceptActionDefid = conceptActionDefid;
    }

    public Concept getConceptid() {
        return conceptid;
    }

    public void setConceptid(Concept conceptid) {
        this.conceptid = conceptid;
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
        if (!(object instanceof Conceptlog)) {
            return false;
        }
        Conceptlog other = (Conceptlog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Conceptlog[ id=" + id + " ]";
    }
    
}
