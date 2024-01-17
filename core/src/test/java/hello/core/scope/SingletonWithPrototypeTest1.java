package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
        protoTypeBean1.addCount();
        Assertions.assertThat(protoTypeBean1.getCount()).isEqualTo(1);

        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);
        protoTypeBean2.addCount();
        Assertions.assertThat(protoTypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUserPrototype(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, ProtoTypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);
        // 생성 시점에 주입된 prototypeBean 사용
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
        // 생성 시점에 주입
        //private final ProtoTypeBean protoTypeBean;

        // 생성자 주입으로 바꿔야 함
//        @Autowired
//        private ObjectProvider<ProtoTypeBean> protoTypeBeanProvider;

        @Autowired
        private Provider<ProtoTypeBean> protoTypeBeanProvider;

        public int logic(){
            ProtoTypeBean protoTypeBean = protoTypeBeanProvider.get();
            //ProtoTypeBean protoTypeBean = protoTypeBeanProvider.getObject();
            protoTypeBean.addCount();
            int count = protoTypeBean.getCount();
            return count;
        }

//        @Autowired
//        public ClientBean(ProtoTypeBean protoTypeBean) {
//            this.protoTypeBean = protoTypeBean;
//        }

//        public int logic(){
//            protoTypeBean.addCount();
//            int count = protoTypeBean.getCount();
//            return count;
//        }

    }

    @Scope("prototype")
    static class ProtoTypeBean {
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init = " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy = " + this);
        }


    }

}
