package com.hu.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hu.bookstore.entity.Book;
import com.hu.bookstore.entity.Goods;
import com.hu.bookstore.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface BookMapper extends BaseMapper<Book> {


    List<Book> getBookList(@Param("begin") int begin, @Param("size") int size,
                           @Param("condition") Map<String, Object> condition);

    int getTotal(@Param("condition") Map<String, Object> condition);

    List<Book> getBookListByTrolley(@Param("trolleyId") String trolleyId);

    List<Book> getBookListByOrder(@Param("orderId") String orderId);

    void addSales(@Param("bookId") String bookId);

    void addView(@Param("bookId") String bookId);

    List<Book> getSimilarBook(@Param("type") String type);

    List<Book> getNewBook(@Param("num")int num);

    List<Book> getHotBook(@Param("num")int num);

    List<Book> getRecommendedBook(@Param("num")int num);

    List<GoodsVO> getBookByCart(@Param("id")String id);
}
