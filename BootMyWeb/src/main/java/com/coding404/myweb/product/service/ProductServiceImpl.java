package com.coding404.myweb.product.service;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.ArrayList;

@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;


    @Override
    public int productRegist(ProductVO vo) {


        return productMapper.productRegist(vo);
    }

    @Override
    public ArrayList<ProductVO> getList(String prodWriter, Criteria cri) {
        return productMapper.getList(prodWriter, cri);
    }

    @Override
    public int getTotal(String prodWriter) {
        return productMapper.getTotal(prodWriter);
    }

    @Override
    public ProductVO getDetail(String prodId) {
        return productMapper.getDetail(prodId);
    }

    @Override
    public void productUpdate(ProductVO vo) {
        productMapper.productUpdate(vo);
    }

    @Override
    public int productDelete(ProductVO vo) {

        return productMapper.productDelete(vo);
    }
}
