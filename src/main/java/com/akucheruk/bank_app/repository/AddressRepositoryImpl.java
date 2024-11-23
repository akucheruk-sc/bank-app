package com.akucheruk.bank_app.repository;

import com.akucheruk.bank_app.domain.entity.Address;
import com.akucheruk.bank_app.domain.entity.AddressState;
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
public class AddressRepositoryImpl implements AddressRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Address> findAllByStateAndPostCodeAndStreet(AddressState state,
                                                            Integer postCode,
                                                            String street) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Address> cq = cb.createQuery(Address.class);
        Root<Address> root = cq.from(Address.class);
        List<Predicate> predicates = new ArrayList<>();

        if (state != null) {
            predicates.add(cb.equal(root.get("state"), state));
        }
        if (postCode != null) {
            predicates.add(cb.equal(root.get("postCode"), postCode));
        }
        if (StringUtils.hasText(street)) {
            predicates.add(cb.equal(root.get("street"), street));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

}
