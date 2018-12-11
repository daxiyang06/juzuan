package com.juzuan.advertiser.rpts.mapper;

import com.juzuan.advertiser.rpts.model.RelationshopCondition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelationshopConditionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RelationshopCondition record);

    int insertSelective(RelationshopCondition record);

    RelationshopCondition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RelationshopCondition record);

    int updateByPrimaryKey(RelationshopCondition record);
}