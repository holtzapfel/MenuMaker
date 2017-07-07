package com.studios.holtzapfel.menumakerexample;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.studios.holtzapfel.menumaker.MMActivity;
import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.MMMenuBuilder;
import com.studios.holtzapfel.menumaker.MMPage;
import com.studios.holtzapfel.menumaker.MMPageBuilder;
import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;

public class BasicMenuActivity extends MMActivity {

    // Unique IDs to associate with pages and menu items
    private static final int PAGE_MAIN = 100;
    private static final int ID_LINK1 = 101;
    private static final int ID_LINK2 = 102;
    private static final int ID_LINK3 = 103;

    private MMMenu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public MMMenu onRequestMenu() {
        if (mMenu == null){
            mMenu = buildMenu();
        }
        return mMenu;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // VERY IMPORTANT! If this is not called, the menu will not be created
        // For more complex menus, call this function **after** all data required to create
        // menu items has been retrieved
        startMenu();
    }

    private MMMenu buildMenu(){
        // Create a menu item
        BodyMenuItem itemLink1 = new BodyMenuItem(ID_LINK1)
                .withTitle("Link 1")
                .withDescription("This is a description for Link 1");

        // Create a Page
        MMPage pageMain = new MMPageBuilder(PAGE_MAIN)
                .withPageTitle("My Links")
                .withMenuItems( // Items can be created as objects and inserted or created inline
                        itemLink1,
                        new BodyMenuItem(ID_LINK2).withTitle("Link 2").withDescription("This is a description for Link 2"),
                        new BodyMenuItem(ID_LINK3).withTitle("Link 3").withDescription("This is a description for Link 3")
                )
                .build();

        // Compile all pages into MMMenuBuilder and then build()
        // Only one page is used in this example
        return new MMMenuBuilder(BasicMenuActivity.this)
                .withFrameLayout(R.id.basic_menu_frame)   // Layout ID must reference to a FrameLayout
                .withPages(pageMain)                            // Include created pages
                .withInitialPageID(PAGE_MAIN)                   // Needed more for menus with multiple pages
                .withOnMenuItemClickListener(new MMMenu.OnMenuItemClickListener() {
                    @Override
                    public void onBodyItemClick(BodyMenuItem bodyMenuItem) {
                        switch (bodyMenuItem.getID()){
                            case ID_LINK1:
                                // TODO perform click action
                                Toast.makeText(BasicMenuActivity.this, bodyMenuItem.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            case ID_LINK2:
                                // TODO perform click action
                                Toast.makeText(BasicMenuActivity.this, bodyMenuItem.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            case ID_LINK3:
                                // TODO perform click action
                                Toast.makeText(BasicMenuActivity.this, bodyMenuItem.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

                    @Override
                    public void onHeaderItemClick(HeaderMenuItem headerMenuItem) {

                    }
                })
                .build();                                       // build() prepares and creates MMMenu object
    }
}
