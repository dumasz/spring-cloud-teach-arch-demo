package com.dumas.scta.service.call.dubbo.sentinel.provider.service;

import com.dumas.scta.service.call.dubbo.sentinel.api.api.UserService;
import com.dumas.scta.service.call.dubbo.sentinel.api.dto.UserAddDTO;
import com.dumas.scta.service.call.dubbo.sentinel.api.dto.UserDTO;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author dumas
 * @desc TODO:
 * @date 2022/05/03 10:58 PM
 */
@Service(protocol = "dubbo", version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1);
    }

    @Override
    public Integer add(UserAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
