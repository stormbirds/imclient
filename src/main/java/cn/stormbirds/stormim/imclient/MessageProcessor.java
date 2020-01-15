package cn.stormbirds.stormim.imclient;

import cn.stormbirds.stormim.imclient.bean.AppMessage;
import cn.stormbirds.stormim.imclient.bean.BaseMessage;
import cn.stormbirds.stormim.imclient.bean.ContentMessage;
import cn.stormbirds.stormim.imclient.netty.NettyTcpClient;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * cn.stormbirds.stormim.imserver
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2020/1/15 16:08
 */
@Slf4j
public class MessageProcessor implements IMessageProcessor {

    private static final String TAG = MessageProcessor.class.getSimpleName();

    private MessageProcessor() {

    }

    private static class MessageProcessorInstance {
        private static final IMessageProcessor INSTANCE = new MessageProcessor();
    }

    public static IMessageProcessor getInstance() {
        return MessageProcessorInstance.INSTANCE;
    }

    /**
     * 接收消息
     * @param message
     */
    @Override
    public void receiveMsg(final AppMessage message) {
        NettyTcpClient.getInstance().getLoopGroup().execMessageTask(new Runnable() {

            @Override
            public void run() {
                try {
                    IMessageHandler messageHandler = MessageHandlerFactory.getHandlerByMsgType(message.getHead().getMsgType());
                    if (messageHandler != null) {
                        messageHandler.execute(message);
                    } else {
                        log.error(TAG, "未找到消息处理handler，msgType=" + message.getHead().getMsgType());
                    }
                } catch (Exception e) {
                    log.error(TAG, "消息处理出错，reason=" + e.getMessage());
                }
            }
        });
    }

    /**
     * 发送消息
     *
     * @param message
     */
    @Override
    public void sendMsg(final AppMessage message) {

        NettyTcpClient.getInstance().getLoopGroup().execMessageTask(new Runnable() {

            @Override
            public void run() {
                boolean isActive = !NettyTcpClient.getInstance().isClosed();
                if (isActive) {
                    NettyTcpClient.getInstance().sendMsg(MessageBuilder.getProtoBufMessageBuilderByAppMessage(message).build());
                } else {
                    log.error(TAG, "发送消息失败");
                }
            }
        });
    }

    /**
     * 发送消息
     *
     * @param message
     */
    @Override
    public void sendMsg(ContentMessage message) {
        this.sendMsg(MessageBuilder.buildAppMessage(message));
    }

    /**
     * 发送消息
     *
     * @param message
     */
    @Override
    public void sendMsg(BaseMessage message) {
        this.sendMsg(MessageBuilder.buildAppMessage(message));
    }
}

