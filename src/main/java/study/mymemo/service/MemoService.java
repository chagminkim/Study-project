package study.mymemo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import study.mymemo.dto.request.UpdateMemoRequestDto;
import study.mymemo.dto.response.MemoResponseDto;
import study.mymemo.entity.Category;
import study.mymemo.entity.Memo;
import study.mymemo.entity.MemoCategory;
import study.mymemo.exception.BaseException;
import study.mymemo.exception.ErrorCode;
import study.mymemo.repository.CategoryRepository;
import study.mymemo.repository.MemoCategoryRespository;
import study.mymemo.repository.MemoRepository;

@Service
@Transactional
public class MemoService {
    private final MemoRepository memoRepository;

    private final CategoryRepository categoryRepository;

    private final MemoCategoryRespository memoCategoryRespository;

    public MemoService(MemoRepository memoRepository, CategoryRepository categoryRepository, MemoCategoryRespository memoCategoryRespository) {
        this.memoRepository = memoRepository;
        this.categoryRepository = categoryRepository;
        this.memoCategoryRespository = memoCategoryRespository;
    }

    public MemoResponseDto save(MemoResponseDto request) {
        if (request.title() == null || request.title().isEmpty()) {
            throw new BaseException(ErrorCode.INVALID_REQUEST);
        }

        if (request.title().length() > 20) {
            throw new BaseException(ErrorCode.INVALID_REQUEST);
        }

        if (request.content() == null || request.content().isEmpty()) {
            throw new BaseException(ErrorCode.INVALID_REQUEST);
        }

        if (request.content().length() > 1000) {
            throw new BaseException(ErrorCode.INVALID_REQUEST);
        }

        if (memoRepository.existsByTitle(request.title())) {
            throw new BaseException(ErrorCode.DUPLICATE_TITLE);
        }

        Memo memo = new Memo(request.title(), request.content());
        memoRepository.save(memo);

        for (Long categoryId : request.categoryIds()) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_CATEGORY));

            MemoCategory memoCategory = new MemoCategory(memo, category);
            memoCategory.addMemoAndCategory(memo, category);

            memoCategoryRepository.save(memoCategory);
        }

        return MemoResponseDto.of(memo);
    }

    public MemoResponseDto update(Long memoId, UpdateMemoRequestDto request) {
        if (!memoRepository.existsById(memoId)) {
            throw new BaseException(ErrorCode.NOT_FOUND_MEMO);
        }

        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_REQUEST));

        if (request.title() != null && !request.title().isEmpty()) {
            memo.updateTitle(request.title());
        }

        if (request.content() != null && !request.content().isEmpty()) {
            memo.updateContent(request.content());
        }

        return MemoResponseDto.of(memo);
    }

    public void delete(Long memoId) {
        if (!memoRepository.existsById(memoId)) {
            throw new BaseException(ErrorCode.NOT_FOUND_MEMO);
        }

        memoRepository.deleteById(memoId);
    }
}
