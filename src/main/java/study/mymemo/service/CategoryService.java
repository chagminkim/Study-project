package study.mymemo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import study.mymemo.dto.request.CategoryCreateRequest;
import study.mymemo.dto.response.CategoryResponse;
import study.mymemo.entity.Category;
import study.mymemo.exception.BaseException;
import study.mymemo.exception.ErrorCode;
import study.mymemo.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse save(CategoryCreateRequest request) {
        if (request.name() == null || request.name().isEmpty()) {
            throw new BaseException(ErrorCode.INVALID_REQUEST);
        }

        if (request.name().length() > 10) {
            throw new BaseException(ErrorCode.INVALID_REQUEST);
        }

        if (categoryRepository.existsByName(request.name())) {
            throw new BaseException(ErrorCode.DUPLICATE_TITLE);
        }

        Category category = new Category(request.name());

        categoryRepository.save(category);

        return CategoryResponse.of(category);
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new BaseException(ErrorCode.NOT_FOUND_CATEGORY);
        }

        categoryRepository.deleteById(id);
    }
}
