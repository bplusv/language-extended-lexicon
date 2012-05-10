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
@Table(name = "user_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserLog.findAll", query = "SELECT u FROM UserLog u"),
    @NamedQuery(name = "UserLog.findById", query = "SELECT u FROM UserLog u WHERE u.id = :id"),
    @NamedQuery(name = "UserLog.findByDate", query = "SELECT u FROM UserLog u WHERE u.date = :date")})
public class UserLog implements Serializable {
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "user_action_def_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserActionDef userActionDefId;

    public UserLog() {
    }

    public UserLog(Integer id) {
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public UserActionDef getUserActionDefId() {
        return userActionDefId;
    }

    public void setUserActionDefId(UserActionDef userActionDefId) {
        this.userActionDefId = userActionDefId;
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
        if (!(object instanceof UserLog)) {
            return false;
        }
        UserLog other = (UserLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserLog[ id=" + id + " ]";
    }
    
}
