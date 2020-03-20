package io.renren.modules.book.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.book.dao.BookUserFictionDao;
import io.renren.modules.book.entity.BookUserFictionEntity;
import io.renren.modules.book.service.BookUserFictionService;


@Service("bookUserFictionService")
public class BookUserFictionServiceImpl extends ServiceImpl<BookUserFictionDao, BookUserFictionEntity> implements BookUserFictionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookUserFictionEntity> page = this.page(
                new Query<BookUserFictionEntity>().getPage(params),
                new QueryWrapper<BookUserFictionEntity>()
        );

        return new PageUtils(page);
    }

}