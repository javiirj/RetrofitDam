package com.javirj.retrofitdam.api;

import com.javirj.retrofitdam.model.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Javi on 12/02/2018.
 */

public interface PostService {
    @POST("/posts")
    public Call<Post> newPost(@Body Post newPost);
}
