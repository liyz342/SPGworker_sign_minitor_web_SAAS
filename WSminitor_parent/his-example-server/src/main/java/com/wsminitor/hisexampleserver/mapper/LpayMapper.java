package com.wsminitor.hisexampleserver.mapper;

import com.wsminitor.hisexampleserver.entity.Lrecord;
import com.wsminitor.hisexampleserver.entity.Pay;
import com.wsminitor.hisexampleserver.entity.Register;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LpayMapper {

    int updPay(Register register);
    int addPay(Register register);
    List<Pay> selPays(Register register);
    List<Lrecord> selSurplus(Lrecord lrecord);
}
