package com.subnit.fastnotice.dao;

import java.util.List;

import com.subnit.fastnotice.dto.NoticeMethodDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface NoticeMethodDao {
    @Delete({
        "delete from notice_method",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into notice_method (id, notice_id, ",
        "notice_method, notice_method_service, ",
        "gmt_modified, gmt_create, ",
        "target, extend)",
        "values (#{id,jdbcType=BIGINT}, #{noticeId,jdbcType=BIGINT}, ",
        "#{noticeMethod,jdbcType=VARCHAR}, #{noticeMethodService,jdbcType=VARCHAR}, ",
        "#{gmtModified,jdbcType=TIMESTAMP}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
        "#{target,jdbcType=LONGVARCHAR}, #{extend,jdbcType=LONGVARCHAR})"
    })
    int insert(NoticeMethodDO record);

    @Select({
        "select",
        "id, notice_id, notice_method, notice_method_service, gmt_modified, gmt_create, ",
        "target, extend",
        "from notice_method",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="notice_id", property="noticeId", jdbcType=JdbcType.BIGINT),
        @Result(column="notice_method", property="noticeMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="notice_method_service", property="noticeMethodService", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="target", property="target", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="extend", property="extend", jdbcType=JdbcType.LONGVARCHAR)
    })
    NoticeMethodDO selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, notice_id, notice_method, notice_method_service, gmt_modified, gmt_create, ",
        "target, extend",
        "from notice_method"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="notice_id", property="noticeId", jdbcType=JdbcType.BIGINT),
        @Result(column="notice_method", property="noticeMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="notice_method_service", property="noticeMethodService", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="target", property="target", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="extend", property="extend", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<NoticeMethodDO> selectAll();

    @Update({
        "update notice_method",
        "set notice_id = #{noticeId,jdbcType=BIGINT},",
          "notice_method = #{noticeMethod,jdbcType=VARCHAR},",
          "notice_method_service = #{noticeMethodService,jdbcType=VARCHAR},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "target = #{target,jdbcType=LONGVARCHAR},",
          "extend = #{extend,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(NoticeMethodDO record);
}