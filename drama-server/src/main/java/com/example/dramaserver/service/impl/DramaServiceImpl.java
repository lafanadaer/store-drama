package com.example.dramaserver.service.impl;

import com.example.dramaserver.dao.DramaMapper;
import com.example.dramaserver.domain.Drama;
import com.example.dramaserver.service.DramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DramaServiceImpl implements DramaService {

    @Autowired
    private DramaMapper dramaMapper;

    @Override
    public List<Drama> allDrama()
    {
        return dramaMapper.allDrama();
    }

    @Override
    public boolean addDrama(Drama drama)
    {

        return dramaMapper.insertSelective(drama) > 0?true:false;
    }

    @Override
    public boolean updateDramaMsg(Drama drama) {
        return dramaMapper.updateDramaMsg(drama) >0 ?true:false;
    }

    @Override
    public boolean updateDramaPic(Drama drama) {

        return dramaMapper.updateDramaPic(drama) >0 ?true:false;
    }

    @Override
    public boolean deleteDrama(Integer id) {
        return dramaMapper.deleteDrama(id) >0 ?true:false;
    }

    @Override
    public List<Drama> dramaOfCategoryId(Integer categoryId)

    {
        return dramaMapper.dramaOfCategoryId(categoryId);
    }

    @Override
    public List<Drama> dramaOfId(Integer id)

    {
        return dramaMapper.dramaOfId(id);
    }

    @Override
    public List<Drama> dramaOfCategoryName(String name)

    {
        return dramaMapper.dramaOfCategoryName(name);
    }

    @Override
    public List<Drama> dramaOfName(String name)

    {
        return dramaMapper.dramaOfName(name);
    }
}

