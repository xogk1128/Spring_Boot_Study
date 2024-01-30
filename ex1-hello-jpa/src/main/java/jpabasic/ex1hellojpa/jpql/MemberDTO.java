package jpabasic.ex1hellojpa.jpql;

public class MemberDTO {

    private String username;

    private String age;

    public MemberDTO(String username, String age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
