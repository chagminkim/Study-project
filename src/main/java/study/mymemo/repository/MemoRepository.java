package study.mymemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mymemo.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    boolean existsByTitle(String title);

}
