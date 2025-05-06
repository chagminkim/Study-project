package study.mymemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mymemo.entity.MemoCategory;

public interface MemoCategoryRepository extends JpaRepository<MemoCategory, Long> {
}
