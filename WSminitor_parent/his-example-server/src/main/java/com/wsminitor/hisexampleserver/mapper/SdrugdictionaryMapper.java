package com.wsminitor.hisexampleserver.mapper;

import com.wsminitor.hisexampleserver.entity.Area;
import com.wsminitor.hisexampleserver.entity.Sdrugdictionary;
import com.wsminitor.hisexampleserver.entity.Type;
import com.wsminitor.hisexampleserver.entity.Unit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SdrugdictionaryMapper {
    //药品字典的增删改查
    List<SdrugdictionaryMapper> findAllSdrugdictionary(Sdrugdictionary sdrugdictionary);
    int addSdrugdictionary(Sdrugdictionary sdrugdictionary);
    int editSdrugdictionary(Sdrugdictionary sdrugdictionary);
    int deleteSdrugdictionary(Integer drugId);
    List<Unit> findAllUnit();
    List<Area> findAllArea();
    List<Type> findAllType();
    int count(Sdrugdictionary sdrugdictionary);
}
