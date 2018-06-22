package cn.yasung.service.impl;


import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.exception.WeChatAPIBizException;
import cn.yasung.mapper.IntegralMapper;
import cn.yasung.mapper.UserMapper;
import cn.yasung.model.Integral;
import cn.yasung.model.User;
import cn.yasung.service.IntegralService;
import cn.yasung.service.UserService;
import cn.yasung.utils.MD5Util;
import cn.yasung.vo.IntegralVo;
import cn.yasung.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.yasung.constants.Constant.SYSTEM_PAGENUM;

/**
 * Project Name:
 * 功能描述：
 *
 * @author Ayang@unmz.net
 * @version 1.0
 * @date 2018-6-15 13:34
 * @since JDK 1.8
 */
@Service
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    private IntegralMapper integralMapper;

    @Override
    public PageInfo<Integral> getListIntegral(IntegralVo integralVo)throws WeChatAPIBizException {

        try {
            Integer pageSize = SYSTEM_PAGENUM;

            if (integralVo.getPageNum()==null || integralVo.getPageNum()==0 || integralVo.getPageNum()<0){
                integralVo.setPageNum(1);
            }
            PageHelper.startPage(integralVo.getPageNum(), pageSize);
            List<Integral>   integralList;
            if (integralVo.getChampion().equals("0")){
                  integralList  = integralMapper.getListDayInteger();
            }else if (integralVo.getChampion().equals("1")){
                integralList  = integralMapper.getListMonthInteger();
            }else {
                  integralList = integralMapper.getListYearInteger();
            }

            PageInfo<Integral> pageInfo = new PageInfo<Integral>(integralList);

            return pageInfo;
        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }
    }



}
