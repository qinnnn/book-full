package io.renren.modules.book.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.book.dao.BookUserDao;
import io.renren.modules.book.entity.BookUserEntity;
import io.renren.modules.book.service.BookUserService;


@Service("bookUserService")
public class BookUserServiceImpl extends ServiceImpl<BookUserDao, BookUserEntity> implements BookUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookUserEntity> page = this.page(
                new Query<BookUserEntity>().getPage(params),
                new QueryWrapper<BookUserEntity>()
        );

        return new PageUtils(page);
    }

}