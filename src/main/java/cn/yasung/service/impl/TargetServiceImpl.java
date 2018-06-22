package cn.yasung.service.impl;


import cn.yasung.constants.APIResponseCodeEnum;
import cn.yasung.exception.WeChatAPIBizException;
import cn.yasung.mapper.IntegralMapper;
import cn.yasung.mapper.TargetMapper;
import cn.yasung.model.Integral;
import cn.yasung.model.Target;
import cn.yasung.service.IntegralService;
import cn.yasung.service.TargetService;
import cn.yasung.vo.IntegralVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.yasung.constants.Constant.SYSTEM_PAGENUM;

/**
 * Project Name:
 * 功能描述：
 *
 * @author Ayang@unmz.net
 * @version 1.0
 * @date 2018-6-19 13:34
 * @since JDK 1.8
 */
@Service
public class TargetServiceImpl implements TargetService {

    @Autowired
    private TargetMapper targetMapper;





    @Override
    public void addTarget(Target target)throws WeChatAPIBizException  {
        try {
            if (target!=null){
                targetMapper.addTarget(target);

            }else {
                throw new WeChatAPIBizException(APIResponseCodeEnum.REQUIRED_PARAM_EMPTY.getCode());
            }

        }catch (WeChatAPIBizException e){
            throw e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }

    }






    @Override
    public List<Target> getListTarget()throws WeChatAPIBizException {

        try {

            List<Target>   targetList  = targetMapper.getListTarget();


            return targetList;
        }catch (WeChatAPIBizException e){
            throw  e;
        }catch (Exception e){
            throw  new WeChatAPIBizException(APIResponseCodeEnum.SYSTEM_ERROR.getCode());
        }
    }


}
