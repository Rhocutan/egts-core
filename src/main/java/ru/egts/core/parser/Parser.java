package ru.egts.core.parser;


import ru.egts.core.bean.ParsedObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Parser<T extends ParsedObject> {

    LocalDateTime START_DATE = LocalDateTime.parse("01.01.2010 UTC 00:00:00",
            DateTimeFormatter.ofPattern("dd.MM.yyyy z HH:mm:ss"));

    T parse(int start, byte[] data);

    default long makeLongFromInt(int start, byte[] data) {
        byte[] lat = new byte[8];
        System.arraycopy(data, start, lat, 0, 4);
        return ByteBuffer.wrap(lat).order(ByteOrder.LITTLE_ENDIAN).getLong();
    }

    default int makeIntFromShort(int start, byte[] data) {
        byte[] lat = new byte[4];
        System.arraycopy(data, start, lat, 0, 2);
        return ByteBuffer.wrap(lat).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    default int unsignedByte(byte b) {
        return b & 0xFF;
    }

}
