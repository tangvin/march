package com.example.concurrent.ch1.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @className:
 * @description: 类说明：连接池的实现
 *              类说明：等待超时模式实现一个数据库连接池
 * @author: Bruce_T
 * @data: 2022/03/06   11:28
 * @version: 1.0
 * @modified:
 */
public class DBPool {


    //容器，存放连接
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    //构造方法，限制的了DBPool中连接数的大小
    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    //释放连接，通知正在等待连接的线程
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool){
                pool.addLast(connection);
                //通知其他的等待连接的线程
                pool.notifyAll();
            }
        }
    }

    //获取连接
    // 在mills内无法获取到连接，将会返回null
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            //永不超时
            if(mills < 0){
                while(pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                //超时时刻
               long future = System.currentTimeMillis() + mills ;
               //等待时长
                long remaining = mills;
                while(pool.isEmpty() && remaining > 0){
                    pool.wait(remaining);
                    //每被唤醒一次，必须要重新计算等待时长，否则体现不了在某一个时刻拿到连接返回空值的效果
                    remaining = future - System.currentTimeMillis();
                }
                Connection connection =  null;
                if(!pool.isEmpty()){
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }
}
