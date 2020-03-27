package io.renren.modules.book.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户书籍管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@Data
@TableName("book_user_fiction")
public class BookUserFictionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 书id
	 */
	private Long fictionId;
	/**
	 * 类型 1小说 2漫画
	 */
	private Integer type;
	/**
	 * 最新阅读时间
	 */
	private Date useTime;
	/**
	 * 当前阅读的章节
	 */
	private Integer useEst;
	/**
	 * 当前章节的页数
	 */
	private Integer usePage;
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
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	@TableLogic
	private Integer delFlag;

}
