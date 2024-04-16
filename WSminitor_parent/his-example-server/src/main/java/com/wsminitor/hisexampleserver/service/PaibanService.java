package com.wsminitor.hisexampleserver.service;

import com.wsminitor.hisexampleserver.entity.Ban;
import com.wsminitor.hisexampleserver.entity.Paiban;

import java.util.List;

public interface PaibanService{
    List<Paiban> findAllPaiban(Paiban paiban);
    int editPaiban(Paiban paiban);
    List<Ban> findAllBan();
    int insertPaiban(Paiban paiban);
    int count(Integer Id);
}
