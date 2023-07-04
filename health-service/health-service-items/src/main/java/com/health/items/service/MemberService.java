package com.health.items.service;

import com.github.pagehelper.PageInfo;
import com.health.items.pojo.Member;

import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Member业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface MemberService {


    public List<Integer> findMemberCountByMonth(List<String> month);

    /***
     * Member多条件分页查询
     * @param member
     * @param page
     * @param size
     * @return
     */
    PageInfo<Member> findPage(Member member, int page, int size);

    /***
     * Member分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Member> findPage(int page, int size);

    /***
     * Member多条件搜索方法
     * @param member
     * @return
     */
    List<Member> findList(Member member);

    /***
     * 删除Member
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Member数据
     * @param member
     */
    void update(Member member);

    /***
     * 新增Member
     * @param member
     */
    void add(Member member);

    /**
     * 根据ID查询Member
     * @param id
     * @return
     */
     Member findById(Integer id);

    /***
     * 查询所有Member
     * @return
     */
    List<Member> findAll();
}
