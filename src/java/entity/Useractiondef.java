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
@Table(name = "user_action_def")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserActionDef.findAll", query = "SELECT u FROM UserActionDef u"),
    @NamedQuery(name = "UserActionDef.findById", query = "SELECT u FROM UserActionDef u WHERE u.id = :id"),
    @NamedQuery(name = "UserActionDef.findByName", query = "SELECT u FROM UserActionDef u WHERE u.name = :name")})
public class UserActionDef implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userActionDefId")
    private Collection<UserLog> userLogCollection;

    public UserActionDef() {
    }

    public UserActionDef(Integer id) {
        this.id = id;
    }

    public UserActionDef(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<UserLog> getUserLogCollection() {
        return userLogCollection;
    }

    public void setUserLogCollection(Collection<UserLog> userLogCollection) {
        this.userLogCollection = userLogCollection;
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
        if (!(object instanceof UserActionDef)) {
            return false;
        }
        UserActionDef other = (UserActionDef) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserActionDef[ id=" + id + " ]";
    }
    
}
