package com.framework.cloud.common.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页VO
 *
 * @author wusiwei
 */
@Data
@NoArgsConstructor
public class PageVO<T> {
    private static final long serialVersionUID = -7620272943757103136L;

    @ApiModelProperty(value = "数据")
    private List<T> records;

    @ApiModelProperty(value = "当前页码")
    private Long num;

    @ApiModelProperty(value = "每页条数")
    private Long size;

    @ApiModelProperty(value = "总条数")
    private Long total;

    @ApiModelProperty(value = "总页数")
    private Long pages;

    @ApiModelProperty(value = "是否第一页")
    private Boolean isFirstPage;

    @ApiModelProperty(value = "是否最后一页")
    private Boolean isLastPage;

    public PageVO(Long num, Long size) {
        this.num = num;
        this.size = size;
        this.total = 0L;
        this.pages = (total + size - 1) / size;
        this.isFirstPage = num == 1;
        this.isLastPage = num.equals(this.pages);
        this.records = Lists.newArrayList();
    }

    public PageVO(Long num, Long size, Long total, List<T> records) {
        this.num = num;
        this.size = size;
        this.total = total;
        this.pages = (total + size - 1) / size;
        this.isFirstPage = num == 1;
        this.isLastPage = num.equals(this.pages);
        this.records = records;
    }

    public static <T> PageVO<T> page(IPage<T> page) {
        PageVO<T> pageVO = new PageVO<T>();
        pageVO.setRecords(page.getRecords());
        pageVO.setNum(page.getCurrent());
        pageVO.setSize(page.getSize());
        pageVO.setTotal(page.getTotal());
        pageVO.setPages(page.getPages());
        pageVO.setIsFirstPage(pageVO.getNum() == 1);
        pageVO.setIsLastPage(pageVO.getNum().equals(pageVO.getPages()));
        return pageVO;
    }
}
