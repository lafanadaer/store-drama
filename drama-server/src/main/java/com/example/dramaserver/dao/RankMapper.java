package com.example.dramaserver.dao;

import com.example.dramaserver.domain.Rank;
import org.springframework.stereotype.Repository;

@Repository
public interface RankMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank
     *
     * @mbg.generated
     */
    int insert(Rank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank
     *
     * @mbg.generated
     */
    int insertSelective(Rank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank
     *
     * @mbg.generated
     */
    Rank selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Rank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rank
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Rank record);

    /**
     * 查总分
     * @param dramaId
     * @return
     */
    int selectScoreSum(Long dramaId);

    /**
     * 查总评分人数
     * @param dramaId
     * @return
     */
    int selectRankNum(Long dramaId);
}