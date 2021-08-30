package com.parkgr95.springboot.web;

import com.parkgr95.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexContoller {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index"; // 머스테치 스타터 덕분에 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됨.
        // src/main/resourcs/templates/index.mustache로 전환되어 View Resolver가 처리하게 된다.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
