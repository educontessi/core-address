package io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.NeighborhoodEntity;
import io.github.educontessi.core.address.core.filter.NeighborhoodFilter;
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

public class NeighborhoodRepositoryImpl implements NeighborhoodRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<NeighborhoodEntity> search(NeighborhoodFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<NeighborhoodEntity> criteria = builder.createQuery(NeighborhoodEntity.class);
        Root<NeighborhoodEntity> root = criteria.from(NeighborhoodEntity.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<NeighborhoodEntity> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(predicates));
    }

    private Predicate[] createRestrictions(NeighborhoodFilter filter, CriteriaBuilder builder, Root<NeighborhoodEntity> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter != null && !StringUtils.isEmpty(filter.getName())) {
            predicates.add(builder.like(root.get(NeighborhoodFilter.COLUMN_NAME), "%" + filter.getName().toLowerCase() + "%"));
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
        Root<NeighborhoodEntity> root = criteria.from(NeighborhoodEntity.class);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
