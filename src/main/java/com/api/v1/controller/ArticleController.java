package com.api.v1.controller;

import com.api.v1.model.dto.response.ArticleResponseDTO;
import com.api.v1.service.article.ArticlesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticlesService articlesService;

    public ArticleController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<ArticleResponseDTO> getArticles(@RequestParam HashMap<String,String> params){
        try {
            return ResponseEntity.ok(articlesService.getArticles(params));
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to load database", e);
        }
    }
}
