package com.sttx.bookmanager.post;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;

import com.sttx.bookmanager.util.threadpool.MyPoolableObjectFactory;
import com.sttx.bookmanager.util.threadpool.SendPostThread;
import com.sttx.ddp.logger.DdpLoggerFactory;

public class TestThreadPool {
    private final static Logger log = DdpLoggerFactory.getLogger(TestThreadPool.class);

    public static void main(String[] args) {
        MyPoolableObjectFactory factory = new MyPoolableObjectFactory();
        GenericObjectPool pool = new GenericObjectPool(factory);
        pool.setMaxActive(50000); // 能从池中借出的对象的最大数目
        pool.setMaxIdle(100); // 池中可以空闲对象的最大数目
        pool.setMaxWait(100); // 对象池空时调用borrowObject方法，最多等待多少毫秒
        pool.setTimeBetweenEvictionRunsMillis(600000);// 间隔每过多少毫秒进行一次后台对象清理的行动
        pool.setNumTestsPerEvictionRun(-1);// －1表示清理时检查所有线程
        pool.setMinEvictableIdleTimeMillis(3000);// 设定在进行后台对象清理时，休眠时间超过了3000毫秒的对象为过期
        for (int i = 0; i < 2000; i++) {
            try {
                SendPostThread simpleThread = (SendPostThread) pool.borrowObject();
                simpleThread.setPool(pool);
                simpleThread.setIsRunning(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}