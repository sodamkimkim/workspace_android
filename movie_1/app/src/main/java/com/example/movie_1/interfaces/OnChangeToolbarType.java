package com.example.movie_1.interfaces;

// 1. 콜백 메서드를 만들 때 인터페이스를 선언 한다.
// 1.1 추상 메서드를 선언 한다.(구분해야 될 부분/ 데이터를 전달해야 할 경우, 매개변수를 만들어 준다.)
// 2. 호출자에 가서 멤버변수 선언(우리가 만든 인터페이스를)
// 2.1 필요한 곳에서 OnChangeToolbarType에 속해있는 추상메서드를 호출하면 된다.
// (
// (MovieFragment, InfoFragment) <-- 호출자가 됨.
public interface OnChangeToolbarType {
    // 코드를 짤 때,,
    // 확장성 있게, 유지보수 편하게 생각을 해 본다.
    void onSetupType(String title);
    // fragment -->  메서드 호출(moviefragment(movie), Infofragment(Info)) --> 값만 던지면 됨.
    // 메인 엑티비티에서 --> 콜백 받음

}
