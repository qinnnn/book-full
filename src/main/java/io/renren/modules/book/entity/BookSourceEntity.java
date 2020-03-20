package io.renren.modules.book.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 数据源
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@Data
@TableName("book_source")
public class BookSourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 源地址id
	 */
	@TableId
	private Long sourceId;
	/**
	 * 网站名
	 */
	private String sourceName;
	/**
	 * 状态 1可使用 2已失效
	 */
	private Integer state;
	/**
	 * 类型 1小说 2漫画
	 */
	private Integer type;
	/**
	 * 网站地址
	 */
	private String url;
	/**
	 * 配置值
	 */
	private String dateValue;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
