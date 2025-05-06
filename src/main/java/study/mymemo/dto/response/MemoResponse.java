package study.mymemo.dto.response;

import study.mymemo.entity.Memo;

import java.util.List;

public record MemoResponse(
        Long id,
        String title,
        String content,
        String createdAt,
        List<CategoryResponse> categories
) {
    public static MemoResponse of(Memo memo) {
        return new MemoResponse(
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
