package cn.stormbirds.stormim.imclient.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * {@link ExecutorServiceFactory}
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2020/1/15 15:24
 */
public class ExecutorServiceFactory {

    private ExecutorService bossPool;// 管理线程组，负责重连
    private ExecutorService workPool;// 工作线程组，负责心跳
    private ExecutorService messagePool;

    /**
     * 初始化boss线程池
     */
    public synchronized void initBossLoopGroup() {
        initBossLoopGroup(1);
    }

    /**
     * 初始化boss线程池
     * 重载
     *
     * @param size 线程池大小
     */
    public synchronized void initBossLoopGroup(int size) {
        destroyBossLoopGroup();
        bossPool = Executors.newFixedThreadPool(size);
    }

    /**
     * 初始化work线程池
     */
    public synchronized void initWorkLoopGroup() {
        initWorkLoopGroup(1);
    }

    /**
     * 初始化work线程池
     * 重载
     *
     * @param size 线程池大小
     */
    public synchronized void initWorkLoopGroup(int size) {
        destroyWorkLoopGroup();
        workPool = Executors.newFixedThreadPool(size);
    }

    /**
     * 初始化work线程池
     */
    public synchronized void initMessageLoopGroup() {
        initWorkLoopGroup(5);
    }

    /**
     * 初始化work线程池
     * 重载
     *
     * @param size 线程池大小
     */
    public synchronized void initMessageLoopGroup(int size) {
        destroyMessageLoopGroup();
        messagePool = Executors.newFixedThreadPool(size);
    }


    /**
     * 执行boss任务
     *
     * @param r
     */
    public void execBossTask(Runnable r) {
        if (bossPool == null) {
            initBossLoopGroup();
        }
        bossPool.execute(r);
    }

    /**
     * 执行work任务
     *
     * @param r
     */
    public void execWorkTask(Runnable r) {
        if (workPool == null) {
            initWorkLoopGroup();
        }
        workPool.execute(r);
    }

    /**
     * 执行message任务
     *
     * @param r
     */
    public void execMessageTask(Runnable r) {
        if (messagePool == null) {
            initMessageLoopGroup();
        }
        messagePool.execute(r);
    }

    /**
     * 释放boss线程池
     */
    public synchronized void destroyBossLoopGroup() {
        if (bossPool != null) {
            try {
                bossPool.shutdownNow();
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                bossPool = null;
            }
        }
    }

    /**
     * 释放work线程池
     */
    public synchronized void destroyWorkLoopGroup() {
        if (workPool != null) {
            try {
                workPool.shutdownNow();
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                workPool = null;
            }
        }
    }

    /**
     * 释放Message线程池
     */
    private void destroyMessageLoopGroup() {
        if (messagePool != null) {
            try {
                messagePool.shutdownNow();
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                messagePool = null;
            }
        }
    }
    /**
     * 释放所有线程池
     */
    public synchronized void destroy() {
        destroyBossLoopGroup();
        destroyWorkLoopGroup();
        destroyMessageLoopGroup();
    }
}
