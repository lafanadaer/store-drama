package com.example.dramaserver.service;

import com.example.dramaserver.domain.CateDrama;

import java.util.List;

public interface CateDramaService {

    boolean addCateDrama(CateDrama cateDrama);

    boolean updateCateDramaMsg(CateDrama cateDrama);

    boolean deleteCateDrama(Integer dramaId);

    List<CateDrama> allCateDrama();

    List<CateDrama> cateDramaOfDramaId(Integer dramaCateId);
}
