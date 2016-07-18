package cn.itcast.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.shiro.MyRealm;

//手动清除shiro缓存示例类
@Controller
public class ClearShiroCache {

    @Autowired
    private MyRealm myRealm;
    
    //该方法用于测试手动清除shiro缓存。直接在地址栏中输入该url即可调用该方法并删除shiro缓存
    @RequestMapping("/clearShiroCache")
    public void clearShiroCache() {
        myRealm.clearCached();
    }
    
}