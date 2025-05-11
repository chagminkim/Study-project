package study.mymemo.dto.response;

import study.mymemo.entity.Category;
import study.mymemo.entity.Memo;

import java.time.LocalDateTime;
import java.util.List;

public record MemoResponseDto(
        Long id,
        String title,
        String content,
        String createdAt,
        List<CategoryResponseDto> categories
) {
    public static MemoResponseDto of(Memo memo) {
        return new MemoResponseDto(
                memo.getId(),
                memo.getTitle(),
                memo.getContent(),
                memo.getCreatedAt().toString(),
                memo.getMemoCategories()
                        .stream()
                        .map(memoCategory -> CategoryResponse.of(memoCategory.getCategory()))
                        .toList()
        );
    }


}
