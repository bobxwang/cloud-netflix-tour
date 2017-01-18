package com.bob.cloud.netflix.apigateway.controller;

import com.bob.cloud.netflix.apigateway.entity.UserBankEntity;
import com.bob.cloud.netflix.apigateway.integration.BankClient;
import com.bob.cloud.netflix.apigateway.integration.UserClient;
import com.bob.cloud.netflix.apigateway.integration.dto.BankDTO;
import com.bob.cloud.netflix.apigateway.integration.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserBankController {

    @Autowired
    private UserClient userClient;

    @Autowired
    @Qualifier("bankClientWithHystrix")
    private BankClient bankClient;

    /**
     * 获取一个用户下的所有银行信息
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/users/{userId}/banks", method =
            RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBankEntity userBanks(@PathVariable("userId") String userId) {

        ResponseEntity<List<UserDTO>> userrs = userClient.getAllUsers();
        List<UserDTO> users = userrs.getBody();
        boolean userIdCanFound = false;
        String userName = null;
        for (UserDTO user : users) {
            if (user.getId() == Long.valueOf(userId)) {
                userIdCanFound = true;
                userName = user.getName();
                break;
            }
        }
        if (!userIdCanFound) {
            throw new RuntimeException("can't find the given usrid");
        }
        List<BankDTO> banks = bankClient.allBanks();
        UserBankEntity userBankEntity = new UserBankEntity();
        userBankEntity.setUserName(userName);
        userBankEntity.setUserId(Long.valueOf(userId));
        userBankEntity.setBanks(banks);
        return userBankEntity;
    }
}