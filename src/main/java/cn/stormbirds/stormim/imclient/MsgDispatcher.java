package cn.stormbirds.stormim.imclient;

import cn.stormbirds.stormim.imclient.listener.OnEventListener;
import cn.stormbirds.stormim.imclient.protobuf.MessageProtobuf;

/**
 * <p>
 * {@link MsgDispatcher}
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2020/1/16 9:31
 */

public class MsgDispatcher {

    private OnEventListener mOnEventListener;

    public MsgDispatcher() {

    }

    public void setOnEventListener(OnEventListener listener) {
        this.mOnEventListener = listener;
    }

    /**
     * 接收消息，并通过OnEventListener转发消息到应用层
     * @param msg
     */
    public void receivedMsg(MessageProtobuf.Msg msg) {
        if(mOnEventListener == null) {
            return;
        }

        mOnEventListener.dispatchMsg(msg);
    }
}
