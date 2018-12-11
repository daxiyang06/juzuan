package com.juzuan.advertiser.rpts.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.juzuan.advertiser.rpts.mapper.RelationshopConditionMapper;
import com.juzuan.advertiser.rpts.mapper.TaobaoAuthorizeUserMapper;
import com.juzuan.advertiser.rpts.model.RelationshopCondition;
import com.juzuan.advertiser.rpts.model.TaobaoAuthorizeUser;
import com.juzuan.advertiser.rpts.service.RelationshopConditionService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ZuanshiBannerRelationshopPackageConditionFindRequest;
import com.taobao.api.response.ZuanshiBannerRelationshopPackageConditionFindResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RelationshopConditionServiceImpl implements RelationshopConditionService {
    private static String appkey="25139411";
    private static String url ="https://eco.taobao.com/router/rest";
    private static String secret="ccd188d30d3731df6d176ba8a2151765";

    @Autowired
    private TaobaoAuthorizeUserMapper taobaoAuthorizeUserMapper;
    @Autowired
    private RelationshopConditionMapper relationshopConditionMapper;
    //@Scheduled(cron = "*/5 * * * * ?")
    public String RelationshopCondition(){
        List<TaobaoAuthorizeUser> taobaoAuthorizeUsers = taobaoAuthorizeUserMapper.selectAllToken();
        for (TaobaoAuthorizeUser tau: taobaoAuthorizeUsers) {
            TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
            ZuanshiBannerRelationshopPackageConditionFindRequest req = new ZuanshiBannerRelationshopPackageConditionFindRequest();
            ZuanshiBannerRelationshopPackageConditionFindResponse rsp = null;
            try {
                rsp = client.execute(req, tau.getAccessToken());
            } catch (ApiException e) {
                e.printStackTrace();
            }
            System.out.println("智钻获取店铺型定向店铺包条件 : " + rsp.getBody());
            //解析响应返回的json
            JSONObject oneObject = JSON.parseObject(rsp.getBody());
            JSONObject zuanshi = oneObject.getJSONObject("zuanshi_banner_relationshop_package_condition_find_response");
            JSONObject twoObject = JSON.parseObject(zuanshi.toString());
            JSONObject result = twoObject.getJSONObject("result");
            JSONObject thrObject = JSON.parseObject(result.toString());
            JSONObject condition = thrObject.getJSONObject("shop_package_query_condition");

            RelationshopCondition rc = new RelationshopCondition();
            JSONObject object = JSON.parseObject(condition.toString());
            JSONObject catList = object.getJSONObject("cat_list");
            JSONObject shopScale = object.getJSONObject("shop_scale_list");
            JSONObject preferenceList = object.getJSONObject("shop_preference_list");
            List<Object> list01 = new ArrayList<>(preferenceList.getJSONArray("shop_preference_d_t_o"));
            if (catList ==null){
                continue;
            }else {
                List<Object> list = new ArrayList<>(catList.getJSONArray("category_d_t_o"));
                if (preferenceList == null){
                    continue;
                }else {
                    //list.add("shop_scale_d_t_o" + " : " + shopScale.getJSONArray("shop_scale_d_t_o"));
                    list.addAll(shopScale.getJSONArray("shop_scale_d_t_o"));
                }
                //list01.add("category_d_t_o" + " : " + list);
                list01.addAll(list);
            }
            //System.out.println(list01.get(3).toString());
            JSONArray one = JSON.parseArray(list01.toString());
            for (Object o: one.toArray()) {
                System.out.println(o);
                JSONArray two = JSON.parseArray(list01.get(list01.size()-1).toString());
                List<Object> list3 = new ArrayList<>(two);
                for (Object ob:two.toArray()) {
                    System.out.println(ob);
                    JSONArray three = JSON.parseArray(list3.get(list3.size()-1).toString());
                    for (Object obj: three.toArray()) {

                        System.out.println(obj);

                    }
                }
            }
        }

/*

            if (catList==null){
                continue;
            }else {
                JSONArray cate = catList.getJSONArray("category_d_t_o");
                for (Object o:cate) {
                    System.out.println("1 :"+o);
                }
            }
            if (shopScale == null){
                continue;
            }else {
                JSONArray scale = shopScale.getJSONArray("shop_scale_d_t_o");
                for (Object ob:scale) {
                    System.out.println("2 :"+ob);
                }

            }
            if (preferenceList == null){
                continue;
            }else {
                JSONArray preference = preferenceList.getJSONArray("shop_preference_d_t_o");
                for (Object objec:preference) {
                    System.out.println("3 :"+objec);
                }

            }

*/


        return "";

    }
}
