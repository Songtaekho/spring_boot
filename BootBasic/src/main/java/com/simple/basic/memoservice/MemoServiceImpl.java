package com.simple.basic.memoservice;

import com.simple.basic.command.TestVO;
import com.simple.basic.memomapper.MemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memoService")
public class MemoServiceImpl implements MemoService {

    @Autowired
    private MemoMapper memoMapper;

    @Override
    public int Write(TestVO vo) {

        return memoMapper.Write(vo);
    }

    @Override
    public List<TestVO> getList() {
        return memoMapper.getList(); // Mapper 호출
    }

    //매퍼 오토와이어드
}
