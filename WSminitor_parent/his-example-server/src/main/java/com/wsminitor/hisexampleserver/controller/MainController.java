package com.wsminitor.hisexampleserver.controller;

import com.wsminitor.hisexampleserver.entity.Paiban;
import com.wsminitor.hisexampleserver.entity.WorkersStatus;
import com.wsminitor.hisexampleserver.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("main")
public class MainController {
    /*
    * 查询体征检测列表
    * */
    @Autowired
    private MainService mainService;
    @RequestMapping("statusList")
    @ResponseBody
    public Object statusList(){
        List<WorkersStatus> statusList = mainService.statusList();
        return statusList;
    }
    /*
    @RequestMapping("one")
    @ResponseBody
    public Object one(){
        List<Paiban> one = mainService.one();
        return one;
    }
    @RequestMapping("two")
    @ResponseBody
    public Object two(){
        List<Paiban> two = mainService.two();
        return two;
    }
    @RequestMapping("three")
    @ResponseBody
    public Object three(){
        List<Paiban> three = mainService.three();
        return three;
    }
    @RequestMapping("four")
    @ResponseBody
    public Object four(){
        List<Paiban> four = mainService.four();
        return four;
    }
    @RequestMapping("five")
    @ResponseBody
    public Object five(){
        List<Paiban> five = mainService.five();
        return five;
    }
    @RequestMapping("six")
    @ResponseBody
    public Object six(){
        List<Paiban> six = mainService.six();
        return six;
    }
    @RequestMapping("seven")
    @ResponseBody
    public Object seven(){
        List<Paiban> seven = mainService.seven();
        return seven;
    }
    */
    /*
    * 查询当天体征监控人数
    * */
    @RequestMapping("currentNum")
    @ResponseBody
    public Object currentNum(){
        int currentNum = mainService.currentNum();
        return currentNum;
    }
    /*
    * 查询所有人数
    * */
    @RequestMapping("Total")
    @ResponseBody
    public Object Total(){
        int Total = mainService.Total();
        return Total;
    }
    /*
     * 查询不健康所有人数
     * */
    @RequestMapping("unhealthTotal")
    @ResponseBody
    public Object unhealthTotal(){
        int unhealthTotal = mainService.unhealthTotal();
        return unhealthTotal;
    }
    /*
     * 查询当天不健康所有人数
     * */
    @RequestMapping("currentUnhealth")
    @ResponseBody
    public Object currentUnhealth(){
        int currentUnhealth = mainService.currentUnhealth();
        return currentUnhealth;
    }
}
