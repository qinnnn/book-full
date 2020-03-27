package io.renren.modules.book.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@Data
@TableName("book_user")
public class BookUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long userId;
	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 开放id
	 */
	private String openid;
	/**
	 * 类型 1手机号登录用户 2qq 3微信
	 */
	private Integer type;
	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;
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
