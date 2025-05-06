package study.mymemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.mymemo.dto.request.MemoCreateRequest;
import study.mymemo.dto.request.MemoUpdateRequest;
import study.mymemo.dto.response.MemoResponse;
import study.mymemo.service.MemoService;

@RestController
@RequestMapping("/api/v1/memos")
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    // 메모 생성
    @PostMapping("/")
    public ResponseEntity<MemoResponse> createMemo(@RequestBody MemoCreateRequest request) {
        return ResponseEntity.ok(memoService.save(request));
    }

    // 메모 수정
    @PutMapping("/{id}")
    public ResponseEntity<MemoResponse> updateMemo(@PathVariable Long id, @RequestBody MemoUpdateRequest request) {
        return ResponseEntity.ok(memoService.update(id, request));
    }

    // 메모 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {
        memoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
