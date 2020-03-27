package io.renren.modules.reptile.change;

import lombok.Data;


/**
 * 资源DateValue
 */
@Data
public class SourceChange {

    //资源网址
    private String url;
    //资源搜索地址
    private String search;
    //搜索key
    private String searchKey;
    //搜索请求方式
    private String searchType;

}
