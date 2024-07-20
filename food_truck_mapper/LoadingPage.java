package dev.lab.food_truck_mapper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

    public class LoadingPage extends AppCompatActivity {

        ImageView imageView;
        TextView textView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.loading_page);

            imageView = (ImageView)findViewById(R.id.imageView);
            textView = (TextView)findViewById(R.id.textView);

            imageView.animate().alpha(0f).setDuration(0);
            textView.animate().alpha(0f).setDuration(0);

            imageView.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                /**
                 * {@inheritDoc}
                 *
                 * @param animation The animation which reached its end.
                 */

                @Override
                public void onAnimationEnd(Animator animation) {
                    textView.animate().alpha(1f).setDuration(800);

                }
            });
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(LoadingPage.this,MainMenu.class);
                    startActivity(intent);
                    finish();
                }
            },3000);
        }
    }

