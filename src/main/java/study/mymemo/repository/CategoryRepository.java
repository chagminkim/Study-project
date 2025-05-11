package study.mymemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mymemo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category , Long> {
}
