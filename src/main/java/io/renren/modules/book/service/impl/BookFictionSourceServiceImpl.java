package io.renren.modules.book.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.book.dao.BookFictionSourceDao;
import io.renren.modules.book.entity.BookFictionSourceEntity;
import io.renren.modules.book.service.BookFictionSourceService;


@Service("bookFictionSourceService")
public class BookFictionSourceServiceImpl extends ServiceImpl<BookFictionSourceDao, BookFictionSourceEntity> implements BookFictionSourceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookFictionSourceEntity> page = this.page(
                new Query<BookFictionSourceEntity>().getPage(params),
                new QueryWrapper<BookFictionSourceEntity>()
        );

        return new PageUtils(page);
    }

}