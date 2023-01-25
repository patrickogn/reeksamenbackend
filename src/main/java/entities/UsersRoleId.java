package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsersRoleId implements Serializable {
    private static final long serialVersionUID = -4982738472828420265L;
    @Size(max = 20)
    @NotNull
    @Column(name = "roleList_role_name", nullable = false, length = 20)
    private String rolelistRoleName;

    @Size(max = 25)
    @NotNull
    @Column(name = "userList_user_name", nullable = false, length = 25)
    private String userlistUserName;

    public String getRolelistRoleName() {
        return rolelistRoleName;
    }

    public void setRolelistRoleName(String rolelistRoleName) {
        this.rolelistRoleName = rolelistRoleName;
    }

    public String getUserlistUserName() {
        return userlistUserName;
    }

    public void setUserlistUserName(String userlistUserName) {
        this.userlistUserName = userlistUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersRoleId entity = (UsersRoleId) o;
        return Objects.equals(this.userlistUserName, entity.userlistUserName) &&
                Objects.equals(this.rolelistRoleName, entity.rolelistRoleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userlistUserName, rolelistRoleName);
    }

}