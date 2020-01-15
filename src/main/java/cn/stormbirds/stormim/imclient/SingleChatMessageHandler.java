package cn.stormbirds.stormim.imclient;

import cn.stormbirds.stormim.imclient.bean.AppMessage;
import cn.stormbirds.stormim.imclient.event.Events;
import cn.stormbirds.stormim.imclient.event.IMEventCenter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * cn.stormbirds.stormim.imserver
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2020/1/15 17:45
 */
@Slf4j
public class SingleChatMessageHandler extends AbstractMessageHandler {

    private static final String TAG = SingleChatMessageHandler.class.getSimpleName();

    @Override
    protected void action(AppMessage message) {
        log.info(TAG, "收到单聊消息，message=" + message);

        SingleMessage msg = new SingleMessage();
        msg.setMsgId(message.getHead().getMsgId());
        msg.setMsgType(message.getHead().getMsgType());
        msg.setMsgContentType(message.getHead().getMsgContentType());
        msg.setFromId(message.getHead().getFromId());
        msg.setToId(message.getHead().getToId());
        msg.setTimestamp(message.getHead().getTimestamp());
        msg.setExtend(message.getHead().getExtend());
        msg.setContent(message.getBody());


        IMEventCenter.dispatchEvent(Events.CHAT_SINGLE_MESSAGE, 0, 0, msg);
    }
}
