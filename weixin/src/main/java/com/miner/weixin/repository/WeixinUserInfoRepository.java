package com.miner.weixin.repository;

import com.miner.weixin.dataobject.WeixinUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hongf
 * @create 2018-02-04 14:22
 * @desc
 **/

public interface WeixinUserInfoRepository extends JpaRepository<WeixinUserInfo,String>{

}
