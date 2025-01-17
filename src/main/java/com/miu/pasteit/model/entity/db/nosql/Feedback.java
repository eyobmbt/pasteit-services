package com.miu.pasteit.model.entity.db.nosql;

import com.miu.pasteit.model.entity.common.NoSqlEntityCommon;
import com.miu.pasteit.model.entity.db.sql.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author Abdi Wako Jilo
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Document("FEEDBACK")
@EqualsAndHashCode(callSuper = true)
public class Feedback extends NoSqlEntityCommon {

    @Id
    private String id;
    private String comment;
    private LocalDateTime dateTime;
    private String pasteId;
    private Long userId;
    private String userName;

    public Feedback(String comment, LocalDateTime dateTime, User user, Paste paste) {
        this.comment = comment;
        this.dateTime = dateTime;
        this.pasteId = paste.getId();
        this.userId = user.getId();
        this.userName = getUserName();
    }
}
