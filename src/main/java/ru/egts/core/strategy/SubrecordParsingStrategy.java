package ru.egts.core.strategy;


import ru.egts.core.bean.SubRecordData;
import ru.egts.core.parser.*;

public class SubrecordParsingStrategy {
    private final Parser<? extends SubRecordData> parser;

    public SubrecordParsingStrategy(int type) {
        switch (type) {
            case 16: this.parser = new PosDataParser(); break;
            case 17: this.parser = new ExtPosDataParser(); break;
            case 18: this.parser = new AdSensorsDataParser(); break;
            case 62: this.parser = new TrackDataParser(); break;
            default: this.parser = null;
        }
    }

    public SubRecordData parse(int start, byte[] data) {
        return this.parser != null ? this.parser.parse(start, data) : null;
    }
}
