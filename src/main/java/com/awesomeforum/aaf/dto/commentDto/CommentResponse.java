package com.awesomeforum.aaf.dto.commentDto;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDateTime;

public record CommentResponse(
                              String content,
                              String username,
                              String profilePicture,
                              @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                              LocalDateTime createdAt) {
}
