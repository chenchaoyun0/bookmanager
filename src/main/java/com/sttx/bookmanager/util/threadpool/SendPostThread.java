package com.sttx.bookmanager.util.threadpool;

import org.apache.commons.pool.impl.GenericObjectPool;

import com.sttx.bookmanager.util.http.HttpPostUtil;

public class SendPostThread extends Thread {

    private boolean isRunning;
    private GenericObjectPool pool;

    public boolean getIsRunning() {
        return isRunning;
    }

    public synchronized void setIsRunning(boolean flag) {
        isRunning = flag;
        if (flag) {
            this.notify();
        }
    }

    public void setPool(GenericObjectPool pool) {
        this.pool = pool;
    }

    public SendPostThread() {
        isRunning = false;
    }

    public void destroy() {
        System.out.println("destroy中");
        this.interrupt();
    }

    public synchronized void run() {
        try {
            if (!isRunning) {
                this.wait();
            } else {
                System.out.println(this.getName() + "开始处理");
                HttpPostUtil u = new HttpPostUtil("http://39.108.0.229");
                byte[] b = u.send();
                String result = new String(b);
                System.out.println("result:{}" + result.length());
                System.out.println(this.getName() + "结束处理");
                setIsRunning(false);
                pool.returnObject(this);
            }
        } catch (Exception e) {
            System.out.println("我被Interrupted了，所以此线程将被关闭");
            e.printStackTrace();
        }
    }

}
