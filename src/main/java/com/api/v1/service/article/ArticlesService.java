package com.api.v1.service.article;

import com.api.v1.model.dto.response.ArticleResponseDTO;

import java.io.IOException;
import java.util.HashMap;

public interface ArticlesService {

    ArticleResponseDTO getArticles(HashMap<String, String> params) throws IOException;
}
