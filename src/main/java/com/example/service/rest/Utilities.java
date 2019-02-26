package com.example.service.rest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public  class Utilities {

    public static Timestamp getCurrentUTCZoneTime() {
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime gmt = zdt.withZoneSameInstant(ZoneId.of("GMT"));
        return Timestamp.valueOf(gmt.toLocalDateTime());
    }
}
