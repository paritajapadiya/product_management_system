package com.productmangement.api.model;

import com.productmangement.api.enums.Role;
import com.productmangement.api.util.Constant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = Constant.COLLECTION_USERS)
@Getter
@Setter
public class EntityUser extends DateAudit {
    @Id
    private String id;

    private String username;

    private String password;

    private Set<Role> roles;

    public EntityUser(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
