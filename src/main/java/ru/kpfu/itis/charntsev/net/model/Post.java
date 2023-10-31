package ru.kpfu.itis.charntsev.net.model;

public class Post {
    private int id;
    private int authorId;
    private String title;
    private String description;
    private String text;
    private String category;
    private int price;

    private String photo;

    public Post(int id, int authorId, String title, String description, String text, String category, int price, String photo) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.description = description;
        this.text = text;
        this.category = category;
        this.price = price;
        this.photo = photo;
    }

    public Post(int authorId, String title, String description, String text, String category, int price, String photo) {
        this.authorId = authorId;
        this.title = title;
        this.description = description;
        this.text = text;
        this.category = category;
        this.price = price;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
