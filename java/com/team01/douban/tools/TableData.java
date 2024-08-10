package com.team01.douban.tools;

import lombok.Data;

import java.util.List;

@Data
public class TableData {
    private int code;
    private String msg;
    private int count;
    private List<? extends Object> data;
}
