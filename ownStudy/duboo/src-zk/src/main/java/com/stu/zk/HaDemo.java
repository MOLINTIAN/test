package com.stu.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * zk实现高可用的zk,多次启动，第一次启动是master,后来的都是salve
 */
public class HaDemo implements Watcher {
    private String role = "master";
    private final static String HA_PATH = "/HA";
    private ZooKeeper zooKeeper;

    private ZooKeeper getZooKeeper() {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181",3000*10, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }

    // 利用zk模拟实现一个HA高可用的程序
    public static void main(String[] args) throws Exception {
        HaDemo haDemo = new HaDemo();
        ZooKeeper zooKeeper = haDemo.getZooKeeper();
        Stat stat = zooKeeper.exists(HA_PATH,haDemo);
        // 主节点存在？
        if (stat == null) {
            zooKeeper.create(HA_PATH,"1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } else {
            haDemo.role = "salve";
            System.out.println("I am " + haDemo.role);
        }
    }

    // 所有创建和宕机这个过程都可以在下面的方法监听到
    @Override
    public void process(WatchedEvent event) {
        // 是否是创建结点的对象，命为role
        if (event.getType().equals(Event.EventType.NodeCreated)) {
            System.out.println("I am " + role);
        }
        if (event.getType().equals(Event.EventType.NodeDeleted)) {
            // master 宕机了
            System.out.println("master is down");
            // 新建master
            this.role = "master";
            try{
                zooKeeper.create(HA_PATH,"1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                System.out.println("I am " + role);
            }  catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
