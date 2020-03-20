package io.renren.modules.book.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 小说数据源
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@Data
@TableName("book_fiction_source")
public class BookFictionSourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 小说id
	 */
	@TableId
	private Long fictionId;
	/**
	 * 源id
	 */
	private Long sourceId;
	/**
	 * 最新章节
	 */
	private String newest;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
