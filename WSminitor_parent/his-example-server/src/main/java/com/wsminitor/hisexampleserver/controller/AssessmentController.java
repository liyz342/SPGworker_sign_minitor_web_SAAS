package com.wsminitor.hisexampleserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/assessment")
public class AssessmentController {

    private final Environment env;

    @Autowired
    public AssessmentController(Environment env) {
        this.env = env;
    }

    @RequestMapping(value = "/getAssessmentResult", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getAssessmentResult(@RequestBody AssessmentParams assessmentParams) {
        String result;
        try {
            String inputParams = convertToPythonParams(assessmentParams);
            result = callPythonModel(inputParams);
            //python汉字编码格式问题,导致只可以用英文
            //return ("HEALTH".equals(result)) ? ResponseEntity.ok(result) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            return ResponseEntity.ok(result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("评估出错");
        }
    }

    private String convertToPythonParams(AssessmentParams assessmentParams) {
        StringBuilder paramsBuilder = new StringBuilder();

        // 遍历AssessmentParams的字段，构建参数字符串
        for (Field field : AssessmentParams.class.getDeclaredFields()) {
            field.setAccessible(true); // 确保可以访问私有字段
            try {
                String value = String.valueOf(field.get(assessmentParams));
                if (value != null && !value.isEmpty()) {
                    // 将字段名和字段值拼接为参数对，用"&"连接不同的参数对
                    paramsBuilder.append(field.getName())
                            .append("=")
                            .append(value)
                            .append("&");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // 移除最后一个"&"字符
        if (paramsBuilder.length() > 0) {
            paramsBuilder.setLength(paramsBuilder.length() - 1);
        }

        return paramsBuilder.toString();
    }

    private String callPythonModel(String inputParams) throws IOException, InterruptedException {
        String pythonScriptPath = env.getProperty("app.python-script-path");
        System.out.println("python " + pythonScriptPath + "\\hjx.py " + inputParams);

        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath + "\\hjx.py", inputParams);
        Process process = processBuilder.start();
        process.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        System.out.println("Default Charset: " + Charset.defaultCharset());
        System.out.println(output);
        System.out.println(output.toString().trim());

        return output.toString().trim();
    }
}

class AssessmentParams {
    private String reportName;
    private String height;
    private String weight;
    private String gender;
    private String age;
    private String rbc;//红细胞计数RBC
    private String hgb;//血红蛋白HGb
    private String wbc;//白细胞计数WBC
    private String lym;//淋巴细胞计数 LYM
    private String plt;//血小板计数 PLT
    private String tc;//总胆固醇 TC
    private String tg;//甘油三酯 TG
    private String ldl_c;//低密度脂蛋白胆固醇 LDL-C
    private String hdl_c;//高密度脂蛋白胆固醇 HDL-C
    private String bg;//血糖浓度
    private String insulin;//肌酐含量
    private String cr;//胰岛素含量Cr
    private String bun;//血尿素氮BUN
    private String protein;//球蛋白、总蛋白、白蛋白含量
    private String bp;//血压
    private String heartRate;//心率
    private String medicalHistory;//体检报告中历史患病情况

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setRbc(String rbc) {
        this.rbc = rbc;
    }

    public void setHgb(String hgb) {
        this.hgb = hgb;
    }

    public void setWbc(String wbc) {
        this.wbc = wbc;
    }

    public void setLym(String lym) {
        this.lym = lym;
    }

    public void setPlt(String plt) {
        this.plt = plt;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public void setLdl_c(String ldl_c) {
        this.ldl_c = ldl_c;
    }

    public void setHdl_c(String hdl_c) {
        this.hdl_c = hdl_c;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public void setInsulin(String insulin) {
        this.insulin = insulin;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public void setBun(String bun) {
        this.bun = bun;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getReportName() {
        return reportName;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getRbc() {
        return rbc;
    }

    public String getHgb() {
        return hgb;
    }

    public String getWbc() {
        return wbc;
    }

    public String getLym() {
        return lym;
    }

    public String getPlt() {
        return plt;
    }

    public String getTc() {
        return tc;
    }

    public String getTg() {
        return tg;
    }

    public String getLdl_c() {
        return ldl_c;
    }

    public String getHdl_c() {
        return hdl_c;
    }

    public String getBg() {
        return bg;
    }

    public String getInsulin() {
        return insulin;
    }

    public String getCr() {
        return cr;
    }

    public String getBun() {
        return bun;
    }

    public String getProtein() {
        return protein;
    }

    public String getBp() {
        return bp;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }
}