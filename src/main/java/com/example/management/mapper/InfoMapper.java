package com.example.management.mapper;

import com.example.management.model.Info;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InfoMapper {
    @Insert("insert into info (point,explanation) values (#{point},#{explanation})")
    void addinfo(Info info);
    @Select("select * from info where point=#{point}")
    Info getinfo(String point);
    @Select("select * from info where point=#{point}")
    Info login(String point, String explanation);
    @Delete("delete from info where point=#{point}")
    void deleteinfo(String point);
    @Update("update info set explanation=#{explanation} where point=#{point}")
    void updateinfo(String point,String explanation);
    @Select("select * from info")
    List<Info> getallinfo();

}