package com.awesomeforum.aaf.dto.postDto;

import com.awesomeforum.aaf.entity.type.PostType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record PostResponse(

        String content,

        PostType postType,

        String link,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,
        String author
) {
}
