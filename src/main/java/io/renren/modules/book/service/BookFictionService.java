package io.renren.modules.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.book.entity.BookFictionEntity;

import java.util.Map;

/**
 * 小说信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
public interface BookFictionService extends IService<BookFictionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

