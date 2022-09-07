package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository { // 엔티티 조회 목적, DAO랑 비슷한 개념

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Member member){ // 커맨드랑 쿼리를 분리하라 -> 저장의 사이드이펙트를 고려한 설계
       entityManager.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return entityManager.find(Member.class, id);
    }
}
