package ru.kpfu.itis.charntsev.net.dto;

public class PostDto {
    private int id;
    private int authorId;
    private String name;
    private String login;
    private String title;
    private String description;
    private String text;
    private String category;
    private int price;
    private String image;

    public PostDto(int id, int authorId, String name, String login, String title,
                   String description, String text, String category, int price, String image) {
        this.id = id;
        this.authorId = authorId;
        this.name = name;
        this.login = login;
        this.title = title;
        this.description = description;
        this.text = text;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public PostDto(int authorId, String name, String login, String title,
                   String description, String text, String category, int price, String image) {
        this.authorId = authorId;
        this.name = name;
        this.login = login;
        this.title = title;
        this.description = description;
        this.text = text;
        this.category = category;
        this.price = price;
        this.image = image;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

