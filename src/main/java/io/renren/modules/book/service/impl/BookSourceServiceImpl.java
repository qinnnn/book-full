package io.renren.modules.book.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.book.dao.BookSourceDao;
import io.renren.modules.book.entity.BookSourceEntity;
import io.renren.modules.book.service.BookSourceService;


@Service("bookSourceService")
public class BookSourceServiceImpl extends ServiceImpl<BookSourceDao, BookSourceEntity> implements BookSourceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookSourceEntity> page = this.page(
                new Query<BookSourceEntity>().getPage(params),
                new QueryWrapper<BookSourceEntity>()
        );

        return new PageUtils(page);
    }

}