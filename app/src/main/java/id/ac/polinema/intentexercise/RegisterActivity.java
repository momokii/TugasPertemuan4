package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private Button buttonMasuk;
    private TextInputEditText fullname, email, password, confir_password, homepage, about;
    private String strPass, strConfirPass;


    private CircleImageView gambar;
    private ImageView buttonGambar;
    private Uri imageUri;

    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        buttonMasuk = findViewById(R.id.button_ok);

        gambar = findViewById(R.id.image_profile);
        buttonGambar = findViewById(R.id.imageView);

        fullname = findViewById(R.id.text_fullname) ;
        email = findViewById(R.id.text_email) ;
        password = findViewById(R.id.text_password) ;
        confir_password = findViewById(R.id.text_confirm_password) ;
        homepage = findViewById(R.id.text_homepage) ;
        about = findViewById(R.id.text_about) ;


        buttonGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);

            }
        });


        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullname.setText(fullname.getText().toString());
                email.setText(email.getText().toString());
                password.setText(password.getText().toString());
                confir_password.setText(confir_password.getText().toString());
                homepage.setText(homepage.getText().toString());
                about.setText(about.getText().toString());

                strPass = password.getText().toString();
                strConfirPass = confir_password.getText().toString();


                if (!Objects.equals(strPass, strConfirPass)){
                    Toast.makeText(RegisterActivity.this, "Password tak cocok", Toast.LENGTH_SHORT).show();
                } else {
                    Intent regisBenar = new Intent(RegisterActivity.this, ProfileActivity.class);

                    regisBenar.putExtra("fullname", fullname.getText().toString());
                    regisBenar.putExtra("email", email.getText().toString());
                    regisBenar.putExtra("homepage", homepage.getText().toString());
                    regisBenar.putExtra("about", about.getText().toString());
                    if(!Objects.equals(imageUri,  null)) {
                        regisBenar.putExtra("gambar", imageUri);
                    }
                    startActivity(regisBenar);
                }

            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( resultCode == RESULT_CANCELED ) {
            Toast.makeText(this, "Cancel Input Image", Toast.LENGTH_SHORT ).show();
        }
        else if(requestCode == GALLERY_REQUEST_CODE){
            if(data != null) {
                try {
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    gambar.setImageBitmap(bitmap);
                }
                catch (IOException e) {
                    Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }

    }
}
