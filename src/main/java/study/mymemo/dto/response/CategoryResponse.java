package study.mymemo.dto.response;

import study.mymemo.entity.Category;

public record CategoryResponse(
        Long id,
        String name,
        String createdAt
) {
    public static CategoryResponse of(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getCreatedAt().toString()
        );
    }
}
