package cn.stormbirds.stormim.imclient.handler;

import cn.stormbirds.stormim.imclient.bean.AppMessage;
import cn.stormbirds.stormim.imclient.bean.GroupMessage;
import cn.stormbirds.stormim.imclient.event.Events;
import cn.stormbirds.stormim.imclient.event.IMEventCenter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * cn.stormbirds.stormim.imserver
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2020/1/15 17:50
 */
@Slf4j
public class GroupChatMessageHandler extends AbstractMessageHandler {

    private static final String TAG = GroupChatMessageHandler.class.getSimpleName();

    @Override
    protected void action(AppMessage message) {
        log.info(TAG, "收到群聊消息，message=" + message);

        GroupMessage msg = new GroupMessage();
        msg.setMsgId(message.getHead().getMsgId());
        msg.setMsgType(message.getHead().getMsgType());
        msg.setMsgContentType(message.getHead().getMsgContentType());
        msg.setFromId(message.getHead().getFromId());
        msg.setToId(message.getHead().getToId());
        msg.setTimestamp(message.getHead().getTimestamp());
        msg.setExtend(message.getHead().getExtend());
        msg.setContent(message.getBody());


        IMEventCenter.dispatchEvent(Events.CHAT_GROUP_MESSAGE, 0, 0, msg);
    }
}
