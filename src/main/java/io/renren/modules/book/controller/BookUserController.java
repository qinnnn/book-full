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

import io.renren.modules.book.entity.BookUserEntity;
import io.renren.modules.book.service.BookUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-20 15:48:09
 */
@RestController
@RequestMapping("book/bookuser")
public class BookUserController {
    @Autowired
    private BookUserService bookUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("book:bookuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bookUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("book:bookuser:info")
    public R info(@PathVariable("userId") Long userId){
		BookUserEntity bookUser = bookUserService.getById(userId);

        return R.ok().put("bookUser", bookUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("book:bookuser:save")
    public R save(@RequestBody BookUserEntity bookUser){
		bookUserService.save(bookUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("book:bookuser:update")
    public R update(@RequestBody BookUserEntity bookUser){
		bookUserService.updateById(bookUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("book:bookuser:delete")
    public R delete(@RequestBody Long[] userIds){
		bookUserService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
