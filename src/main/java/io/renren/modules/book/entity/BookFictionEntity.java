package io.renren.modules.book.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 小说信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@Data
@TableName("book_fiction")
public class BookFictionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 小说id
	 */
	@TableId
	private Long fictionId;
	/**
	 * 小说名
	 */
	private String fictionName;
	/**
	 * 作者
	 */
	private String autor;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 状态 1 连载中 2 已完结
	 */
	private Integer state;
	/**
	 * 最新章节
	 */
	private String newest;
	/**
	 * 字数
	 */
	private Integer number;
	/**
	 * 简介
	 */
	private String brief;
	/**
	 * 图片地址
	 */
	private String imgUrl;
	/**
	 * 数据源id
	 */
	private Long sourceId;
	/**
	 * 源网站名
	 */
	private String sourceName;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
