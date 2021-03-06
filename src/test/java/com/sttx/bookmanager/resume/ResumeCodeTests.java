 package com.sttx.bookmanager.resume;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ResumeCodeTests {
    @Test
    public void test1() {
        int type = 4;
        switch (type) {
            default:
                System.out.println(4);
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
        }
    }

    @Test
    public void test2() throws Exception {
        try {
            throw new Exception("1");
        } catch (IOException e) {
            throw new Exception("2");
        } catch (Exception e) {
            throw new Exception("3");
        } finally {
            throw new Exception("4");
        }
    }

    @Test
    public void threadTest() {
        ExecutorService pool = Executors.newCachedThreadPool();
    }

    @Test
    public void java8Test() {
        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
    }

}
