package com.health.items.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.health.items.dao.MemberMapper;
import com.health.items.pojo.Member;
import com.health.items.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Member业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public List<Integer> findMemberCountByMonth(List<String> month) {
        List<Integer> list = new ArrayList<>();
        for (String s : month) {
            s = s + ".31";
            Integer count = memberMapper.findMemberCountBeforeDate(s);
            list.add(count);
        }

        return list;
    }

    /**
     * Member条件+分页查询
     * @param member 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Member> findPage(Member member, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(member);
        //执行搜索
        return new PageInfo<Member>(memberMapper.selectByExample(example));
    }

    /**
     * Member分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Member> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Member>(memberMapper.selectAll());
    }

    /**
     * Member条件查询
     * @param member
     * @return
     */
    @Override
    public List<Member> findList(Member member){
        //构建查询条件
        Example example = createExample(member);
        //根据构建的条件查询数据
        return memberMapper.selectByExample(example);
    }


    /**
     * Member构建查询对象
     * @param member
     * @return
     */
    public Example createExample(Member member){
        Example example=new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        if(member!=null){
            //
            if(!StringUtils.isEmpty(member.getId())){
                    criteria.andEqualTo("id",member.getId());
            }
            //
            if(!StringUtils.isEmpty(member.getFileNumber())){
                    criteria.andEqualTo("fileNumber",member.getFileNumber());
            }
            //
            if(!StringUtils.isEmpty(member.getName())){
                    criteria.andLike("name","%"+member.getName()+"%");
            }
            //
            if(!StringUtils.isEmpty(member.getSex())){
                    criteria.andEqualTo("sex",member.getSex());
            }
            //
            if(!StringUtils.isEmpty(member.getIdCard())){
                    criteria.andEqualTo("idCard",member.getIdCard());
            }
            //
            if(!StringUtils.isEmpty(member.getPhoneNumber())){
                    criteria.andEqualTo("phoneNumber",member.getPhoneNumber());
            }
            //
            if(!StringUtils.isEmpty(member.getRegTime())){
                    criteria.andEqualTo("regTime",member.getRegTime());
            }
            //
            if(!StringUtils.isEmpty(member.getPassword())){
                    criteria.andEqualTo("password",member.getPassword());
            }
            //
            if(!StringUtils.isEmpty(member.getEmail())){
                    criteria.andEqualTo("email",member.getEmail());
            }
            //
            if(!StringUtils.isEmpty(member.getBirthday())){
                    criteria.andEqualTo("birthday",member.getBirthday());
            }
            //
            if(!StringUtils.isEmpty(member.getRemark())){
                    criteria.andEqualTo("remark",member.getRemark());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        memberMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Member
     * @param member
     */
    @Override
    public void update(Member member){
        memberMapper.updateByPrimaryKey(member);
    }

    /**
     * 增加Member
     * @param member
     */
    @Override
    public void add(Member member){
        memberMapper.insert(member);
    }

    /**
     * 根据ID查询Member
     * @param id
     * @return
     */
    @Override
    public Member findById(Integer id){
        return  memberMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Member全部数据
     * @return
     */
    @Override
    public List<Member> findAll() {
        return memberMapper.selectAll();
    }
}
