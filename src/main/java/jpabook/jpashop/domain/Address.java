package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { // @Setter는 제거, 값 타입 변경 불가능하게 설계
                        // JPA 구현 라이브러리가 객체 생성 시 리플렉션 같은 기술을 사용하도록 지원해야 하기 때문

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}


