package ru.egts.core.bean.service.teledata;

import lombok.Builder;
import lombok.Data;
import ru.egts.core.bean.SubRecordData;

import java.time.LocalDateTime;

@Builder
@Data
public class PosData implements SubRecordData {
    private LocalDateTime navigationTime;
    private long lat;
    private long lon;

    private boolean alth;
    private boolean lohs;
    private boolean lahs;
    private boolean mv;
    private boolean bb;
    private boolean cs;
    private boolean fix;
    private boolean vld;

    private int speed;
    private byte direction;
    private byte[] odometr;
    private byte digitalInputs;
    private byte source;
    private byte[] altitude;
    private short sourceData;
}
