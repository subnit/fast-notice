package com.subnit.fastnotice.dao;

import java.util.List;

import com.subnit.fastnotice.dto.NoticeDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface NoticeDao {
    @Delete({
        "delete from notice",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into notice (id, db_name, ",
        "notice_name, notice_interval, ",
        "notice_status, gmt_modified, ",
        "gmt_create, delete_status, ",
        "sql_text, notice_extend)",
        "values (#{id,jdbcType=BIGINT}, #{dbName,jdbcType=VARCHAR}, ",
        "#{noticeName,jdbcType=VARCHAR}, #{noticeInterval,jdbcType=INTEGER}, ",
        "#{noticeStatus,jdbcType=BIT}, #{gmtModified,jdbcType=TIMESTAMP}, ",
        "#{gmtCreate,jdbcType=TIMESTAMP}, #{deleteStatus,jdbcType=BIT}, ",
        "#{sqlText,jdbcType=LONGVARCHAR}, #{noticeExtend,jdbcType=LONGVARCHAR})"
    })
    int insert(NoticeDO record);

    @Select({
        "select",
        "id, db_name, notice_name, notice_interval, notice_status, gmt_modified, gmt_create, ",
        "delete_status, sql_text, notice_extend",
        "from notice",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "db_name", property = "dbName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "notice_name", property = "noticeName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "notice_interval", property = "noticeInterval", jdbcType = JdbcType.INTEGER),
        @Result(column = "notice_status", property = "noticeStatus", jdbcType = JdbcType.BIT),
        @Result(column = "gmt_modified", property = "gmtModified", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "gmt_create", property = "gmtCreate", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "delete_status", property = "deleteStatus", jdbcType = JdbcType.BIT),
        @Result(column = "sql_text", property = "sqlText", jdbcType = JdbcType.LONGVARCHAR),
        @Result(column = "notice_extend", property = "noticeExtend", jdbcType = JdbcType.LONGVARCHAR)
    })
    NoticeDO selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, db_name, notice_name, notice_interval, notice_status, gmt_modified, gmt_create, ",
        "delete_status, sql_text, notice_extend",
        "from notice"
    })
    @Results({
        @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
        @Result(column = "db_name", property = "dbName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "notice_name", property = "noticeName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "notice_interval", property = "noticeInterval", jdbcType = JdbcType.INTEGER),
        @Result(column = "notice_status", property = "noticeStatus", jdbcType = JdbcType.BIT),
        @Result(column = "gmt_modified", property = "gmtModified", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "gmt_create", property = "gmtCreate", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "delete_status", property = "deleteStatus", jdbcType = JdbcType.BIT),
        @Result(column = "sql_text", property = "sqlText", jdbcType = JdbcType.LONGVARCHAR),
        @Result(column = "notice_extend", property = "noticeExtend", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<NoticeDO> selectAll();

    @Update({
        "update notice",
        "set db_name = #{dbName,jdbcType=VARCHAR},",
        "notice_name = #{noticeName,jdbcType=VARCHAR},",
        "notice_interval = #{noticeInterval,jdbcType=INTEGER},",
        "notice_status = #{noticeStatus,jdbcType=BIT},",
        "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
        "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
        "delete_status = #{deleteStatus,jdbcType=BIT},",
        "sql_text = #{sqlText,jdbcType=LONGVARCHAR},",
        "notice_extend = #{noticeExtend,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(NoticeDO record);
}