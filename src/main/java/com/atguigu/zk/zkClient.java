package com.atguigu.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;


public class zkClient{
    //注意：都好左右不能有空格
    private String connectString = "47.122.44.7:2181,47.122.44.34:2181,47.122.37.164:2181";

    private  ZooKeeper zkClient;
    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(connectString,40000,new Watcher(){
            @Override
            public void process(WatchedEvent watchedEvent){
             /*   List<String> children = null;
                try{
                    children = zkClient.getChildren("/", true);
                    for (String child: children){
                        System.out.println(child);
                    }

                    System.out.println("--------------------------");
                } catch(KeeperException e){
                    e.printStackTrace();
                } catch (InterruptedException e){

                }*/
            }
        });
    }
    @Test
    public void create() throws InterruptedException, KeeperException {
        String nodeCreate = zkClient.create("/atguigu","ss.avi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void getChildren() throws InterruptedException, KeeperException {
        List<String> children = zkClient.getChildren("/",true);

        for (String child : children){
            System.out.println(child);
        }
        // 延时
        Thread.sleep(Long.MAX_VALUE);
    }
    @Test
    public void exist() throws InterruptedException, KeeperException {
        Stat stat = zkClient.exists("/atguigu", false);

        System.out.println(stat == null?"not exist": "exist");
    }
}
