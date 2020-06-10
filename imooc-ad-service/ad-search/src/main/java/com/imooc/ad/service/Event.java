package com.imooc.ad.service;

import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.EventHeader;

import java.io.Serializable;

public class Event implements Serializable {

    //Binlog中的俩个最核心的元素: header 和 data
    private EventHeader header;

    private EventData data;

    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Event");
        sb.append("{header=").append(header);
        sb.append(",data=").append(data);
        sb.append("}");
        return sb.toString();
    }
}
