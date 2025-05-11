package study.mymemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.mymemo.dto.request.MemoRequestDto;
import study.mymemo.dto.response.MemoResponseDto;
import study.mymemo.repository.MemoRepository;
import study.mymemo.service.MemoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/memos")
public class MemoController {

    private final MemoService memoService;

    private final MemoRepository memoRepository;

    public MemoController(MemoService memoService,MemoRepository memoRepository) {
        this.memoService = memoService;
        this.memoRepository = memoRepository;
    }

    // 메모 생성
    @PostMapping
    public ResponseEntity<?> createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        try {
           return ResponseEntity.ok(memoService.createMemo(memoRequestDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 메모 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> fixMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto) {
        try {
            return ResponseEntity.ok(memoService.fixMemo(memoRequestDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 메모 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemo(@PathVariable Long id) {
        MemoResponseDto dto = memoService.getMemo(id);
        return ResponseEntity.ok(dto);

    }

    // 전체 메모 조회
    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> getMemos() {
        List<MemoResponseDto> memos = memoService.getMemos();
        return ResponseEntity.ok(memos);
    }

    @DeleteMapping("/{id}") // 메모 삭제
    public ResponseEntity<?> deleteMemo(@PathVariable("id") Long id){
        memoService.deleteMemo(id);
        return ResponseEntity.ok("삭제 되었습니다.");
    }

    @PatchMapping("/{id}") // 즐겨 찾기
    public ResponseEntity<?> toggleFavorite(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(memoService.toggleFavorite(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search") // 메모 검색
    public ResponseEntity<List<MemoResponseDto>> searchMemos(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String category
    ) {
        List<MemoResponseDto> results = memoService.searchMemos(title, category);
        return ResponseEntity.ok(results);
    }
}
