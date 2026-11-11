package com.kandidato.model.dto;

import com.kandidato.constants.VacancyState;
import com.kandidato.model.dto.mark.Mark;
import com.kandidato.model.dto.mark.MatchMark;
import com.kandidato.model.dto.tag.TaggableDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class BaseVacancyDTO  implements Dto {

    private Long id;
    private String number;

    @NotNull
    private String name;

    @NotNull
    private VacancyState state;

    private boolean hot;

    @NotNull
    @NotEmpty
    private String requirements;


    private Date createTime;

    //TODO replace default mark
    private Set<? extends Mark> marks = Collections.singleton(new MatchMark(1L, 1L));

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VacancyState getState() {
        return state;
    }

    public void setState(VacancyState state) {
        this.state = state;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public Set<? extends Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<? extends Mark> marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseVacancyDTO)) return false;

        BaseVacancyDTO that = (BaseVacancyDTO) o;

        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
