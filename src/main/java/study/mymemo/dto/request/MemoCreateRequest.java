package study.mymemo.dto.request;

import java.util.List;

public record MemoCreateRequest(
        String title,
        String content,
        List<Long> categoryIds
) {
}
