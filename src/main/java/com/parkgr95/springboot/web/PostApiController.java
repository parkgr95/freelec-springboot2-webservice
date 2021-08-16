package com.parkgr95.springboot.web;

import com.parkgr95.springboot.service.posts.PostService;
import com.parkgr95.springboot.web.dto.PostsResponseDto;
import com.parkgr95.springboot.web.dto.PostsSaveRequestDto;
import com.parkgr95.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postsService;

    @PostMapping("/api/v1/posts") //등록
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}") //조회
    public PostsResponseDto findbyID (@PathVariable Long id) {
        return postsService.finById(id);
    }
}
