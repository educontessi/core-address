package io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.StreetEntity;
import io.github.educontessi.core.address.core.filter.StreetFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StreetRepositoryImpl implements StreetRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<StreetEntity> search(StreetFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<StreetEntity> criteria = builder.createQuery(StreetEntity.class);
        Root<StreetEntity> root = criteria.from(StreetEntity.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<StreetEntity> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(predicates));
    }

    private Predicate[] createRestrictions(StreetFilter filter, CriteriaBuilder builder, Root<StreetEntity> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter != null && !StringUtils.isEmpty(filter.getName())) {
            predicates.add(builder.like(root.get(StreetFilter.COLUMN_NAME), "%" + filter.getName().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private void addPaginationRestrictions(TypedQuery<?> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPageRecord);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Long total(Predicate[] predicates) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<StreetEntity> root = criteria.from(StreetEntity.class);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
