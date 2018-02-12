package com.javirj.retrofitdam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.javirj.retrofitdam.api.PostService;
import com.javirj.retrofitdam.model.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText userId, title, body;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = findViewById(R.id.editTextUserId);
        title = findViewById(R.id.editTextTitle);
        body = findViewById(R.id.editTextBody);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post();

                post.setUserId(Integer.parseInt(userId.getText().toString()));
                post.setTitle(title.getText().toString());
                post.setBody(body.getText().toString());

                sendNetworkRequest(post);
            }
        });
    }

    private void sendNetworkRequest(Post post) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        PostService service = retrofit.create(PostService.class);
        Call<Post> call = service.newPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(MainActivity.this, "Post created !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
