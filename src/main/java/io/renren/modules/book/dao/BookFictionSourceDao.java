package io.renren.modules.book.dao;

import io.renren.modules.book.entity.BookFictionSourceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 小说数据源
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@Mapper
public interface BookFictionSourceDao extends BaseMapper<BookFictionSourceEntity> {

    /**
     * 更新小说最新章节
     * @param fictionSourceEntity
     */
	void updateByIdUpdate(BookFictionSourceEntity fictionSourceEntity);
}
