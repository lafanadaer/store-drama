package com.example.dramaserver.service;

import com.example.dramaserver.domain.Rank;

public interface RankService {

    int rankOfDramaId(Long dramaId);

    boolean addRank(Rank rank);
}
