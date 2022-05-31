package com.example.myretrofit3;

// 싱글톤패턴

public class MyPrinter {
    private static MyPrinter myPrinter;

    private MyPrinter() {
    }

    public static MyPrinter getInstance() {
        if (myPrinter == null) {
            myPrinter = new MyPrinter();
        }
        return myPrinter;
    }

    // 테스트코드 작성
    public static void main(String[] args) {
        AClass aClass = new AClass();
        BClass bClass = new BClass();

        // 래퍼런스 주소 비교(같은 녀석이면 같은 객체겠죠)
        MyPrinter myPrinterA = aClass.myPrinter;
        MyPrinter myPrinterB = bClass.myPrinter;
        System.out.println(myPrinterA.equals(myPrinterB)); // true나온다.
    }
}

class AClass {
    // AClass는 MyPrinter를 포함하고 있다(포함관계)
    public MyPrinter myPrinter;

    public AClass() {
        myPrinter = MyPrinter.getInstance();
    }
}

class BClass {
    public MyPrinter myPrinter;

    public BClass() {
        myPrinter = MyPrinter.getInstance();
    }
}
