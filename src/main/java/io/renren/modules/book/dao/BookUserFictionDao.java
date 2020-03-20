package io.renren.modules.book.dao;

import io.renren.modules.book.entity.BookUserFictionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户书籍管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@Mapper
public interface BookUserFictionDao extends BaseMapper<BookUserFictionEntity> {
	
}
