package ru.egts.core.parser;


import ru.egts.core.bean.service.teledata.PosData;

import java.time.temporal.ChronoUnit;

public class PosDataParser implements Parser<PosData> {

    @Override
    public PosData parse(int start, byte[] data) {
        PosData.PosDataBuilder posDataBuilder = PosData.builder();
        posDataBuilder.navigationTime(
                START_DATE.plus(makeLongFromInt(start, data), ChronoUnit.SECONDS)
        );
        posDataBuilder.lat(makeLongFromInt(start + 4, data));
        posDataBuilder.lon(makeLongFromInt(start + 8, data));

        // FLG
        final byte flg = data[12];
        posDataBuilder.vld((flg & 0x01) == (byte) 0x01);
        posDataBuilder.fix(((flg >>> 1) & 0x01) == (byte) 0x01);
        posDataBuilder.cs(((flg >>> 2) & 0x01) == (byte) 0x01);
        posDataBuilder.bb(((flg >>> 3) & 0x01) == (byte) 0x01);
        posDataBuilder.mv(((flg >>> 4) & 0x01) == (byte) 0x01);
        posDataBuilder.lahs(((flg >>> 5) & 0x01) == (byte) 0x01);
        posDataBuilder.lohs(((flg >>> 6) & 0x01) == (byte) 0x01);
        posDataBuilder.alth(((flg >>> 7) & 0x01) == (byte) 0x01);

        // only eight last Bits are a speed value
        int speed = makeIntFromShort(start + 13, data);
        speed <<= 18;
        speed >>>= 18;
        posDataBuilder.speed(speed);

        posDataBuilder.direction(data[start + 15]);

        return posDataBuilder.build();
    }

}
