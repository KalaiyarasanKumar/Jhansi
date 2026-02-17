package com.example.jhansiasafetyqueen;

import static com.example.jhansiasafetyqueen.R.*;
import static com.example.jhansiasafetyqueen.R.id.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawers;
    View headerViw1;
    CircleImageView admin_nav_image;
    private TextView nav_name;
    private TextView nav_email;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    public String resulturl="";
    private DatabaseReference databaseReference1;
    private ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Firebase Initialization

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser= firebaseAuth.getCurrentUser();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users").child(firebaseAuth.getCurrentUser().getUid());
        databaseReference.keepSynced(true);


        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        drawers = findViewById(id.drawer_layout);

        NavigationView navigationView = findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Drawer


        headerViw1=navigationView.getHeaderView(0);
        nav_name=(TextView)headerViw1.findViewById(id.username);
        nav_email=(TextView)headerViw1.findViewById(id.usermail);
        admin_nav_image=headerViw1.findViewById(id.imageView);

        navigationView.setNavigationItemSelectedListener(this);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Model member = dataSnapshot.getValue(Model.class);
                nav_name.setText(member.getName());
                nav_email.setText(member.getEmail());
                Glide.with(MainScreen.this)
                        .load(member.getImageurl())
                        .into(admin_nav_image);

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

                Toast.makeText(MainScreen.this, "Retrieve Failed !", Toast.LENGTH_SHORT).show();


            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawers, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawers.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(id.nav_home);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (drawers.isDrawerOpen(GravityCompat.START)){
            drawers.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new HomeFragment()).commit();
        } else if (itemId == id.nav_profiles) {
            getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new ProfileFragment()).commit();
        } else if (itemId == id.nav_contacts) {
            getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new ContactFragment()).commit();
        } else if (itemId == id.nav_sos) {
            getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new SOSFragment()).commit();
        } else if (itemId == id.nav_siren) {
            startActivity(new Intent(MainScreen.this, SirensActivity.class));
        } else if (itemId == id.nav_call) {
            startActivity(new Intent(MainScreen.this, FakeCallActivity.class));
        } else if (itemId == id.nav_video) {
            startActivity(new Intent(MainScreen.this, VideoActivity.class));
        } else if (itemId == id.nav_search) {
            startActivity(new Intent(MainScreen.this, SearchActivity.class));
        } else if (itemId == id.nav_privacy) {
            getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new PrivacyFragment()).commit();
        } else if (itemId == id.nav_aboutapp) {
            startActivity(new Intent(MainScreen.this, About_App.class));
        } else if (itemId == id.nav_aboutdeveloper) {
            startActivity(new Intent(MainScreen.this, About_Us.class));
        }
        else if (itemId == nav_record){
            startActivity(new Intent(MainScreen.this, RecordActivity.class));
        }
        else if (itemId == nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainScreen.this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Toast.makeText(MainScreen.this, "User LogOut...", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        drawers.closeDrawer(GravityCompat.START);
        return true;
    }



       /* switch (item.getItemId()){


            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new HomeFragment()).commit();
                break;
            case id.nav_profiles:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new ProfileFragment()).commit();
                break;
            case id.nav_contacts:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new ContactFragment()).commit();
                break;
            case id.nav_sos:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new SOSFragment()).commit();
                break;
            case id.nav_siren:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NotificationFragment()).commit();
                startActivity(new Intent(MainScreen.this, SirensActivity.class));
                break;
            case id.nav_call:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NotificationFragment()).commit();
                startActivity(new Intent(MainScreen.this, FakeCallActivity.class));
                break;
            case id.nav_video:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NotificationFragment()).commit();
                startActivity(new Intent(MainScreen.this, VideoActivity.class));
                break;
            case id.nav_search:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NotificationFragment()).commit();
                startActivity(new Intent(MainScreen.this, SearchActivity.class));
                break;
            case id.nav_privacy:
                getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new PrivacyFragment()).commit();
                break;
            case id.nav_aboutapp:
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingFragment()).commit();
                startActivity(new Intent(MainScreen.this,About_App.class));
                break;
            case id.nav_aboutdeveloper:
                // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingFragment()).commit();
                startActivity(new Intent(MainScreen.this,About_Us.class));
                break;
            case id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainScreen.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(MainScreen.this, "User LogOut...", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
                break;
        }

        drawers.closeDrawer(GravityCompat.START);
        return true;
    }

   /*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.frag_container, home).commit();
                return true;
            case R.id.nav_contacts:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.frag_container, contacts).commit();
                return true;
            case R.id.nav_fav:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.frag_container, profile).commit();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.termscondition :
                startActivity(new Intent(getApplicationContext(),TermsConditions.class));
                return true;

            case R.id.AboutApp :
                startActivity(new Intent(getApplicationContext(),About_App.class));
                return true;

            case R.id.rateApp :
                startActivity(new Intent(getApplicationContext(),Rating_App.class));
                return true;

            case R.id.shareApp :

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plan");
                String shareBody="Click this Link and Download Women Saftey PRO App : https://www.tour2tech.com ";
                String shareSubject = " Download Women Saftey PRO App ";

                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

                startActivity(Intent.createChooser(sharingIntent,"Share Via"));
                return true;

            case R.id.aboutDeveloper :
                startActivity(new Intent(getApplicationContext(),About_Us.class));
                return true;

            case R.id.logout :
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainScreen.this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(MainScreen.this, "User Successfully LogOut..", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            default: return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you Sure you want to Exit ")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        MainScreen.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }

    */


}