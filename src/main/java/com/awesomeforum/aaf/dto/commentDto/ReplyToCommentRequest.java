package com.awesomeforum.aaf.dto.commentDto;

import com.awesomeforum.aaf.entity.User;

public record ReplyToCommentRequest(Long postId,
                                    Long parentCommentId,
                                    User user,
                                    String content) {
}
