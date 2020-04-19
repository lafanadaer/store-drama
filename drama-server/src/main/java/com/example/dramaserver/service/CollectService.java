package com.example.dramaserver.service;

import com.example.dramaserver.domain.Collect;

import java.util.List;
public interface CollectService {

    boolean addCollection(Collect collect);

    boolean existDramaId(Integer dramaId);

    boolean updateCollectMsg(Collect collect);

    boolean deleteCollect(Integer songId);

    List<Collect> allCollect();

    List<Collect> collectionOfUser(Integer userId);
}
