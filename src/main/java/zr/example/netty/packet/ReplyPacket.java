package zr.example.netty.packet;

import zr.example.netty.common.MessageTypeEnum;
import zr.example.netty.packet.base.BasePacket;

/**
 * @description:
 * @author: zhurui
 * @date: 2021/2/16
 * @time: 1:28 下午
 * Copyright (C) 2021 CASDP All rights reserved
 */
public class ReplyPacket extends BasePacket<Byte> {

    @Override
    public Byte messageType() {
        return MessageTypeEnum.Reply.getCode();
    }

}
