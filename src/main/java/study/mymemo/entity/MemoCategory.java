package study.mymemo.entity;

import jakarta.persistence.*;

public class MemoCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Memo memo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public MemoCategory(){
    }

    public MemoCategory(Long id, Memo memo, Category category) {
        this.id = id;
        this.memo = memo;
        this.category = category;
    }

    public void addMemoAndCategory(Memo memo, Category category) {
        this.memo.getMemoCategories().add(this);
        this.category.getMemoCategories().add(this);
    }

    public Long getId() {
        return id;
    }

    public Memo getMemo() {
        return memo;
    }

    public Category getCategory() {
        return category;
    }
}
