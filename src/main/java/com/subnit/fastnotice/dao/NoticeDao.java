package com.subnit.fastnotice.dao;

import java.util.List;

import com.subnit.fastnotice.dto.NoticeDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
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
            "#{noticeStatus,jdbcType=INTEGER}, #{gmtModified,jdbcType=TIMESTAMP}, ",
            "#{gmtCreate,jdbcType=TIMESTAMP}, #{deleteStatus,jdbcType=INTEGER}, ",
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

    @Update({"<script>",
            "update notice",
            "set gmt_modified = now()",
            "<if test='dbName!=null and dbName!=\"\" '> , db_name = #{dbName} </if>",
            "<if test='noticeName!=null and noticeName!=\"\" '> , notice_name = #{noticeName} </if>",
            "<if test='noticeInterval!=null and noticeInterval!=\"\" '> , notice_interval = #{noticeInterval} </if>",
            "<if test='noticeStatus!=null '> , notice_status = #{noticeStatus} </if>",
            "<if test='deleteStatus!=null and deleteStatus!=\"\" '> , delete_status = #{deleteStatus} </if>",
            "<if test='sqlText!=null and dbName!=\"\" '> , sql_text = #{sqlText} </if>",
            "<if test='noticeExtend!=null and noticeExtend!=\"\" '> , notice_extend = #{noticeExtend} </if>",
            "where id = #{id,jdbcType=BIGINT}",
            "</script>"
    })
    int updateByPrimaryKey(NoticeDO record);
}