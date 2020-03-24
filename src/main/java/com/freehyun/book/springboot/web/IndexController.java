package com.freehyun.book.springboot.web;

import com.freehyun.book.springboot.config.auth.LoginUser;
import com.freehyun.book.springboot.config.auth.dto.SessionUser;
import com.freehyun.book.springboot.service.PostsService;
import com.freehyun.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")                //@LoginUser SessionUser user : 기존에 (User) httpSession.getAttribute("user") 로 가져오던 세션 정보 값이 개선, 이제는 어트 컨트롤러든지 @LoginUser 만 사용하면 세션 정보를 가져올 수 있게 되었다.
    public String index(Model model, @LoginUser SessionUser user) { //Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장, 여기서는 postsService.findAllDesc()로 가져온 결과를 posts 로 index.mustache 에 전달
        model.addAttribute("posts", postsService.findAllDesc());

        //SessionUser user = (SessionUser) httpSession.getAttribute("user"); //앞서 작성된 CustomOAuth2UserService 에서 로그인 성공 시 세션에 SessionUser 를 저장. 즉, 로그인 성공 시 httpSession.getAttribute("user") 에서 값을 가져올 수 있다.

        if (user != null) { //세션에 저장된 값이 있을 때만 model 에 userName 으로 등록. 세션에 저장된 값이 없으면 model 엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 된다.
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
