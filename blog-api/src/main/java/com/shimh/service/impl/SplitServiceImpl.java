package com.shimh.service.impl;

import com.shimh.common.util.StringUtils;
import com.shimh.common.util.UserUtils;
import com.shimh.entity.PageEntity;
import com.shimh.entity.Split;
import com.shimh.entity.User;
import com.shimh.repository.SplitRepository;
import com.shimh.service.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void thumbsSplit(Integer id) {
        splitRepository.thumbsSplit(id);
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
