package com.shimh.service.impl;

import com.shimh.common.util.RedisClient;
import com.shimh.common.util.StringUtils;
import com.shimh.common.util.UserUtils;
import com.shimh.entity.PageEntity;
import com.shimh.entity.Split;
import com.shimh.entity.User;
import com.shimh.repository.SplitRepository;
import com.shimh.service.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author linguochao
 * @Description TODO
 * @Date 2020/1/19 14:31
 */
@Service
public class SplitServiceImpl implements SplitService {

    @Autowired
    private SplitRepository splitRepository;

    @Autowired
    private RedisClient redisClient;


    @Override
    public Split saveSplit(Split split) {
        User user = UserUtils.getCurrentUser();
        split.setAuthorId(user.getId());
        split.setAuthorName(user.getNickname());
        split.setCreateDate(new Date());
        split.setThumbup(0);
        if (split.getParentId()==null){
            split.setParentId(0);
        }
        Split save = splitRepository.save(split);
        return save;
    }

    @Override
    public boolean thumbsSplit(Integer id) {
        Long userId = UserUtils.getCurrentUser().getId();
        String s = redisClient.get("thumbs" + userId + id);
        boolean result=false;
        if (StringUtils.isEmpty(s)) {
            splitRepository.thumbsSplit(id);
            redisClient.setex("thumbs" + userId + id, 60 * 60 * 24, "1");
            result=true;
        }else {
            splitRepository.delThumbsSplit(id);
            redisClient.del("thumbs" + userId + id);
        }
        return result;
    }

    @Override
    public List<Split> getSplitList(PageEntity pageEntity) {

        List<Split> splitPage = splitRepository.getSplitPage(pageEntity.getPageNum() * pageEntity.getPageSize(), pageEntity.getPageSize());
        for (Split split : splitPage) {
            List<Split> childs = splitRepository.findByParentId(split.getId());
            split.setChildrens(childs);
        }
        return splitPage;
    }

    @Override
    public Split replySplit(Split split) {
        saveSplit(split);
        Split newSplit = splitRepository.findOne(split.getParentId());
        List<Split> childs = splitRepository.findByParentId(split.getParentId());
        newSplit.setChildrens(childs);
        return newSplit;
    }

}
