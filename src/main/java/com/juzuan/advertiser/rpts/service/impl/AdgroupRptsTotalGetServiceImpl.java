package com.juzuan.advertiser.rpts.service.impl;

import com.juzuan.advertiser.rpts.mapper.AdgroupListMapper;
import com.juzuan.advertiser.rpts.mapper.AdgroupRptsTotalGetMapper;
import com.juzuan.advertiser.rpts.mapper.TaobaoAuthorizeUserMapper;
import com.juzuan.advertiser.rpts.service.AdgroupRptsTotalGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 广告主定向多日汇总数据查询
 */
@Service
public class AdgroupRptsTotalGetServiceImpl implements AdgroupRptsTotalGetService {
    private static String appkey = "25139411";
    private static String url = "https://eco.taobao.com/router/rest";
    private static String secret = "ccd188d30d3731df6d176ba8a2151765";

    @Autowired
    private TaobaoAuthorizeUserMapper taobaoAuthorizeUserMapper;
    @Autowired
    private AdgroupListMapper adgroupListMapper;
    @Autowired
    private AdgroupRptsTotalGetMapper adgroupRptsTotalGetMapper;
    @Scheduled(cron = "*/5 * * * * ?")
    public String AdgroupRptsTotalGet(){

        return "";
    }

}
