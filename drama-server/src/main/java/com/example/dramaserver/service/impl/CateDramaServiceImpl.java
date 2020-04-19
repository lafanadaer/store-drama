package com.example.dramaserver.service.impl;

import com.example.dramaserver.dao.CateDramaMapper;
import com.example.dramaserver.domain.CateDrama;
import com.example.dramaserver.service.CateDramaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateDramaServiceImpl implements CateDramaService {
    @Autowired
    private CateDramaMapper cateDramaMapper;

    @Override
    public List<CateDrama> allCateDrama()
    {
        return cateDramaMapper.allCateDrama();
    }

    @Override
    public boolean updateCateDramaMsg(CateDrama cateDrama) {
        return cateDramaMapper.updateCateDramaMsg(cateDrama) >0 ?true:false;
    }

    @Override
    public boolean deleteCateDrama(Integer dramaId) {
        return cateDramaMapper.deleteCateDrama(dramaId) >0 ?true:false;
    }

    @Override
    public boolean addCateDrama(CateDrama cateDrama)
    {
        return cateDramaMapper.insertSelective(cateDrama) > 0?true:false;
    }

    @Override
    public List<CateDrama> cateDramaOfDramaId(Integer cateDramaId)
    {
        return cateDramaMapper.cateDramaOfDramaId(cateDramaId);
    }

}

