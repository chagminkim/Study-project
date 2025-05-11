package study.mymemo.dto.request;

import java.util.List;

public record MemoRequestDto(
        String title,
        String content,
        List<Long>categoryIds
) {
}
