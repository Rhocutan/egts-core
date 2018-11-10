package ru.egts.core.parser;


import ru.egts.core.bean.service.teledata.TrackData;

public class TrackDataParser implements Parser<TrackData> {
    @Override
    public TrackData parse(int start, byte[] data) {
        return TrackData.builder()
                .structureAmount(unsignedByte(data[start]))
                .absoluteTime(makeLongFromInt(start + 1, data))
                .build();
    }
}
