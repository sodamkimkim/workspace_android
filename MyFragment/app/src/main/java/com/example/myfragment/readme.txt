Fragment
- 프래그먼트는 하나의 액티비티가 여러개의 화면을 가지도록 만들기 위해 사용하는 개념.
A Fragment represents a reusable portion of your app's UI.
A fragment defines and manages its own layout,
has its own lifecycle,
and can handle its own input events.
Fragments cannot live on their own
--they must be hosted by an activity or another fragment.

The fragment’s view hierarchy becomes part of,
or attaches to,
the host’s view hierarchy.

사용방법)
- XML ViewComponent 로 추가한다.(xml에서 직접 사용 가능)
- 코드로 (자바, 코틀린) 동적으로 추가한다.
(추가하고 싶을 때 추가를 하고 없애고 싶을 때 제거할 수 있다.)

(문법)(코드)
중요!
라이프 사이클이 존재한다.(액티비티와 마찬가지로 생명주기가 있다.)
액티비티에 종속적이다.