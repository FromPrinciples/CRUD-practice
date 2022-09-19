package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // JPA의 데이터 변경이나 로직들은 가급적 트랜잭션 내부에서 수행되어야 한다.
@RequiredArgsConstructor // final field만 선택적으로 생성자 injection 생성
public class MemberService {

    //    @Autowired // field injection
    private final MemberRepository memberRepository;

    /**
 *      @Autowired // setter injection, 주입 순서의 차이. 하지만 보통 애플리케이션 로딩 시점에 조립이 끝나버리므로 쓸일이 없음
 *      public void setMemberRepository(MemberRepository memberRepository) {
 *          this.memberRepository = memberRepository;
 *      }
 *     @Autowired // 일반적으로 선호되는 생성자 injection, @RequiredArgsConstructor로 생략 가능
 *     public MemberService(MemberRepository memberRepository) {
 *         this.memberRepository = memberRepository;
 *     }
     */




    //회원 가입
    @Transactional // 읽기 아닌 쓰기에선 readOnly = true 사용 하면 안되므로 우선권가지고 부분적으로 readOnly = false가 먹힘
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증 로직
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }


    //회원 전체 조회
    @Transactional // 읽기엔 readOnly = true 사용 시 JPA가 성능 최적화 시켜줌
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member findOne(Long memberId) { // 회원 단일 조회
        return memberRepository.findOne(memberId);
    }

}