package com.example.myretrofit3.models.response;
// https://jsonplaceholder.typicode.com/posts/1

/**
 * {
 *         "userId": 1,
 *         "id": 1,
 *         "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
 *         "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
 *         }
 */
public class ResponsePostDto {
    public int userId;
    public int id;
    public String title;
    public String body;
    //실제론 private + getter setter쓰기

    @Override
    public String toString() {
        return "ResponsePostDto{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
