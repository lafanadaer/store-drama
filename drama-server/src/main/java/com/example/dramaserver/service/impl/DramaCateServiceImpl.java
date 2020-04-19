package com.example.dramaserver.service.impl;

import com.example.dramaserver.dao.DramaCateMapper;
import com.example.dramaserver.domain.DramaCate;

import com.example.dramaserver.service.DramaCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DramaCateServiceImpl implements DramaCateService {

    @Autowired
    private DramaCateMapper dramaCateMapper;

    @Override
    public boolean updateDramaCateMsg(DramaCate dramaCate) {
        return dramaCateMapper.updateDramaCateMsg(dramaCate) >0 ?true:false;
    }

    @Override
    public boolean deleteDramaCate(Integer id) {
        return dramaCateMapper.deleteDramaCate(id) >0 ?true:false;
    }

    @Override
    public List<DramaCate> allDramaCate()
    {
        return dramaCateMapper.allDramaCate();
    }

    @Override
    public List<DramaCate> likeName(String name)
    {
        return dramaCateMapper.likeName(name);
    }

    @Override
    public List<DramaCate> dramaCateOfName(String name)
    {
        return dramaCateMapper.dramaCateOfName(name);
    }

    @Override
    public boolean addDramaCate(DramaCate dramaCate)
    {
        return dramaCateMapper.insertSelective(dramaCate) > 0?true:false;
    }

    @Override
    public boolean updateDramaCatePic(DramaCate dramaCate) {

        return dramaCateMapper.updateDramaCatePic(dramaCate) >0 ?true:false;
    }
}

