package io.renren.modules.book.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.book.dao.BookFictionDao;
import io.renren.modules.book.entity.BookFictionEntity;
import io.renren.modules.book.service.BookFictionService;


@Service("bookFictionService")
public class BookFictionServiceImpl extends ServiceImpl<BookFictionDao, BookFictionEntity> implements BookFictionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookFictionEntity> page = this.page(
                new Query<BookFictionEntity>().getPage(params),
                new QueryWrapper<BookFictionEntity>()
        );

        return new PageUtils(page);
    }

}