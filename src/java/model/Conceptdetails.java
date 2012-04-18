/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lu
 */
@Entity
@Table(name = "conceptdetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conceptdetails.findAll", query = "SELECT c FROM Conceptdetails c"),
    @NamedQuery(name = "Conceptdetails.findById", query = "SELECT c FROM Conceptdetails c WHERE c.id = :id")})
public class Conceptdetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "comments")
    private String comments;
    @Lob
    @Column(name = "notion")
    private String notion;
    @Lob
    @Column(name = "actualIntention")
    private String actualIntention;
    @Lob
    @Column(name = "futureIntention")
    private String futureIntention;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptDetailsid")
    private Collection<Concept> conceptCollection;

    public Conceptdetails() {
    }

    public Conceptdetails(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conceptdetails)) {
            return false;
        }
        Conceptdetails other = (Conceptdetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Conceptdetails[ id=" + id + " ]";
    }
    
}
