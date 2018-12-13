package com.juzuan.advertiser.rpts.service.impl;

import com.juzuan.advertiser.rpts.mapper.AccountRptsTotalGetMapper;
import com.juzuan.advertiser.rpts.mapper.TaobaoAuthorizeUserMapper;
import com.juzuan.advertiser.rpts.model.TaobaoAuthorizeUser;
import com.juzuan.advertiser.rpts.service.AccountRptsTotalGetService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ZuanshiAdvertiserAccountRptsTotalGetRequest;
import com.taobao.api.response.ZuanshiAdvertiserAccountRptsTotalGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRptsTotalGetServiceImpl implements AccountRptsTotalGetService {
    private static String appkey="25139411";
    private static String url ="https://eco.taobao.com/router/rest";
    private static String secret="ccd188d30d3731df6d176ba8a2151765";

    @Autowired
    private TaobaoAuthorizeUserMapper taobaoAuthorizeUserMapper;
    @Autowired
    private AccountRptsTotalGetMapper accountRptsTotalGetMapper;
    //@Scheduled(cron = "*/5 * * * * ?")
    public String AccountRptsTotalGet(){
        List<TaobaoAuthorizeUser> taobaoAuthorizeUsers = taobaoAuthorizeUserMapper.selectAllToken();
        for (TaobaoAuthorizeUser tau :taobaoAuthorizeUsers) {
            TaobaoClient client = new DefaultTaobaoClient(url,appkey,secret);
            ZuanshiAdvertiserAccountRptsTotalGetRequest req = new ZuanshiAdvertiserAccountRptsTotalGetRequest();
            req.setStartTime("2018-08-29");
            req.setEndTime("2018-11-27");
            req.setEffect(7L);
            req.setEffectType("click");
            ZuanshiAdvertiserAccountRptsTotalGetResponse rsp = null;
            try {
                rsp = client.execute(req,tau.getAccessToken());
            }catch (ApiException e){
                e.printStackTrace();
            }
            System.out.println("钻展广告主账户多日数据汇总: "+rsp.getBody());



        }

        return "";
    }

}
