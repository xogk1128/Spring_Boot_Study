package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy dicountPolicy = new RateDiscountPolicy();


    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야한다")
    public void vip_o() throws Exception{
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = dicountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }
    
    // 테스트할때는 안되는 경우도 해봐야함
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    public void vip_x() throws Exception{
        //given
        Member member = new Member(2L, "memberVIP", Grade.BASIC);
        //when
        int discount = dicountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }

}