package io.github.educontessi.core.address.adapters.out.persistence.repository.customquery;

import io.github.educontessi.core.address.adapters.out.persistence.entity.CountryEntity;
import io.github.educontessi.core.address.core.filter.CountryFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CountryRepositoryImpl implements CountryRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<CountryEntity> search(CountryFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<CountryEntity> criteria = builder.createQuery(CountryEntity.class);
        Root<CountryEntity> root = criteria.from(CountryEntity.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<CountryEntity> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(predicates));
    }

    private Predicate[] createRestrictions(CountryFilter filter, CriteriaBuilder builder, Root<CountryEntity> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter != null && !StringUtils.isEmpty(filter.getName())){
            predicates.add(builder.like(root.get(CountryFilter.COLUMN_NAME), "%" + filter.getName().toLowerCase() + "%"));
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
        Root<CountryEntity> root = criteria.from(CountryEntity.class);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
