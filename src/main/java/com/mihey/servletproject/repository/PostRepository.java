package com.mihey.servletproject.repository;

import com.mihey.servletproject.model.Post;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Integer> {
    List<Post> getPostsByUserId(Integer id);
}
