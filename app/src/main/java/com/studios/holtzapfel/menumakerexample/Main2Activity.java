package com.studios.holtzapfel.menumakerexample;

import android.os.Bundle;

import com.studios.holtzapfel.menumaker.MMActivity;
import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.MMMenuBuilder;
import com.studios.holtzapfel.menumaker.MMPageBuilder;
import com.studios.holtzapfel.menumaker.model.BodyMenuItem;

public class Main2Activity extends MMActivity {

    MMMenu mMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMenu();
    }

    @Override
    public MMMenu onRequestMenu() {
        if (mMenu == null){
            mMenu = new MMMenuBuilder(this)
                    .withFrameLayout(R.id.menu_frame)
                    .withSlidingAnimation(true)
                    .withPages(
                            new MMPageBuilder(10)
                                    .withPageTitle("Activity Extending MMA")
                                    .withMenuItems(
                                            new BodyMenuItem(11).withTitle("Item 1"),
                                            new BodyMenuItem(12).withTitle("Item 2"),
                                            new BodyMenuItem(13).withTitle("Item 3")
                                    )
                                    .build()
                    )
                    .build();
        }
        return mMenu;
    }
}
