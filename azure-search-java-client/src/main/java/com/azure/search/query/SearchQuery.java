package com.azure.search.query;

import com.azure.search.query.filter.FilterExp;

import java.util.Optional;
import static java.lang.String.*;

public class SearchQuery {
    private String search;
    private Optional<Boolean> count = Optional.empty();
    private Optional<Integer> skip = Optional.empty();
    private Optional<Integer> top = Optional.empty();
    private Optional<FilterExp> filter = Optional.empty();

    public static SearchQuery forTerm(String term){
        return new SearchQuery(term);
    }

    private SearchQuery(String search) {
        this.search = search;
    }

    public SearchQuery filter(FilterExp filter) {
        this.filter = Optional.ofNullable(filter);
        return this;
    }

    public SearchQuery includeCount() {
        this.count = Optional.of(Boolean.TRUE);
        return this;
    }

    public SearchQuery skip(int n) {
        this.skip = Optional.of(n);
        return this;
    }

    public SearchQuery top(int size) {
        this.top = Optional.of(size);
        return this;
    }

    public String toQuery(){
        StringBuilder sb = new StringBuilder(format("search=%s", search));

        count.ifPresent(c -> sb.append(format("&$count=%s", c)));
        skip.ifPresent(s -> sb.append(format("&$skip=%s", s)));
        top.ifPresent(t -> sb.append(format("&$top=%s", t)));
        filter.ifPresent(f -> sb.append(format("&$filter=%s", f.toQuery())));

        return sb.toString();
    }
}
