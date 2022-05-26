package com.example.myaddview;

import java.util.ArrayList;

public class Fruit {
    String imageUrl;
    String name;
    String price;
    String count;

    public Fruit(String imageUrl, String name, String price, String count){
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
    }

    // 샘플 데이터 만들어 보기
    public static ArrayList<Fruit> getFruits(){
        ArrayList<Fruit> list = new ArrayList<>();
        list.add(new Fruit("https://cdn.pixabay.com/photo/2015/06/19/16/48/watermelon-815072__340.jpg", "수박","10000","1"));
        list.add(new Fruit("https://cdn.pixabay.com/photo/2017/08/02/22/46/peaches-2573836__480.jpg", "복숭아","11000","1"));
        list.add(new Fruit("https://cdn.pixabay.com/photo/2017/05/13/17/31/fruit-2310212__340.jpg", "포도","12000","1"));
        list.add(new Fruit("https://cdn.pixabay.com/photo/2017/05/13/17/31/fruit-2310212__340.jpg", "딸기","13000","1"));
        list.add(new Fruit("https://cdn.pixabay.com/photo/2017/05/13/17/31/fruit-2310212__340.jpg", "블루베리","14000","1"));
        list.add(new Fruit("https://cdn.pixabay.com/photo/2017/05/13/17/31/fruit-2310212__340.jpg", "레몬","15000","1"));
        list.add(new Fruit("https://cdn.pixabay.com/photo/2017/05/13/17/31/fruit-2310212__340.jpg", "참외","16000","1"));
        return list;
        }
}
