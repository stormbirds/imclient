package cn.stormbirds.stormim.imclient;

import cn.stormbirds.stormim.imclient.listener.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * cn.stormbirds.stormim.imserver
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2020/1/15 17:43
 */
public class MessageHandlerFactory {

    private MessageHandlerFactory() {

    }

    private static final List<IMessageHandler> HANDLERS = new ArrayList<>();

    static {
        /** 单聊消息处理handler */
        HANDLERS.add(MessageType.SINGLE_CHAT.getMsgType(), new SingleChatMessageHandler());
        /** 群聊消息处理handler */
        HANDLERS.add(MessageType.GROUP_CHAT.getMsgType(), new GroupChatMessageHandler());
        /** 服务端返回的消息发送状态报告处理handler */
        HANDLERS.add(MessageType.SERVER_MSG_SENT_STATUS_REPORT.getMsgType(), new ServerReportMessageHandler());
    }

    /**
     * 根据消息类型获取对应的处理handler
     *
     * @param msgType
     * @return
     */
    public static IMessageHandler getHandlerByMsgType(int msgType) {
        return HANDLERS.get(msgType);
    }
}