package com.example.management.controller;


import com.example.management.mapper.InfoMapper;
import com.example.management.model.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class Infocontroller {
    @Autowired
    private InfoMapper infoMapper;

    @GetMapping("/add_info")
    public String reg() {
        return "add_info";
    }

    @RequestMapping("/add_info")
    public String register(HttpServletRequest request, Map<String, Object> map) {
        String point = request.getParameter("point");
        String explanation = request.getParameter("explanation");
        Info info = new Info();
        info.setPoint(point);
        info.setExplanation(explanation);


        Info info1 = infoMapper.getinfo(point);
        if (info1 != null) {
            map.put("msg1", "该知识点已经添加，无需重复添加！");
            return "add_info";
        } else {
            infoMapper.addinfo(info);
            return "select";
        }

    }

    @RequestMapping("/getinfo")
    public String getinfo(HttpServletRequest request, Map<String, Object> map) {
        String point = request.getParameter("point");
        Info info = infoMapper.getinfo(point);
        if (info != null) {
            map.put("msg", "此知识点已经记录，可直接查看！");
            return "add_info";
        } else {
            map.put("msg", "此知识点未记录，可添加记录！");
            return "add_info";
        }
    }

    @GetMapping("/login")
    public String info_login(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) {
        String point = request.getParameter("point");
        String explanation = request.getParameter("explanation");
        Info info = infoMapper.getinfo(point);
        if (info != null) {
            map.put("msg2", point + ":" + info.getExplanation());
            return "login";
        } else {
            map.put("msg2", "该知识点不存在！");
            return "login";
        }

    }

    @RequestMapping("/deleteinfo")
    public String deleteinfo(HttpServletRequest request, Map<String, Object> map) {
        String point = request.getParameter("point");
        Info getinfo = infoMapper.getinfo(point);
        if (getinfo != null) {
            infoMapper.deleteinfo(point);
            map.put("msg4", "该知识点已成功删除！");
            return "login";
        } else {
            map.put("msg4", "该知识点不存在！");
            return "login";
        }
    }

    @RequestMapping("/updateinfo")
    public String update(HttpServletRequest request, Map<String, Object> map) {
        String point = request.getParameter("point");
        String explanation = request.getParameter("explanation");
        Info getinfo = infoMapper.getinfo(point);
        if (getinfo != null) {
            infoMapper.updateinfo(point, explanation);
            map.put("msg3", "该知识点详细说明已更新！");
            return "login";
        } else {
            map.put("msg3", "该知识点不存在！");
            return "login";
        }
    }

    @RequestMapping("/getallinfo")
    public String getallinfo(HttpServletRequest request, Map<String, Object> map) {
        List<Info> infoList = infoMapper.getallinfo();
        int count = infoList.size();
        List<Info> newInfoList = IntStream.range(0, count)
                .mapToObj(index -> new Info(String.valueOf(index + 1), infoList.get(index).getPoint(), infoList.get(index).getExplanation()))
                .collect(Collectors.toList());
        map.put("infoList", newInfoList);
        return "getallinfo";
    }

}
