package models;

import java.io.Serializable;
import java.util.ArrayList;
// 직렬화 : object화..-> byte
// 역직렬화 : byte -> object
public class Food implements Serializable {
    private String thumbnail;
    private String title;
    private String subTitle;
    private String detail;
        // alt + insert => 생성자 만들기, shift눌러서 화살표 내리면 다 선택 가능
    public Food(String thumbnail, String title, String subTitle, String detail) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.subTitle = subTitle;
        this.detail = detail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return "Food{" +
                "thumbnail='" + thumbnail + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public static ArrayList<Food> getSampleData(){
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food1","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food2","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food3","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food4","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food5","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food6","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food7","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food8","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food9","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food10","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food11","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food12","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food13","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food14","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food15","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food16","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food17","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food18","subTitle1","detail"));
        foods.add(new Food("https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_960_720.jpg", "Food19","subTitle1","detail"));
        return foods;
    }
}
