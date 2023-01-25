package entities;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
public class UsersRole {
    @EmbeddedId
    private UsersRoleId id;

    @MapsId("rolelistRoleName")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roleList_role_name", nullable = false)
    private Role rolelistRoleName;

    @MapsId("userlistUserName")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userList_user_name", nullable = false)
    private User userlistUserName;

    public UsersRoleId getId() {
        return id;
    }

    public void setId(UsersRoleId id) {
        this.id = id;
    }

    public Role getRolelistRoleName() {
        return rolelistRoleName;
    }

    public void setRolelistRoleName(Role rolelistRoleName) {
        this.rolelistRoleName = rolelistRoleName;
    }

    public User getUserlistUserName() {
        return userlistUserName;
    }

    public void setUserlistUserName(User userlistUserName) {
        this.userlistUserName = userlistUserName;
    }

}