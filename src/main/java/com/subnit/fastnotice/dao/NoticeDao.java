package com.subnit.fastnotice.dao;

import java.util.List;

import com.subnit.fastnotice.dto.NoticeDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface NoticeDao {
    @Delete({
        "delete from notice",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into notice ( db, name, ",
        "interval, gmt_modified, ",
        "gmt_create, sql, ",
        "email, ding_webhook, ",
        "extend)",
        "values (#{db,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{interval,jdbcType=VARCHAR}, #{gmtModified,jdbcType=TIMESTAMP}, ",
        "#{gmtCreate,jdbcType=TIMESTAMP}, #{sql,jdbcType=LONGVARCHAR}, ",
        "#{email,jdbcType=LONGVARCHAR}, #{dingWebhook,jdbcType=LONGVARCHAR}, ",
        "#{extend,jdbcType=LONGVARCHAR})"
    })
    int insert(NoticeDO record);

    @Select({
        "select",
        "id, db, name, interval, gmt_modified, gmt_create, sql, email, ding_webhook, ",
        "extend",
        "from notice",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="db", property="db", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="interval", property="interval", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sql", property="sql", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="ding_webhook", property="dingWebhook", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="extend", property="extend", jdbcType=JdbcType.LONGVARCHAR)
    })
    NoticeDO selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, db, name, interval, gmt_modified, gmt_create, sql, email, ding_webhook, ",
        "extend",
        "from notice"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="db", property="db", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="interval", property="interval", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sql", property="sql", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="ding_webhook", property="dingWebhook", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="extend", property="extend", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<NoticeDO> selectAll();

    @Update({
        "update notice",
        "set db = #{db,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "interval = #{interval,jdbcType=VARCHAR},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "sql = #{sql,jdbcType=LONGVARCHAR},",
          "email = #{email,jdbcType=LONGVARCHAR},",
          "ding_webhook = #{dingWebhook,jdbcType=LONGVARCHAR},",
          "extend = #{extend,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(NoticeDO record);

    @Select("select count(*) from notice")
    Integer findByName();
}