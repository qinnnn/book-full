package io.renren.modules.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.book.entity.BookFictionSourceEntity;

import java.util.Map;

/**
 * 小说数据源
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
public interface BookFictionSourceService extends IService<BookFictionSourceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 更新小说最新章节
     * @param fictionSourceEntity
     */
    void updateByIdUpdate(BookFictionSourceEntity fictionSourceEntity);
}

