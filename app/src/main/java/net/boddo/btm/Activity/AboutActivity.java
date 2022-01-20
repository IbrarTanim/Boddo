package net.boddo.btm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import net.boddo.btm.Fragment.ProfileFragment;
import net.boddo.btm.Fragment.ProfileOneFragment;
import net.boddo.btm.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutActivity extends AppCompatActivity {

    private Button sadiaEmailBtn,ranaEmailBtn;
    private CircleImageView fbAboutActivity,linkedinAboutActivity;
    private ImageView ivProfileAboutActivity;
    private AboutActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        activity = this;
        sadiaEmailBtn = findViewById(R.id.sadiaEmailBtn);
        ranaEmailBtn = findViewById(R.id.ranaEmailBtn);
        fbAboutActivity = findViewById(R.id.fbAboutActivity);
        linkedinAboutActivity = findViewById(R.id.linkedinAboutActivity);
        ivProfileAboutActivity = findViewById(R.id.ivProfileAboutActivity);

        ivProfileAboutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, new ProfileFragment());
                fragmentTransaction.commit();*/
            }
        });

        sadiaEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:sadia@boddo.net"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] {"sadia@boddo.net"});

                try{
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                   // Log.e("Finished sending email...", "");
                }catch (Exception e){
                    Toast.makeText(AboutActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ranaEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:shopon@boddo.net"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] {"shopon@boddo.net"});

                try{
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    // Log.e("Finished sending email...", "");
                }catch (Exception e){
                    Toast.makeText(AboutActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
            
        });

        fbAboutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "https://www.facebook.com/SarwarBadhon");
                startActivity(Intent.createChooser(intent, "Share with"));

                /*Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(
                        "https://www.facebook.com/SarwarBadhon"));
                startActivity(browserIntent);*/
            }
        });

        linkedinAboutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserLinkedIn = new Intent(Intent.ACTION_VIEW,Uri.parse(
                        "https://www.linkedin.com/signup/cold-join?trk=guest_homepage-basic_nav-header-join"));
                startActivity(browserLinkedIn);
            }
        });
    }
}