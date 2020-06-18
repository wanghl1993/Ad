package com.imooc.ad.sender.index;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.dump.table.AdCreativeTable;
import com.imooc.ad.dump.table.AdPlanTable;
import com.imooc.ad.handler.AdLevelDataHandler;
import com.imooc.ad.index.DataLevel;
import com.imooc.ad.mysql.constant.Constant;
import com.imooc.ad.mysql.dto.MySqlRowData;
import com.imooc.ad.sender.ISender;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("indexSender")
public class IndexSender implements ISender {


    @Override
    public void sender(MySqlRowData rowData) {

        String level = rowData.getLevel();

        if(DataLevel.LEVEL2.getLevel().equals(level)){
            //
        } else if(DataLevel.LEVEL3.getLevel().equals(level)){
            //
        } else if(DataLevel.LEVEL4.getLevel().equals(level)){
            log.error("MysqlRowData ERROR: {}", JSON.toJSONString(rowData));
        }
    }


    private void Level2RowData(MySqlRowData rowData){

        if (rowData.getTableName().equals(
                Constant.AD_PLAN_TABLE_INFO.TABLE_NAME
        )){
            List<AdPlanTable> planTables = new ArrayList<>();

            for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()) {

                AdPlanTable planTable = new AdPlanTable();

                fieldValueMap.forEach((k, v) -> {
                    switch (k){
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_ID:
                            planTable.setId(Long.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_USER_ID:
                            planTable.setUserId(Long.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_PLAN_STATUS:
                            planTable.setPlanStatus(Integer.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_START_DATE:
                            planTable.setStartDate(
                                    CommonUtils.parseStringDate(v)
                            );
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_END_DATE:
                            planTable.setEndDate(
                                    CommonUtils.parseStringDate(v)
                            );
                    }
                });

                planTables.add(planTable);
            }

            planTables.forEach(p ->
                    AdLevelDataHandler.handleLevel2(p, rowData.getOpType()));
        } else if(rowData.getTableName().equals(
                Constant.AD_CREATIVE_TABLE_INFO.TABLE_NAME
        )){
            List<AdCreativeTable> creativeTables = new ArrayList<>();


            for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()) {

                AdCreativeTable creativeTable = new AdCreativeTable();

                fieldValueMap.forEach((k, v) -> {
                    switch (k) {
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_ID:
                            creativeTable.setAdId(Long.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_TYPE:
                            creativeTable.setType(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_MATERIAL_TYPE:
                            creativeTable.setMaterialType(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_HEIGHT:
                            creativeTable.setHeight(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_WIDTH:
                            creativeTable.setWidth(Integer.valueOf(v));
                            break;

                    }
                });

                creativeTables.add(creativeTable);
            }

            creativeTables.forEach(c ->
                    AdLevelDataHandler.handleLevel2(c, rowData.getOpType()));
        }
    }

}
