package com.alexspataru.watchout;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

public class StartActivity extends AppCompatActivity {


    ImageButton play, more, shop, info;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //full screnn

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //animated gradient background on Main Menu screen
        RelativeLayout relativeLayout = (RelativeLayout) findViewById( R.id.layout_start_menu );
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration( 2000 );
        animationDrawable.setExitFadeDuration( 2000 );
        animationDrawable.start();

        //buttons
        play = (ImageButton) findViewById(R.id.play);
        more = (ImageButton) findViewById(R.id.more);
        shop = (ImageButton) findViewById(R.id.shop);
        info = (ImageButton) findViewById( R.id.info ) ;

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        info.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( StartActivity.this,InfoActivity.class ));
                finish();
            }
        } );

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, ShopActivity.class));
                finish();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(StartActivity.this, more);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        Intent intent, chooser;
                        int id = menuItem.getItemId();
                        if (id == R.id.feedback){
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setData( Uri.parse("mailto:"));
                            String[] to = {"pikapps10@gmail.com"}; // my email
                            intent.putExtra(Intent.EXTRA_EMAIL, to);
                            intent.setType("message/rfc822");
                            chooser = Intent.createChooser(intent, "Send Feedback");
                            startActivity(chooser);
                        }

                        if (id == R.id.share){
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Vermiculi");
                            String sAux = "\n Let me recommend you this Game \n\n";
                            sAux = sAux+ "https://play.google.com/store/apps/details?id=com.angrymonkey.angrymonkey \n\n";
                            intent.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(intent, "Share"));
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case  KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return  super.dispatchKeyEvent(event);
    }
}
