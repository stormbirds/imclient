package cn.stormbirds.stormim.imclient.factory;


import cn.stormbirds.stormim.imclient.listener.IMClientInterface;
import cn.stormbirds.stormim.imclient.netty.NettyTcpClient;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       IMSClientFactory.java</p>
 * <p>@PackageName:     com.freddy.im</p>
 * <b>
 * <p>@Description:     ims实例工厂方法</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/03/31 20:54</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class IMSClientFactory {

    public static IMClientInterface getIMSClient() {
        return NettyTcpClient.getInstance();
    }
}
