package cn.stormbirds.stormim.imclient;

import cn.stormbirds.stormim.imclient.bean.AppMessage;
import cn.stormbirds.stormim.imclient.bean.BaseMessage;
import cn.stormbirds.stormim.imclient.bean.ContentMessage;

/**
 * <p>
 * cn.stormbirds.stormim.imserver
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2020/1/15 16:09
 */
public interface IMessageProcessor {

    void receiveMsg(AppMessage message);
    void sendMsg(AppMessage message);
    void sendMsg(ContentMessage message);
    void sendMsg(BaseMessage message);
}
