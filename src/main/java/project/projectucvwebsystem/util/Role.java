package project.projectucvwebsystem.util;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum Role {
    ADMIN(
        Arrays.asList(
            Permission.REGISTER_PRODUCT,
            Permission.REGISTER_SALE,
            Permission.REGISTER_SUPPLIER,
            Permission.MODIFY_PRODUCT,
            Permission.MODIFY_SALE,
            Permission.MODIFY_SUPPLIER,
            Permission.DELETE_PRODUCT,
            Permission.DELETE_SUPPLIER
        )
    ),
    WAREHOUSE_MANAGER(
        Arrays.asList(
            Permission.REGISTER_PRODUCT,
            Permission.MODIFY_PRODUCT
        )
    ),
    VENDEDOR(
        Arrays.asList(
            Permission.REGISTER_SALE,
            Permission.MODIFY_SALE
        )
    );

    @Getter
    @Setter
    private List<Permission> permissions;
}
