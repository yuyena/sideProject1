package hello.hellospring.controller;

import hello.hellospring.dto.UserRegisterDto;
import hello.hellospring.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        return "register"; // templates/register.html
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute("userRegisterDto") UserRegisterDto dto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            userService.register(dto);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    // 아이디 중복 체크 API (Ajax)
    @GetMapping("/check-username")
    @ResponseBody
    public boolean checkUsername(@RequestParam String username) {
        return userService.checkUsernameDuplicate(username);
    }

    // 닉네임 중복 체크 API (Ajax)
    @GetMapping("/check-nickname")
    @ResponseBody
    public boolean checkNickname(@RequestParam String nickname) {
        return userService.checkNicknameDuplicate(nickname);
    }
}
