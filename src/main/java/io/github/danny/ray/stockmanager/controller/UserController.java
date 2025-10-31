package io.github.danny.ray.stockmanager.controller;

import io.github.danny.ray.lib.response.dto.BaseResult;
import io.github.danny.ray.stockmanager.dto.user.RegisterDto;
import io.github.danny.ray.stockmanager.dto.user.UserInfoDto;
import io.github.danny.ray.stockmanager.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用者
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 註冊
     *
     * @param dto 使用者資訊
     * @return 使用者資訊
     */
    @PostMapping("/register")
    public BaseResult<UserInfoDto> register(@RequestBody RegisterDto dto) {
        return BaseResult.ok(userService.register(dto));
    }

}
