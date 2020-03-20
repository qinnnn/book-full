package io.renren.modules.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.book.entity.BookUserFictionEntity;

import java.util.Map;

/**
 * 用户书籍管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
public interface BookUserFictionService extends IService<BookUserFictionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

