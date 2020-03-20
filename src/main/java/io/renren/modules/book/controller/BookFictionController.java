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

import io.renren.modules.book.entity.BookFictionEntity;
import io.renren.modules.book.service.BookFictionService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 小说信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@RestController
@RequestMapping("book/bookfiction")
public class BookFictionController {
    @Autowired
    private BookFictionService bookFictionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("book:bookfiction:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bookFictionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fictionId}")
    @RequiresPermissions("book:bookfiction:info")
    public R info(@PathVariable("fictionId") Long fictionId){
		BookFictionEntity bookFiction = bookFictionService.getById(fictionId);

        return R.ok().put("bookFiction", bookFiction);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("book:bookfiction:save")
    public R save(@RequestBody BookFictionEntity bookFiction){
		bookFictionService.save(bookFiction);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("book:bookfiction:update")
    public R update(@RequestBody BookFictionEntity bookFiction){
		bookFictionService.updateById(bookFiction);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("book:bookfiction:delete")
    public R delete(@RequestBody Long[] fictionIds){
		bookFictionService.removeByIds(Arrays.asList(fictionIds));

        return R.ok();
    }

}
