package com.akucheruk.bank_app.repository.client;

import com.akucheruk.bank_app.domain.dto.in.ClientRequest;
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
    public List<Client> findAllByClientRequestObj(ClientRequest clientRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> root = cq.from(Client.class);
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(clientRequest.getFirstName())) {
            predicates.add(cb.equal(root.get("firstName"), clientRequest.getFirstName()));
        }
        if (StringUtils.hasText(clientRequest.getLastName())) {
            predicates.add(cb.equal(root.get("lastName"), clientRequest.getLastName()));
        }
        if (clientRequest.getIsActive() != null) {
            predicates.add(cb.equal(root.get("isActive"), clientRequest.getIsActive()));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

}
