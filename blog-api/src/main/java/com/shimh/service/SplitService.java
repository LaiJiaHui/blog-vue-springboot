package com.shimh.service;

import com.shimh.entity.PageEntity;
import com.shimh.entity.Split;

import java.util.List;

/**
 * @author linguochao
 * @Description TODO
 * @Date 2020/1/19 14:30
 */
public interface SplitService {

    Split saveSplit(Split split);

    void thumbsSplit(Integer id);

    List<Split> getSplitList(PageEntity pageEntity);

    Split replySplit(Split split);
}
