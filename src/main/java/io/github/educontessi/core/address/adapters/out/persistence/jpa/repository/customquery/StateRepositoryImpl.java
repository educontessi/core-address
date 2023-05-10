package io.github.educontessi.core.address.adapters.out.persistence.jpa.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.entity.StateEntity;
import io.github.educontessi.core.address.core.filter.StateFilter;
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

public class StateRepositoryImpl implements StateRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<StateEntity> search(StateFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<StateEntity> criteria = builder.createQuery(StateEntity.class);
        Root<StateEntity> root = criteria.from(StateEntity.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<StateEntity> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(predicates));
    }

    private Predicate[] createRestrictions(StateFilter filter, CriteriaBuilder builder, Root<StateEntity> root) {
        List<Predicate> predicates = new ArrayList<>();

        if(filter != null){
            if (!StringUtils.isEmpty(filter.getName())) {
                predicates.add(builder.like(root.get(StateFilter.COLUMN_NAME), "%" + filter.getName().toLowerCase() + "%"));
            }

            if (!StringUtils.isEmpty(filter.getUf())) {
                predicates.add(builder.equal(root.get(StateFilter.COLUMN_UF), filter.getUf().toUpperCase()));
            }
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
        Root<StateEntity> root = criteria.from(StateEntity.class);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
