package com.ccsw.tutorial.prestamo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.prestamo.model.Prestamo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PrestamoSpecification implements Specification<Prestamo> {

    private static final long serialVersionUID = 1L;

    private final SearchCriteria criteria;

    public PrestamoSpecification(SearchCriteria criteria) {

        this.criteria = criteria;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Predicate toPredicate(Root<Prestamo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null) {
            Path<String> path = (Path<String>) getPath(root);
            if (path.getJavaType() == String.class) {
                return builder.like(path, "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(path, criteria.getValue());
            }
        }

        if (criteria.getOperation().equalsIgnoreCase("<=") && criteria.getValue() != null) {
            Path<Date> path = (Path<Date>) getPath(root);

            Date date;
            try {

                date = parseStringToDate(criteria.getValue().toString());
                return builder.greaterThanOrEqualTo(path, date);
            } catch (ParseException e) {

                e.printStackTrace();
            }

        } else if (criteria.getOperation().equalsIgnoreCase(">=") && criteria.getValue() != null) {
            Path<Date> path = (Path<Date>) getPath(root);

            Date date;
            try {

                date = parseStringToDate(criteria.getValue().toString());
                return builder.lessThanOrEqualTo(path, date);
            } catch (ParseException e) {

                e.printStackTrace();
            }
        }

        return null;
    }

    public static Date parseStringToDate(String dateString) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(dateString);
        return new Date(parsedDate.getTime());
    }

    private Path<?> getPath(Root<Prestamo> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }

}
