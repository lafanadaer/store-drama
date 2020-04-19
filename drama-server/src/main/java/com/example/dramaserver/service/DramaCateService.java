package com.example.dramaserver.service;

import com.example.dramaserver.domain.DramaCate;

import java.util.List;

public interface DramaCateService {

    boolean addDramaCate (DramaCate dramaCate);

    boolean updateDramaCateMsg(DramaCate dramaCate);

    boolean updateDramaCatePic(DramaCate dramaCate);

    boolean deleteDramaCate(Integer id);

    List<DramaCate> allDramaCate();

    List<DramaCate> likeName(String name);

    List<DramaCate> dramaCateOfName(String name);
}
