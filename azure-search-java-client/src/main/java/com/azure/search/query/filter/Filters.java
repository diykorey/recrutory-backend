package com.azure.search.query.filter;

import static java.lang.String.format;

public class Filters {
    public static FilterExp not(FilterExp exp){
        return new NotExp(exp);
    }

    public static FilterExp and(FilterExp exp1, FilterExp exp2){
        return new BinaryLogicalExp(exp1, "and", exp2);
    }

    public static FilterExp or(FilterExp exp1, FilterExp exp2){
        return new BinaryLogicalExp(exp1, "or", exp2);
    }

    public static FilterExp eq(String field, Object value){
        return new ComparisonExp(field, "eq", value);
    }

    public static FilterExp ne(String field, Object value){
        return new ComparisonExp(field, "ne", value);
    }

    public static FilterExp lt(String field, Object value){
        return new ComparisonExp(field, "lt", value);
    }

    public static FilterExp ge(String field, Object value){
        return new ComparisonExp(field, "ge", value);
    }

    public static FilterExp le(String field, Object value){
        return new ComparisonExp(field, "le", value);
    }

    private static final class BinaryLogicalExp implements FilterExp {
        private final FilterExp exp1;
        private final FilterExp exp2;
        private final String op;

        private BinaryLogicalExp(FilterExp exp1, String op, FilterExp exp2) {
            this.exp1 = exp1;
            this.exp2 = exp2;
            this.op = op;
        }

        @Override
        public String toQuery() {
            return format("(%s+%s+%s)", exp1.toQuery(), op, exp2.toQuery());
        }
    }

    private static final class NotExp implements FilterExp {
        private final FilterExp exp;

        private NotExp(FilterExp exp) {
            this.exp = exp;
        }

        @Override
        public String toQuery() {
            return format("(not+%s)", exp.toQuery());
        }
    }

    private static final class ComparisonExp implements FilterExp {
        private final String query;

        private ComparisonExp(String field, String op, Object val) {
            this.query = format("(%s+%s+%s)", field, op, val);
        }

        @Override
        public String toQuery() {
            return query;
        }
    }
}
