package com.example.project;

class ProductPojo {

    private int id;
    private String name;
    private String description;
    private String genreType;
    private String type;
    private String imageURL;
    private String language;

    ProductPojo(int id, String name, String description, String genreType, String type, String imageURL, String language) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genreType = genreType;
        this.type = type;
        this.imageURL = imageURL;
        this.language = language;
    }

    String getLanguage() {
        return language;
    }

    String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getDescription() {
        return description;
    }

    String getGenreType() {
        return genreType;
    }

    String getType() {
        return type;
    }

}
