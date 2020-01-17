package cn.stormbirds.stormim.imclient.handler;

import cn.stormbirds.stormim.imclient.bean.AppMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * cn.stormbirds.stormim.imserver
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2020/1/15 17:51
 */
@Slf4j
public class ServerReportMessageHandler extends AbstractMessageHandler {

    private static final String TAG = ServerReportMessageHandler.class.getSimpleName();

    @Override
    protected void action(AppMessage message) {
        log.info(TAG, "收到消息状态报告，message=" + message);
    }
}
