package com.imooc.ad.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonTable {

    private String tableName;
    private Integer level;

    private List<Colunm> insert;
    private List<Colunm> update;
    private List<Colunm> delete;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Colunm {

        private String column;
    }
}
