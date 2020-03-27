package io.renren.modules.book.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.reptile.resources.ShuQuGeResources;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.book.entity.BookSourceEntity;
import io.renren.modules.book.service.BookSourceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 数据源
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@RestController
@RequestMapping("book/booksource")
public class BookSourceController {
    @Autowired
    private BookSourceService bookSourceService;
    @Autowired
    private ShuQuGeResources shuQuGeResources;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("book:booksource:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bookSourceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{sourceId}")
    @RequiresPermissions("book:booksource:info")
    public R info(@PathVariable("sourceId") Long sourceId){
		BookSourceEntity bookSource = bookSourceService.getById(sourceId);

        return R.ok().put("bookSource", bookSource);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("book:booksource:save")
    public R save(@RequestBody BookSourceEntity bookSource){
		bookSourceService.save(bookSource);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("book:booksource:update")
    public R update(@RequestBody BookSourceEntity bookSource){
		bookSourceService.updateById(bookSource);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("book:booksource:delete")
    public R delete(@RequestBody Long[] sourceIds){
		bookSourceService.removeByIds(Arrays.asList(sourceIds));

        return R.ok();
    }

    /**
     * 数据源爬取小说列表
     */
    @RequestMapping("/crawl")
    @RequiresPermissions("book:booksource:save")
    public R crawl(@PathVariable("sourceId") Long sourceId){
        BookSourceEntity bookSource = bookSourceService.getById(sourceId);
        switch (bookSource.getSourceName()){
            case "书趣阁":
                shuQuGeResources.crawl(bookSource);
                break;
        }
        return R.ok();
    }

}
