package com.shimh.controller;

import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.result.Result;
import com.shimh.entity.Comment;
import com.shimh.entity.PageEntity;
import com.shimh.entity.Split;
import com.shimh.service.SplitService;
import com.shimh.vo.PageVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author linguochao
 * @Description 吐槽微服务
 * @Date 2020/1/19 10:23
 */
@RestController
@RequestMapping(value = "/api/split")
public class SplitController {

    @Autowired
    private SplitService splitService;

    @PostMapping("/create")
    @RequiresAuthentication
    @LogAnnotation(module = "吐槽", operation = "添加吐槽")
    public Result saveSplit(@Validated @RequestBody Split split) {
        splitService.saveSplit(split);
        return Result.success();
    }
    @PostMapping("/reply")
    @RequiresAuthentication
    @LogAnnotation(module = "吐槽", operation = "回复吐槽")
    public Result replySplit(@Validated @RequestBody Split split) {

        Split save = splitService.replySplit(split);
        Result r = Result.success();
        r.simple().put("split", save);
        return r;
    }

    @PostMapping("/thumbs/{id}")
    @RequiresAuthentication
    @LogAnnotation(module = "吐槽", operation = "点赞吐槽")
    public Result thumbsSplit(@PathVariable("id") Integer id) {
        splitService.thumbsSplit(id);
        Result r = Result.success();
        return Result.success();
    }

    @PostMapping("/list")
    @LogAnnotation(module = "吐槽", operation = "查询吐槽列表")
    public Result getSplit(@Validated @RequestBody PageEntity pageEntity) {
        List<Split> splits = splitService.getSplitList(pageEntity);
        Result r = Result.success();
        r.simple().put("splits", splits);
        return r;
    }


}
