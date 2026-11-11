package com.kandidato.model.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by andriy on 1/13/15.
 */
public class BaseUserDTO implements Dto {

    private Long id;

    @NotNull
    private String login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public BaseUserDTO() {
    }

    public BaseUserDTO(String login) {
        this.id = null;
        this.login = login;
    }

    public BaseUserDTO(Long id, String login) {
        this(login);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseUserDTO)) return false;

        BaseUserDTO that = (BaseUserDTO) o;

        if (!id.equals(that.id)) return false;
        if (!login.equals(that.login)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BaseUserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
