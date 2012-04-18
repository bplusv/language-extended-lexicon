/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lu
 */
@Entity
@Table(name = "userlog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userlog.findAll", query = "SELECT u FROM Userlog u"),
    @NamedQuery(name = "Userlog.findById", query = "SELECT u FROM Userlog u WHERE u.id = :id"),
    @NamedQuery(name = "Userlog.findByDate", query = "SELECT u FROM Userlog u WHERE u.date = :date")})
public class Userlog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "User_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userid;
    @JoinColumn(name = "UserActionDef_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Useractiondef userActionDefid;

    public Userlog() {
    }

    public Userlog(Integer id) {
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

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public Useractiondef getUserActionDefid() {
        return userActionDefid;
    }

    public void setUserActionDefid(Useractiondef userActionDefid) {
        this.userActionDefid = userActionDefid;
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
        if (!(object instanceof Userlog)) {
            return false;
        }
        Userlog other = (Userlog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Userlog[ id=" + id + " ]";
    }
    
}
