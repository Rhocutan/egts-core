package ru.egts.core.parser;


import ru.egts.core.bean.service.teledata.Tds;

public class TdsParser implements Parser<Tds> {
    @Override
    public Tds parse(int start, byte[] data) {
        return Tds.builder()
                .build();
    }
}
