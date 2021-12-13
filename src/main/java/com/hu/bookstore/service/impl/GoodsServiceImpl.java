package com.hu.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.bookstore.entity.Book;
import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.entity.Order;
import com.hu.bookstore.mapper.GoodsMapper;
import com.hu.bookstore.mapper.TrolleyMapper;
import com.hu.bookstore.service.BookService;
import com.hu.bookstore.service.GoodsService;
import com.hu.bookstore.service.SysUserService;
import com.hu.bookstore.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author suhu
 * @createDate 2021-11-05
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    private TrolleyMapper trolleyMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private BookService bookService;

    @Override
    public void addGoods(Goods goods) {
        goodsMapper.insert(goods);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsMapper.updateById(goods);
    }

    @Override
    public void setOrderId(String goodsId, String orderId) {
        goodsMapper.setOrderId(goodsId, orderId);
    }

    @Override
    public void setAllOrderId(String trolleyId, String orderId) {
        goodsMapper.setAllOrderId(trolleyId, orderId);
    }

    @Override
    public List<Goods> getOrderGoods(String id) {
        return goodsMapper.getGoodsByOrder(id);
    }

    @Override
    public GoodsVO transformGoodsVO(Goods goods) {
        GoodsVO goodsVO = new GoodsVO();
        Book book = bookService.getById(goods.getBookId());
        goodsVO.setAuthor(book.getAuthor());
        goodsVO.setBookId(book.getId());
        goodsVO.setBookName(book.getName());
        goodsVO.setCount(goods.getCount());
        goodsVO.setPress(book.getPress());
        goodsVO.setPrice(book.getDiscountPrice());
        return goodsVO;
    }



}
