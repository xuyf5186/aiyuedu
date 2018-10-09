package cn.entity;

import java.util.Date;

public class Chapter {
    private Integer id;

    private Integer bookId;

    private Book book;

    private Integer catalogId;

    private Catalog catalog;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private String content;

    public Chapter()
    {
    }
    public Chapter( String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}