package com.akucheruk.bank_app.repository.client;

import com.akucheruk.bank_app.domain.dto.in.ClientSearchRequest;
import com.akucheruk.bank_app.domain.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ClientRepositoryImpl implements ClientRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Client> findAllByClientRequestObj(ClientSearchRequest clientSearchRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> root = cq.from(Client.class);
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(clientSearchRequest.getFirstName())) {
            predicates.add(cb.equal(root.get("firstName"), clientSearchRequest.getFirstName()));
        }
        if (StringUtils.hasText(clientSearchRequest.getLastName())) {
            predicates.add(cb.equal(root.get("lastName"), clientSearchRequest.getLastName()));
        }
        if (clientSearchRequest.getIsActive() != null) {
            predicates.add(cb.equal(root.get("isActive"), clientSearchRequest.getIsActive()));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

}
