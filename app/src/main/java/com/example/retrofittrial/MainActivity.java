package com.example.retrofittrial;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofittrial.api.interfaces.JSONPlaceholderapi;
import com.example.retrofittrial.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textresult1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textresult1 = findViewById(R.id.textresult);
createPost();
    }
void createPost(){
       Post post = new Post(89,"sample data","body data");

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JSONPlaceholderapi jsonPlaceholderapi = retrofit.create(JSONPlaceholderapi.class);
    Call<Post> call =  jsonPlaceholderapi.createPost(post);

    call.enqueue(new Callback<Post>() {
        @Override
        public void onResponse(Call<Post> call, Response<Post> response) {
            if(!response.isSuccessful()){
                textresult1.setText("Code: "+response.code());
                return;
            }
            Post postResponse = response.body();


            String content = "";
            content += "Code "+ response.code()+"\n";
            content += "ID "+ postResponse.getId()+"\n";
            content += "User ID "+ postResponse.getUserId()+"\n";
            content += "Title "+ postResponse.getTitle()+"\n";
            content += "Text "+ postResponse.getText()+"\n\n";
            textresult1.setText(content);
        }

        @Override
        public void onFailure(Call<Post> call, Throwable t) {
            textresult1.setText(t.getMessage());
        }
    });

    }
    public void getPosts(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceholderapi jsonPlaceholderapi = retrofit.create(JSONPlaceholderapi.class);
        Call<List<Post>> call =  jsonPlaceholderapi.getposts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textresult1.setText("Code: "+response.code());
                    return;
                }
                List<Post> posts =response.body();
                for(Post post:posts){

                    String content = "";
                    content += "ID "+ post.getId()+"\n";
                    content += "User ID "+ post.getUserId()+"\n";
                    content += "Title "+ post.getTitle()+"\n";
                    content += "Text "+ post.getText()+"\n\n";
                    textresult1.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textresult1.setText(t.getMessage());
                textresult1.setText("i am not working");
            }
        });
    }
}