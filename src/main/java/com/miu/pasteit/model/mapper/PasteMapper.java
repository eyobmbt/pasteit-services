package com.miu.pasteit.model.mapper;

import com.miu.pasteit.model.dto.PasteModel;
import com.miu.pasteit.model.entity.common.Language;
import com.miu.pasteit.model.entity.common.PasteStatus;
import com.miu.pasteit.model.entity.db.nosql.Feedback;
import com.miu.pasteit.model.entity.db.nosql.Paste;
import com.miu.pasteit.model.entity.db.sql.User;
import com.miu.pasteit.model.request.PasteCreateRequest;
import com.miu.pasteit.model.request.PasteUpdateRequest;
import com.miu.pasteit.utils.Utils;

import java.time.LocalDateTime;

/**
 * @author Rimon Mostafiz
 */
public class PasteMapper {

    public static PasteModel mapper(Paste entity) {
        PasteModel model = new PasteModel();
        model.setId(entity.getId());
        model.setContent(entity.getContent());
        model.setTitle(entity.getTitle());
        model.setUrl(entity.getUrl());
        model.setDescription(entity.getDescription());
        model.setStatus(entity.getStatus());
        model.setLanguage(entity.getLanguage());
        model.setFolder(entity.getFolder());
        model.setPasteUser(entity.getPasteUser());
        model.setPasteDateTime(entity.getPasteDateTime());
        return model;
    }

    public static Paste createRequestToEntity(PasteCreateRequest pasteCreateRequest, String createdBy, User user, String url) {
        Paste entity = new Paste();

        entity.setContent(pasteCreateRequest.getContent());
        entity.setContentHash(Utils.getContentHash(entity.getContent()));
        entity.setTitle(pasteCreateRequest.getTitle());
        entity.setUrl(url);
        entity.setDescription(pasteCreateRequest.getDescription());
        entity.setStatus(pasteCreateRequest.getStatus() == null
                ? PasteStatus.PUBLIC
                : PasteStatus.getStatus(pasteCreateRequest.getStatus()));
        entity.setLanguage(pasteCreateRequest.getLanguage() == null
                ? Language.NONE
                : Language.getLanguage(pasteCreateRequest.getLanguage()));
        entity.setPasteUser(user.getId());
        entity.setFolder(pasteCreateRequest.getFolder());
        entity.setPasteDateTime(LocalDateTime.now());

        entity.setCreatedBy(createdBy);
        return entity;
    }

    public static void updateRequestToEntity(Paste entity, PasteUpdateRequest pasteUpdateRequest, String createdBy, User user) {

        if (pasteUpdateRequest.getDescription() != null) {
            entity.setDescription(pasteUpdateRequest.getDescription());
        }
        if (pasteUpdateRequest.getStatus() != null) {
            entity.setStatus(PasteStatus.getStatus(pasteUpdateRequest.getStatus()));
        }
        if (user != null) {
            entity.setPasteUser(user.getId());
        }
        entity.setCreatedBy(createdBy);
    }

    public static void addFeedbackToEntity(Paste entity, Feedback feedback, String createdBy, Long user) {

        if (feedback.getComment() != null) {
            entity.addFeedBack(feedback);
        }
        entity.setCreatedBy(createdBy);
    }

}
