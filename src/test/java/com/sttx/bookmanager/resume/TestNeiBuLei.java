 package com.sttx.bookmanager.resume;

 public class TestNeiBuLei {
    static {
        TestParant.a = 3;
    }
    static class TestParant {
        public static int a = 1;
        static{
            a = 2;
         }
    }

    public static void main(String[] args) {
        System.out.println(TestParant.a);
    }
}
