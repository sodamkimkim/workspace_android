package com.example.myretrofit3;
// 빌더패턴 만들기
// this키워드에 대한 충분한 이해가 있으면 어렵지 않다.
public class Student {
    private String name;
    private int grade;

    //1. 기본 생성자를 private 으로 정의한다.
    private Student() {}

    //2. 내부 클래스를 생성(static --> public , default, private으로도 만들 수 있다.
    // 접근지시자에 static을 붙이게 되면 내부정적클래스라고 한다.)
    public static class MyBuilder {
        // 2-1. out class 멤버 변수를 한번 더 정의한다.
        // 단, priavte
        private String name;
        private int grade;

        //2-2. setter 메서드를 만들어 준다.(하지만 return 타입은 자기 자신이다.--> this키워드를 사용)
        public MyBuilder setName(String name){
            this.name = name;
            return this;
        }
        public MyBuilder setGrade(int grade){
            this.grade=grade;
            return this;
        }
        // 3.핵심!!
        //outer-class 반환하는 메서드를 만들어준다.
        //개발자들끼리 암묵적으로 build()쓴다.
        public Student build(){
            Student student = new Student();
            student.name = name; // outer-class의 멤버, = , inner-class의 멤버
            student.grade = grade;
            return student;
        }

    }
        public static void main(String[] args) {
            Student student1 = new MyBuilder().setName("홍길동").build();
            Student student2 = new MyBuilder().setGrade(1).build();
            Student student3 = new MyBuilder().setName("이순신").setGrade(3).build();
            // 빌더패턴으로 만들게되면 쓰는 입장에서 편하게 객체를 메모리에 올려 활용할 수 있다.
            // 매게변수가 15개인데 2개만 넣고싶을 때, 2개 외 다른 변수는 null값 안넣고 빌더패턴으로 2개값만 넣어 사용할 수 있다.
            //retrofit에서 .build() --> 빌더패턴임.
        }

}
