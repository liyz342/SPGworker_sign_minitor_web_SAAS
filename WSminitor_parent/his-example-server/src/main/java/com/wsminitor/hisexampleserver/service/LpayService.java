package com.wsminitor.hisexampleserver.service;

import com.wsminitor.hisexampleserver.entity.Lrecord;
import com.wsminitor.hisexampleserver.entity.Pay;
import com.wsminitor.hisexampleserver.entity.Register;

import java.util.List;

public interface LpayService{

    int updPay(Register register);
    int addPay(Register register);
    List<Pay> selPays(Register register);
    List<Lrecord> selSurplus(Lrecord lrecord);
}
