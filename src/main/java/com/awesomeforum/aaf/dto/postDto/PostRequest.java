package com.awesomeforum.aaf.dto.postDto;

import com.awesomeforum.aaf.entity.User;
import com.awesomeforum.aaf.entity.type.PostType;

public record PostRequest(

        String content,

        PostType postType,
        User user,
        String link

) {
}
