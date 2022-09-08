package com.liuming.seata.stock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("storage_tbl")
public class Stock {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String commodityCode;
    private Long count;
}
