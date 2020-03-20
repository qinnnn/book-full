package io.renren.modules.book.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.book.entity.BookUserFictionEntity;
import io.renren.modules.book.service.BookUserFictionService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户书籍管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@RestController
@RequestMapping("book/bookuserfiction")
public class BookUserFictionController {
    @Autowired
    private BookUserFictionService bookUserFictionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("book:bookuserfiction:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bookUserFictionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("book:bookuserfiction:info")
    public R info(@PathVariable("id") Long id){
		BookUserFictionEntity bookUserFiction = bookUserFictionService.getById(id);

        return R.ok().put("bookUserFiction", bookUserFiction);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("book:bookuserfiction:save")
    public R save(@RequestBody BookUserFictionEntity bookUserFiction){
		bookUserFictionService.save(bookUserFiction);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("book:bookuserfiction:update")
    public R update(@RequestBody BookUserFictionEntity bookUserFiction){
		bookUserFictionService.updateById(bookUserFiction);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("book:bookuserfiction:delete")
    public R delete(@RequestBody Long[] ids){
		bookUserFictionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
