package com.sttx.bookmanager.util.threadpool;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sttx.bookmanager.util.http.HttpPostUtil;
import com.sttx.bookmanager.util.http.UspHttpUtils;

public class SendPostThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(SendPostThread.class);

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
        log.info(">>>>>>>>>>>>>>>>destroy中");
        this.interrupt();
    }

    public synchronized void run() {
        try {
            if (!isRunning) {
                this.wait();
            } else {
                log.info(">>>>>>>>>>>>>>>>" + this.getName() + "开始处理");
//                HttpPostUtil u = new HttpPostUtil("http://www.shopbop.ink/bookmanager/indexHome");
//                byte[] b = u.send();
//                String result = new String(b);
                String result = UspHttpUtils.doGetStringType("http://58.87.121.73:9091/info");
                log.info(">>>>>>>>>>>>>>>>result:{}" +result);
                log.info(">>>>>>>>>>>>>>>>result:{}" + result.length());
                log.info(">>>>>>>>>>>>>>>>" + this.getName() + "结束处理");
                setIsRunning(false);
                pool.returnObject(this);
            }
        } catch (Exception e) {
            log.info(">>>>>>>>>>>>>>>>我被Interrupted了，所以此线程将被关闭");
            e.printStackTrace();
        }
    }

}
