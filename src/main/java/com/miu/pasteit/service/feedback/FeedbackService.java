package com.miu.pasteit.service.feedback;

import com.miu.pasteit.component.exception.EntityNotFoundException;
import com.miu.pasteit.component.exception.ValidationException;
import com.miu.pasteit.model.dto.FeedbackModel;
import com.miu.pasteit.model.entity.common.PasteStatus;
import com.miu.pasteit.model.entity.db.nosql.Feedback;
import com.miu.pasteit.model.entity.db.nosql.Paste;
import com.miu.pasteit.model.entity.db.sql.User;
import com.miu.pasteit.model.mapper.FeedbackMapper;
import com.miu.pasteit.model.request.FeedbackCreateRequest;
import com.miu.pasteit.repository.mongo.FeedbackRepository;
import com.miu.pasteit.repository.mongo.PasteRepository;
import com.miu.pasteit.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Abdi Wako Jilo
 * @author Rimon Mostafiz
 */
@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackService {
    public static final Supplier<EntityNotFoundException> pasteNotFound = () -> new EntityNotFoundException(HttpStatus.BAD_REQUEST, "pasteId", "error.paste.not.found");
    public static final Supplier<ValidationException> notOwnPaste = () -> new ValidationException(HttpStatus.UNAUTHORIZED, "pasteId", "error.paste.user.not.authorized");

    private final UserService userservice;
    private final PasteRepository pasteRepository;
    private final FeedbackRepository feedbackRepository;

    public List<FeedbackModel> createFeedback(String id, FeedbackCreateRequest feedbackCreateRequest, String requestUser) {
        User user = userservice.getUserByUsername(requestUser);
        Paste paste = pasteRepository.findById(id).orElseThrow(pasteNotFound);
        if (paste.getStatus() == PasteStatus.DELETED) {
            throw new ValidationException(HttpStatus.BAD_REQUEST, "status", "error.paste.status.deleted.not.editable");
        }

        Feedback feedback = FeedbackMapper.createRequestToEntity(feedbackCreateRequest, requestUser, user, paste);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return feedbackRepository.findAllByPasteId(paste.getId())
                .stream()
                .map(FeedbackMapper::mapper)
                .collect(Collectors.toList());
    }

    public List<Feedback> getAllFeedbackForPaste(String pasteId) {
        return feedbackRepository.findAllByPasteId(pasteId);
    }

}
