package com.kandidato.model.dto.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class TagsIn {


    @Size(min = 1)
    @NotNull
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TagsIn{");
        sb.append("tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }
}
