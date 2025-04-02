package com.simple.basic.memoservice;

import com.simple.basic.command.TestVO;

import java.util.List;

public interface MemoService {

    int Write(TestVO vo);

    List<TestVO> getList();

}
