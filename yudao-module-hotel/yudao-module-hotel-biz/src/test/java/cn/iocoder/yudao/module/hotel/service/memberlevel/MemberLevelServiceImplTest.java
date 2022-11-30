package cn.iocoder.yudao.module.hotel.service.memberlevel;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.memberlevel.MemberLevelMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link MemberLevelServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(MemberLevelServiceImpl.class)
public class MemberLevelServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MemberLevelServiceImpl memberLevelService;

    @Resource
    private MemberLevelMapper memberLevelMapper;

    @Test
    public void testCreateMemberLevel_success() {
        // 准备参数
        MemberLevelCreateReqVO reqVO = randomPojo(MemberLevelCreateReqVO.class);

        // 调用
        Long memberLevelId = memberLevelService.createMemberLevel(reqVO);
        // 断言
        assertNotNull(memberLevelId);
        // 校验记录的属性是否正确
        MemberLevelDO memberLevel = memberLevelMapper.selectById(memberLevelId);
        assertPojoEquals(reqVO, memberLevel);
    }

    @Test
    public void testUpdateMemberLevel_success() {
        // mock 数据
        MemberLevelDO dbMemberLevel = randomPojo(MemberLevelDO.class);
        memberLevelMapper.insert(dbMemberLevel);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MemberLevelUpdateReqVO reqVO = randomPojo(MemberLevelUpdateReqVO.class, o -> {
            o.setId(dbMemberLevel.getId()); // 设置更新的 ID
        });

        // 调用
        memberLevelService.updateMemberLevel(reqVO);
        // 校验是否更新正确
        MemberLevelDO memberLevel = memberLevelMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, memberLevel);
    }

    @Test
    public void testUpdateMemberLevel_notExists() {
        // 准备参数
        MemberLevelUpdateReqVO reqVO = randomPojo(MemberLevelUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> memberLevelService.updateMemberLevel(reqVO), MEMBER_LEVEL_NOT_EXISTS);
    }

    @Test
    public void testDeleteMemberLevel_success() {
        // mock 数据
        MemberLevelDO dbMemberLevel = randomPojo(MemberLevelDO.class);
        memberLevelMapper.insert(dbMemberLevel);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMemberLevel.getId();

        // 调用
        memberLevelService.deleteMemberLevel(id);
       // 校验数据不存在了
       assertNull(memberLevelMapper.selectById(id));
    }

    @Test
    public void testDeleteMemberLevel_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> memberLevelService.deleteMemberLevel(id), MEMBER_LEVEL_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMemberLevelPage() {
       // mock 数据
       MemberLevelDO dbMemberLevel = randomPojo(MemberLevelDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setLevel(null);
           o.setCreateTime(null);
       });
       memberLevelMapper.insert(dbMemberLevel);
       // 测试 name 不匹配
       memberLevelMapper.insert(cloneIgnoreId(dbMemberLevel, o -> o.setName(null)));
       // 测试 level 不匹配
       memberLevelMapper.insert(cloneIgnoreId(dbMemberLevel, o -> o.setLevel(null)));
       // 测试 createTime 不匹配
       memberLevelMapper.insert(cloneIgnoreId(dbMemberLevel, o -> o.setCreateTime(null)));
       // 准备参数
       MemberLevelPageReqVO reqVO = new MemberLevelPageReqVO();
       reqVO.setName(null);
       reqVO.setLevel(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<MemberLevelDO> pageResult = memberLevelService.getMemberLevelPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMemberLevel, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMemberLevelList() {
       // mock 数据
       MemberLevelDO dbMemberLevel = randomPojo(MemberLevelDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setLevel(null);
           o.setCreateTime(null);
       });
       memberLevelMapper.insert(dbMemberLevel);
       // 测试 name 不匹配
       memberLevelMapper.insert(cloneIgnoreId(dbMemberLevel, o -> o.setName(null)));
       // 测试 level 不匹配
       memberLevelMapper.insert(cloneIgnoreId(dbMemberLevel, o -> o.setLevel(null)));
       // 测试 createTime 不匹配
       memberLevelMapper.insert(cloneIgnoreId(dbMemberLevel, o -> o.setCreateTime(null)));
       // 准备参数
       MemberLevelExportReqVO reqVO = new MemberLevelExportReqVO();
       reqVO.setName(null);
       reqVO.setLevel(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<MemberLevelDO> list = memberLevelService.getMemberLevelList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMemberLevel, list.get(0));
    }

}
