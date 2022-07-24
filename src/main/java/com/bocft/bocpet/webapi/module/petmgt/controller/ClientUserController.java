package com.bocft.bocpet.webapi.module.petmgt.controller;

import com.bocft.bocpet.webapi.module.petmgt.entity.ClientUser;
import com.bocft.bocpet.webapi.module.petmgt.service.ClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas
 * @create 2022-07-25 2:35
 */
@RestController
@RequestMapping("/clientUser")
public class ClientUserController {
    @Autowired
    ClientUserService clientUserService;

    @RequestMapping("/insertClientUser")
    public int insertClientUser(ClientUser clientUser) {
        return clientUserService.insertClientUser(clientUser);
    }
}
