package com.bocft.bocpet.webapi.module.petmgt.service;

import com.bocft.bocpet.webapi.module.petmgt.entity.ClientUser;
import com.bocft.bocpet.webapi.module.sysmgt.entity.User;

/**
 * @author Lucas
 * @create 2022-07-25 2:33
 */
public interface ClientUserService {
    int insertClientUser(ClientUser clientUser);
}
