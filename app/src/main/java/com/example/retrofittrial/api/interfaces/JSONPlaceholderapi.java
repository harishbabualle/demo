package com.example.retrofittrial.api.interfaces;

import com.example.retrofittrial.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JSONPlaceholderapi {
    @GET("posts")
    Call<List<Post>> getposts();

    @POST("posts")
    Call<Post> createPost(@Body Post post);
}
