package com.example.springbootdemo.test;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.security.SecureRandom;
import java.time.LocalDateTime;

public class RedisionTest {

    public static void main(String[] args) throws InterruptedException {
        SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(1000000000));
        }


    }

    public void interruptTest() {
        for (int i = 0; i < 10; i++) {
            try {
                if (i == 1) {
                    throw new InterruptedException();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(i);
        }
        System.out.println("end");
    }
//    public static void main(String[] args) {
//        // 1. Create config object
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        // 2. Create Redisson instance
//        // Sync and Async API
//        RedissonClient redisson = Redisson.create(config);
//        RedisionTest redisionTest = new RedisionTest();
//        new Thread(() -> redisionTest.process(redisson)).start();
//        new Thread(() -> redisionTest.process(redisson)).start();
//        new Thread(() -> redisionTest.process(redisson)).start();
////        redisson.
//    }


    private void process(RedissonClient redisson) {
        //获取分布式锁，只要锁的名字一样，就是同一把锁
        RLock lock = redisson.getLock("lock");
        //加锁（阻塞等待），默认过期时间是30秒
        lock.lock();
        try{

            System.out.println(LocalDateTime.now() +"加锁成功，执行后续代码。线程 ID：" + Thread.currentThread().getId());
            //如果业务执行过长，Redisson会自动给锁续期
            Thread.sleep(5000);
//            System.out.println("加锁成功，执行业务逻辑");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //解锁，如果业务执行完成，就不会继续续期，即使没有手动释放锁，在30秒过后，也会释放锁
            lock.unlock();
            System.out.println(LocalDateTime.now()+"Finally，释放锁成功。线程 ID：" + Thread.currentThread().getId());
        }
    }
}
