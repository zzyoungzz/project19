package filesource.project16.controller;

import filesource.project16.domain.UserForm;
import filesource.project16.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;




    @GetMapping("/signup")  //(파라미터)로 html에바운딩
    public String signup(UserForm userForm) {
        return "user/signup";
    }


    @PostMapping("/signup")
    public String signup(@Valid UserForm userForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
        //UserForm오류적용 (password 미입력 등)
            return "user/signup";
        }

        try {
            userService.save(userForm.getUsername(), userForm.getPassword1());
        }
        catch(DataIntegrityViolationException e){        //중복시 오류 DataIntegrityViolationException이 발생
            e.printStackTrace();
            bindingResult.reject("signup_Failed", "이미 등록된 사용자, 이메일, 전화번호 입니다.");

            return "user/signup";
        }


        return "main";
    }





    @GetMapping("/login")
    public String login() {
        return "user/login";
    }


}
