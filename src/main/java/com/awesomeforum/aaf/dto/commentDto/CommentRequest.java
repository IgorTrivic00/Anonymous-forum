package com.awesomeforum.aaf.dto.commentDto;

import com.awesomeforum.aaf.entity.User;

public record CommentRequest(Long postId,
                             String content,
                             User user) {
}
