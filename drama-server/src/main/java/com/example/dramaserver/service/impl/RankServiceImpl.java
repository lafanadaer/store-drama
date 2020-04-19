package com.example.dramaserver.service.impl;

import com.example.dramaserver.dao.RankMapper;
import com.example.dramaserver.domain.Rank;
import com.example.dramaserver.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public int rankOfDramaId(Long dramaId) {
        return rankMapper.selectScoreSum(dramaId) / rankMapper.selectRankNum(dramaId);
    }

    @Override
    public boolean addRank(Rank rank) {

        return rankMapper.insertSelective(rank) > 0 ? true:false;
    }
}
