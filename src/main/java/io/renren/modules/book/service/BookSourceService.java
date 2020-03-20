package io.renren.modules.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.book.entity.BookSourceEntity;

import java.util.Map;

/**
 * 数据源
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
public interface BookSourceService extends IService<BookSourceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

