package study.mymemo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private Boolean isFavorite;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "memo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemoCategory> memoCategories;

    public Memo() {
    }

    public Memo(String title, String content) {
        this.title = title;
        this.content = content;
        this.isFavorite = false;
        this.memoCategories = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<MemoCategory> getMemoCategories() {
        return memoCategories;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
