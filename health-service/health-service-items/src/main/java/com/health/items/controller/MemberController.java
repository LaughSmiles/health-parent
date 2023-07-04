package com.health.items.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.health.items.dao.MemberMapper;
import com.health.items.pojo.Member;
import com.health.items.service.MemberService;
import constant.MessageConstant;
import constant.RedisMessageConstant;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "MemberController")
@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/getMemberReport")
    public Result getMemberReport(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-12);//获得当前日期之前12个月的日期

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH,1);
            list.add(new SimpleDateFormat("yyyy.MM").format(calendar.getTime()));
        }

        Map<String,Object> map = new HashMap<>();
        map.put("months",list);

        List<Integer> memberCount = memberService.findMemberCountByMonth(list);
        map.put("memberCount",memberCount);

        return new Result(true, StatusCode.OK, "查询成功", map);
    }



    @PostMapping("/login")
    public Result login(@RequestBody Map map){
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");

        //从redis中拿到 验证码
        Set members = redisTemplate.boundSetOps(telephone + RedisMessageConstant.SENDTYPE_LOGIN).members();
        String code = "";
        for (Object member : members) {
            code = (String)member;
        }
        if(!code.equals(validateCode)){
            return new Result(false, StatusCode.ERROR,MessageConstant.VALIDATECODE_ERROR);
        }

        Member memberPojo = new Member();
        memberPojo.setPhoneNumber(telephone);
        Member member = memberMapper.selectOne(memberPojo);
        if(member == null){
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            memberMapper.insert(member);
        }

        redisTemplate.boundSetOps(telephone).add(JSON.toJSONString(member));
        redisTemplate.expire(telephone, 30, TimeUnit.MINUTES);

        return new Result(true, StatusCode.OK, MessageConstant.LOGIN_SUCCESS);
    }



    /***
     * Member分页条件搜索实现
     * @param member
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Member条件分页查询",notes = "分页条件查询Member方法详情",tags = {"MemberController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Member对象",value = "传入JSON数据",required = false) Member member, @PathVariable  int page, @PathVariable  int size){
        //调用MemberService实现分页条件查询Member
        PageInfo<Member> pageInfo = memberService.findPage(member, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Member分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Member分页查询",notes = "分页查询Member方法详情",tags = {"MemberController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用MemberService实现分页查询Member
        PageInfo<Member> pageInfo = memberService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param member
     * @return
     */
    @ApiOperation(value = "Member条件查询",notes = "条件查询Member方法详情",tags = {"MemberController"})
    @PostMapping(value = "/search" )
    public Result<List<Member>> findList(@RequestBody(required = false) @ApiParam(name = "Member对象",value = "传入JSON数据",required = false) Member member){
        //调用MemberService实现条件查询Member
        List<Member> list = memberService.findList(member);
        return new Result<List<Member>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Member根据ID删除",notes = "根据ID删除Member方法详情",tags = {"MemberController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用MemberService实现根据主键删除
        memberService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Member数据
     * @param member
     * @param id
     * @return
     */
    @ApiOperation(value = "Member根据ID修改",notes = "根据ID修改Member方法详情",tags = {"MemberController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Member对象",value = "传入JSON数据",required = false) Member member,@PathVariable Integer id){
        //设置主键值
        member.setId(id);
        //调用MemberService实现修改Member
        memberService.update(member);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Member数据
     * @param member
     * @return
     */
    @ApiOperation(value = "Member添加",notes = "添加Member方法详情",tags = {"MemberController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Member对象",value = "传入JSON数据",required = true) Member member){
        //调用MemberService实现添加Member
        memberService.add(member);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Member数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Member根据ID查询",notes = "根据ID查询Member方法详情",tags = {"MemberController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Member> findById(@PathVariable Integer id){
        //调用MemberService实现根据主键查询Member
        Member member = memberService.findById(id);
        return new Result<Member>(true,StatusCode.OK,"查询成功",member);
    }

    /***
     * 查询Member全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Member",notes = "查询所Member有方法详情",tags = {"MemberController"})
    @GetMapping
    public Result<List<Member>> findAll(){
        //调用MemberService实现查询所有Member
        List<Member> list = memberService.findAll();
        return new Result<List<Member>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
