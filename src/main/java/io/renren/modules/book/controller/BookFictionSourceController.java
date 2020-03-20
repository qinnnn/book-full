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

import io.renren.modules.book.entity.BookFictionSourceEntity;
import io.renren.modules.book.service.BookFictionSourceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 小说数据源
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@RestController
@RequestMapping("book/bookfictionsource")
public class BookFictionSourceController {
    @Autowired
    private BookFictionSourceService bookFictionSourceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("book:bookfictionsource:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bookFictionSourceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fictionId}")
    @RequiresPermissions("book:bookfictionsource:info")
    public R info(@PathVariable("fictionId") Long fictionId){
		BookFictionSourceEntity bookFictionSource = bookFictionSourceService.getById(fictionId);

        return R.ok().put("bookFictionSource", bookFictionSource);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("book:bookfictionsource:save")
    public R save(@RequestBody BookFictionSourceEntity bookFictionSource){
		bookFictionSourceService.save(bookFictionSource);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("book:bookfictionsource:update")
    public R update(@RequestBody BookFictionSourceEntity bookFictionSource){
		bookFictionSourceService.updateById(bookFictionSource);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("book:bookfictionsource:delete")
    public R delete(@RequestBody Long[] fictionIds){
		bookFictionSourceService.removeByIds(Arrays.asList(fictionIds));

        return R.ok();
    }

}
