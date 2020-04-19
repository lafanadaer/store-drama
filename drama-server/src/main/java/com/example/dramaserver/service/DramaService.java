package com.example.dramaserver.service;

import com.example.dramaserver.domain.Drama;

import java.util.List;

public interface DramaService {
    boolean addDrama (Drama drama);

    boolean updateDramaMsg(Drama drama);

    boolean updateDramaPic(Drama drama);

    boolean deleteDrama(Integer id);

    List<Drama> allDrama();

    List<Drama> dramaOfCategoryId(Integer categoryId);

    List<Drama> dramaOfId(Integer id);

    List<Drama> dramaOfCategoryName(String name);

    List<Drama> dramaOfName(String name);
}
