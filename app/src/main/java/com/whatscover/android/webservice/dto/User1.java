package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 30.04.2017.
 */

public class User1 {

    @SerializedName("id")
    private Integer id;
    @SerializedName("login")
    private String login;
    @SerializedName("password_hash")
    private String password_hash;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("email")
    private String email;
    @SerializedName("image_url")
    private String image_url;
    @SerializedName("activated")
    private Boolean activated;
    @SerializedName("lang_key")
    private String lang_key;
    @SerializedName("activation_key")
    private String activation_key;
    @SerializedName("reset_key")
    private String reset_key;

    public User1(Integer id, String login, String password_hash, String first_name, String last_name,
                 String email, String image_url, Boolean activated, String lang_key,
                 String activation_key, String reset_key) {
        this.id = id;
        this.login = login;
        this.password_hash = password_hash;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.image_url = image_url;
        this.activated = activated;
        this.lang_key = lang_key;
        this.activation_key = activation_key;
        this.reset_key = reset_key;
    }

    public User1(){

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setLang_key(String lang_key) {
        this.lang_key = lang_key;
    }

    public String getLang_key() {
        return lang_key;
    }

    public void setActivation_key(String activation_key) {
        this.activation_key = activation_key;
    }

    public String getActivation_key() {
        return activation_key;
    }

    public void setReset_key(String reset_key) {
        this.reset_key = reset_key;
    }

    public String getReset_key() {
        return reset_key;
    }
}
