package study.mymemo.dto.request;

public record MemoUpdateRequest(
        String title,
        String content
) {
}
