package com.subnit.fastnotice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

/**
 * description:
 *
 * @author subo177693
 * @date : create in 21:02 2019/12/22
 */
@Mapper
public interface NoticeDao {

    @Select("select count(*) from notice")
    Integer findByName();

}



