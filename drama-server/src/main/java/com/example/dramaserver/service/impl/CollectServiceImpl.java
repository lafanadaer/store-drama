package com.example.dramaserver.service.impl;

import com.example.dramaserver.dao.CollectMapper;
import com.example.dramaserver.domain.Collect;
import com.example.dramaserver.service.CollectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean addCollection(Collect collect) {
        return collectMapper.insertSelective(collect) > 0 ? true:false;
    }

    @Override
    public boolean existDramaId(Integer dramaId) {
        return collectMapper.existDramaId(dramaId)>0 ? true:false;
    }

    @Override
    public boolean updateCollectMsg(Collect collect) {
        return collectMapper.updateCollectMsg(collect) >0 ?true:false;
    }

    @Override
    public boolean deleteCollect(Integer dramaId) {
        return collectMapper.deleteCollect(dramaId) >0 ?true:false;
    }

    @Override
    public List<Collect> allCollect()

    {
        return collectMapper.allCollect();
    }

    @Override
    public List<Collect> collectionOfUser(Integer userId)

    {
        return collectMapper.collectionOfUser(userId);
    }
}
