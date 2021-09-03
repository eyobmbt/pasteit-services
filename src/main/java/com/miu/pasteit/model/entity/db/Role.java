package com.miu.pasteit.model.entity.db;

import com.miu.pasteit.model.entity.common.EntityCommon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.miu.pasteit.utils.ValidationConstants.ALPHANUMERIC_UNDERSCORE_DOT;
import static com.miu.pasteit.utils.ValidationConstants.ROLE_MAX_SIZE;

/**
 * @author Rimon Mostafiz
 */
@Data
@NoArgsConstructor
@Document("ROLE")
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = true)
public class Role extends EntityCommon {
    @Id
    private String id;

    @NotBlank(message = "{error.role.name.blank}")
    @Size(max = ROLE_MAX_SIZE, message = "{error.role.name.max.size}")
    @Pattern(regexp = ALPHANUMERIC_UNDERSCORE_DOT, message = "{error.role.name.invalid}")
    private String name;
}
