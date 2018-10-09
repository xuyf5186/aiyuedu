package cn.service.impl;

import cn.service.TranslateService;
import cn.util.HttpRequestUtil;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/4/26
 * Time 17:10
 */
@Service
public class TranslateServiceImpl implements TranslateService{
    @Override
    public String enToCh(String word) {
        String resStr=HttpRequestUtil.sendGet("http://translate.google.cn/translate_a/single?client=gtx&sl=en&tl=zh-CN&dt=t&q=googleq=",word);
        //正则提取翻译结果
        String regex = "googleq =(.*?)\\\"";
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(resStr);
        String result="";
        if (mMatcher.find()) {
            result=mMatcher.group(1);
        }
        return result;
    }
}
