package cn.stormbirds.stormim.imclient.handler;

import cn.stormbirds.stormim.imclient.bean.AppMessage;

/**
 * <p>
 * cn.stormbirds.stormim.imserver
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2020/1/15 17:43
 */
public interface IMessageHandler {

    void execute(AppMessage message);
}
