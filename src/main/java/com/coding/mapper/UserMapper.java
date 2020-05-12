package com.coding.mapper;

import com.coding.domain.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
@Repository
public interface UserMapper extends Mapper<User>  {

}
