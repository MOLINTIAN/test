package com.stu.zk;

import org.apache.zookeeper.*;

public class ZkDemo implements Watcher {
    public static void main(String[] args) throws Exception {
        // 可以直接new出,测试可以连接上,可以看到CONNECTING22:45:57.544 [main-SendThread(127.0.0.1:2181)] INFO org.apache.zookeeper.ClientCnxn - Opening socket connection to server 127.0.0.1/127.0.0.1:2181.
        // ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",3000*10, null);
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",3000*10, new ZkDemo());
        // 查看连接状态
        ZooKeeper.States state = zooKeeper.getState();
        System.out.print(state);
        // 创建文件前存在，删除
        // zooKeeper.delete("/data",-1);
        zooKeeper.exists("/data",new ZkDemo());
        zooKeeper.create("/data","bywind".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Thread.sleep(2000);
    }

    // 回调机制，才能实时的回调和心跳机制。
   @Override
    public void process(WatchedEvent event) {
        // 事件
        System.out.println("watcher:"+event.getState());
        if (event.getType().equals(Event.EventType.NodeCreated)){
            System.out.println("watcher:node created");
        }
    }
}
