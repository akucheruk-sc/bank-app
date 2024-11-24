package com.akucheruk.bank_app.repository.transaction;

import com.akucheruk.bank_app.domain.dto.in.TransactionSearchRequest;
import com.akucheruk.bank_app.domain.entity.Transaction;
import com.akucheruk.bank_app.util.DateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TransactionRepositoryImpl implements TransactionRepositoryCustom {

    private final EntityManager em;

    @Override
    public Page<Transaction>    findAllByTransactionSearchRequestObj(
            TransactionSearchRequest transactionSearchRequest,
            Pageable page) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);
        List<Predicate> predicates = new ArrayList<>();

        if (transactionSearchRequest.getLessThanAmount() != null) {
            predicates.add(cb.le(root.get("amount"), transactionSearchRequest.getLessThanAmount()));
        }
        if (transactionSearchRequest.getMoreThanAmount() != null) {
            predicates.add(cb.ge(root.get("amount"), transactionSearchRequest.getMoreThanAmount()));
        }
        if (transactionSearchRequest.getStartFromDate() != null) {
            var startFrom = DateUtils.parseDate(transactionSearchRequest.getStartFromDate());
            predicates.add(cb.greaterThanOrEqualTo(root.get("processedTime"), startFrom));
        }
        if (transactionSearchRequest.getEndToDate() != null) {
            var endTo = DateUtils.parseDate(transactionSearchRequest.getEndToDate());
            predicates.add(cb.lessThanOrEqualTo(root.get("processedTime"), endTo));
        }

        if (!CollectionUtils.isEmpty(transactionSearchRequest.getStatuses())) {
            predicates.add(cb.and(root.get("status").in(transactionSearchRequest.getStatuses())));
        }
        cq.where(predicates.toArray(new Predicate[0]))
                .orderBy(QueryUtils.toOrders(page.getSort(), root, cb));

        TypedQuery<Transaction> query = em.createQuery(cq);
        int totalRows = query.getResultList().size();
        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

        return new PageImpl<>(query.getResultList(), page, totalRows);
    }
}
