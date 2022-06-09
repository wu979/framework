package com.framework.cloud.common.base;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 父级分页
 *
 * @author wusiwei
 */
public class BasePage {

    @ApiModelProperty(value = "当前页码")
    private Integer current = 1;

    @ApiModelProperty(value = "每页条数")
    private Integer size = 10;

    @ApiModelProperty(value = "排序")
    private List<OrderItem> orders = OrderItem.descs("create_time");

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }
}
