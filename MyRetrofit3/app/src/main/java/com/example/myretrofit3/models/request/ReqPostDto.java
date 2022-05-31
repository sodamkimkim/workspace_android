package com.example.myretrofit3.models.request;

/**
 * {
 *      title: 'foo',
 *     body: 'bar',
 *     userId: 1,
 *   }),
 *   
 *   A(모바일) --> 객체를 생성해서 --> 서비스 로직 매개변수에 전달하는 코드를 사용
 *   => 객체 생성해서 데이터 보내야 한다.
 *   => requestdto에서는 생성자를 만들어야 한다는 소리
 */
public class ReqPostDto {

    public String title;
    public String body;
    public Integer userId;

    public ReqPostDto(String title, String body, Integer userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
