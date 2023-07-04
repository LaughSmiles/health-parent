package com.health.items.dao;
import com.health.items.pojo.Member;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:shenkunlin
 * @Description:Member的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface MemberMapper extends Mapper<Member> {
    @Select("select count(id) from tb_member where regTime <= #{date}")
    Integer findMemberCountBeforeDate(String date);

    @Select("select count(id) from tb_member where regTime = #{today}")
    Integer findMemberCountByDate(String today);

    @Select("select count(id) from tb_member")
    Integer findMemberTotalCount();

    @Select("select count(id) from tb_member where regTime >= #{thisWeekMonday}")
    Integer findMemberCountAfterDate(String thisWeekMonday);
}
