package cn.stormbirds.stormim.imclient.bean;


/**
 * Copyright (c) 小宝 @ 2019
 *
 * @ Package Name:    cn.stormbirds.stimlib.bean
 * @ Author：         stormbirds
 * @ Email：          xbaojun@gmail.com
 * @ Created At：     2019/5/13 11:18
 * @ Description：    单聊消息
 *
 */
public class SingleMessage extends ContentMessage implements Cloneable {

    @Override
    public int hashCode() {
        try {
            return this.msgId.hashCode();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof cn.stormbirds.stormim.imclient.bean.SingleMessage)) {
            return false;
        }

        return this.msgId.equals( ((cn.stormbirds.stormim.imclient.bean.SingleMessage) obj).getMsgId());
    }
}


