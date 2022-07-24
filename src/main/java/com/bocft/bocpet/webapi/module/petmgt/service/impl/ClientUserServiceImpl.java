package com.bocft.bocpet.webapi.module.petmgt.service.impl;

import com.bocft.bocpet.webapi.module.petmgt.entity.ClientUser;
import com.bocft.bocpet.webapi.module.petmgt.mapper.ClientUserMapper;
import com.bocft.bocpet.webapi.module.petmgt.service.ClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @create 2022-07-25 2:34
 */
@Service
public class ClientUserServiceImpl implements ClientUserService {
    @Autowired
    ClientUserMapper clientUserMapper;

    @Override
    public int insertClientUser(ClientUser clientUser) {
        return clientUserMapper.insertClientUser(clientUser);
    }
}
