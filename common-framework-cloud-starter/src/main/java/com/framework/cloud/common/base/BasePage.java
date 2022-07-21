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
    private Long current = 1L;

    @ApiModelProperty(value = "每页条数")
    private Long size = 10L;

    @ApiModelProperty(value = "排序")
    private List<OrderItem> orders = OrderItem.descs("create_time");

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }
}
