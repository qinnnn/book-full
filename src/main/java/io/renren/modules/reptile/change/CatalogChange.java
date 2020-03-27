package io.renren.modules.reptile.change;

import lombok.Data;


/**
 * 文章目录格式
 */
@Data
public class CatalogChange {

    //标题
    private String title;
    //排序
    private Integer sort;
    //内容url
    private String url;

}
