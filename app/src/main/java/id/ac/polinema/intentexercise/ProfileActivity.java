package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private TextView hasilFullname, hasilEmail, hasilHomepage, hasilAbout;
    private CircleImageView gambar;

    private Button bukaURL;


    private Intent pindah;
    private Uri imageUri;

    private static final String TAG = RegisterActivity.class.getCanonicalName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bukaURL = findViewById(R.id.button_homepage);


        String fullname = getIntent().getExtras().getString("fullname");
        String email = getIntent().getExtras().getString("email");
        final String homepage = getIntent().getExtras().getString("homepage");
        String about = getIntent().getExtras().getString("about");
        pindah = getIntent();

        gambar = findViewById(R.id.image_profile);
        hasilFullname = findViewById(R.id.label_fullname);
        hasilEmail = findViewById(R.id.label_email);
        hasilHomepage = findViewById(R.id.label_homepage);
        hasilAbout = findViewById(R.id.label_about);

        hasilFullname.setText(fullname);
        hasilEmail.setText(email);
        hasilHomepage.setText(homepage);
        hasilAbout.setText(about);

        if(pindah.hasExtra("gambar")) {
            imageUri = pindah.getParcelableExtra("gambar");
            try {
                Bitmap avatarBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                gambar.setImageBitmap(avatarBitmap);
            } catch (IOException e) {
                Toast.makeText(ProfileActivity.this, "Tak bisa muat gambar", Toast.LENGTH_SHORT).show();
                Log.e(TAG, e.getMessage());
            }
        }


        bukaURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://" + homepage;

                Intent bukaurl = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(bukaurl);
            }
        });


    }
}
