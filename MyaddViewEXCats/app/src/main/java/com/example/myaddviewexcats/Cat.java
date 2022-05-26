package com.example.myaddviewexcats;

import java.util.ArrayList;

public class Cat {
    String imageUrl;
    String catName;
    String familyName;
    String phoneNum;

    public Cat(String imageUrl, String catName, String familyName, String phoneNum){
        this.imageUrl = imageUrl;
        this.catName = catName;
        this.familyName = familyName;
        this.phoneNum = phoneNum;
    }

    public static ArrayList<Cat> getCats(){
        ArrayList<Cat> list = new ArrayList<>();
        list.add(new Cat("https://cdn.pixabay.com/photo/2017/11/14/13/06/kitty-2948404_960_720.jpg","냥냥이","순이네","010-5027-0220"));
        list.add(new Cat("https://cdn.pixabay.com/photo/2016/09/05/21/37/cat-1647775_960_720.jpg","잠잠이","가나네","010-5027-0220"));
        list.add(new Cat("https://media.istockphoto.com/photos/shiba-inu-puppy-and-his-friend-grey-kitty-picture-id1312619551?s=612x612","다라","다라네","010-5027-0220"));
        list.add(new Cat("https://cdn.pixabay.com/photo/2017/02/20/18/03/cat-2083492_960_720.jpg","구마","마바네","010-5027-0220"));
        list.add(new Cat("https://cdn.pixabay.com/photo/2018/07/13/10/20/kittens-3535404_960_720.jpg","고구마","사아네","010-5027-0220"));
        list.add(new Cat("https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_960_720.jpg","만두","자차네","010-5027-0220"));
        list.add(new Cat("https://cdn.pixabay.com/photo/2016/11/14/09/14/cat-1822979_960_720.jpg","냥펀치","카타네","010-5027-0220"));
        list.add(new Cat("https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg","냐옹이","파하네","010-5027-0220"));
        return list;
    }

}
