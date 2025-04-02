package com.simple.basic.memomapper;

import com.simple.basic.command.TestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoMapper {

    String getTime();

    int Write(TestVO vo);

    List<TestVO> getList();
}
