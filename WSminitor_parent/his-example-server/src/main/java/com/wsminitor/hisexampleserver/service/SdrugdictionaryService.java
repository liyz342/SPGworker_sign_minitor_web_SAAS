package com.wsminitor.hisexampleserver.service;

import com.wsminitor.hisexampleserver.entity.Area;
import com.wsminitor.hisexampleserver.entity.Sdrugdictionary;
import com.wsminitor.hisexampleserver.entity.Type;
import com.wsminitor.hisexampleserver.entity.Unit;
import com.wsminitor.hisexampleserver.mapper.SdrugdictionaryMapper;

import java.util.List;

public interface SdrugdictionaryService{
    List<SdrugdictionaryMapper> findAllSdrugdictionary(Sdrugdictionary sdrugdictionary);
    int addSdrugdictionary(Sdrugdictionary sdrugdictionary);
    int editSdrugdictionary(Sdrugdictionary sdrugdictionary);
    int deleteSdrugdictionary(Integer drugId);
    List<Unit> findAllUnit();
    List<Area> findAllArea();
    List<Type> findAllType();
    int count(Sdrugdictionary sdrugdictionary);
}
