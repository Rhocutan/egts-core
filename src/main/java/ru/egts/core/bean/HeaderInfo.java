package ru.egts.core.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HeaderInfo implements ParsedObject {
    private int protocolVersion;
    private int securityKeyId;
    private int headerLength;
    private int dataLength;

    private int headerEncoding;

    private int packetIdentifier;

    private PacketType packetType;

    @AllArgsConstructor
    public enum PacketType {
        RESPONSE(0),
        APPDATA(1),
        SIGNED_APPDATA(2);
        @Getter
        private final int code;
    }
}
