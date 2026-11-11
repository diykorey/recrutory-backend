package com.kandidato.persistence.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Set;

@Document(indexName = "vacancies")
public class VacancyDocument {

    @Id
    private String id;

    private String number;
    private String name;
    private long userId;
    private boolean hot;

    private Set<String> tags;

    private String requirements;

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public long getUserId() {
        return userId;
    }

    public boolean isHot() {
        return hot;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getRequirements() {
        return requirements;
    }

    public VacancyDocument(String number, String name, long userId, boolean hot,
                           Set<String> tags, String requirements) {
        this.number = number;
        this.name = name;
        this.userId = userId;
        this.hot = hot;
        this.tags = tags;
        this.requirements = requirements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VacancyDocument that = (VacancyDocument) o;

        if (!number.equals(that.number)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        return "VacancyDocument{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
