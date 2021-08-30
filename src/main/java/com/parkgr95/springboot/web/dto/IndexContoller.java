package com.parkgr95.springboot.web.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexContoller {

    @GetMapping("/")
    public String index() {
        return "index"; // 머스테치 스타터 덕분에 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됨.
        // src/main/resourcs/templates/index.mustache로 전환되어 View Resolver가 처리하게 된다.
    }
}
