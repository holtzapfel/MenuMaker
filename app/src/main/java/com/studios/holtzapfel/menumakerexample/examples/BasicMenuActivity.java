package com.studios.holtzapfel.menumakerexample.examples;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

import com.studios.holtzapfel.menumaker.MMActivity;
import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.MMMenuBuilder;
import com.studios.holtzapfel.menumaker.MMPage;
import com.studios.holtzapfel.menumaker.MMPageBuilder;
import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumakerexample.R;

/**
 * A very basic menu
 */
public class BasicMenuActivity extends MMActivity {

    /**
     * While creating static final int variables is not required, handling click listeners is
     * significantly easier and you will likely run into less issues
     */
    private static final int PAGE_MAIN = 100;
    private static final int ID_LINK1 = 101;
    private static final int ID_LINK2 = 102;
    private static final int ID_LINK3 = 103;

    private MMMenu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * This is periodically called by MMActivity and MMFragment to ensure an updated MMMenu is used
     * Add a statement like this...
     *
     * if (mMenu == null){
     *     // build and store menu
     * }
     *
     * ...so that the menu can be dynamic.  If mMenu is always recreated, user inputted info will
     * likely be reverted
     *
     * NOTE: This function is not called until startMenu() is called.
     */
    @Override
    public MMMenu onRequestMenu() {
        if (mMenu == null){
            mMenu = buildMenu();
        }
        return mMenu;
    }

    /**
     * I prefer to call startMenu() here, but it can be called at any time.
     *
     * VERY IMPORTANT: If this is not called, the menu will not be created.
     *
     * NOTE: If data for menu values needs loaded, pulled, etc., ensure data is loaded before calling startMenu()
     */
    @Override
    protected void onResume() {
        super.onResume();
        startMenu();
    }

    /**
     * A function that builds and returns a new MMMenu
     */
    private MMMenu buildMenu(){
        // Create a menu item
        BodyMenuItem itemLink1 = new BodyMenuItem(ID_LINK1)
                .withTitle("Link 1")
                .withDescription("This is a description for Link 1");

        // Create a Page
        MMPage pageMain = new MMPageBuilder(PAGE_MAIN)
                .withPageTitle("Basic Menu")
                .withMenuItems( // Items can be created as objects and inserted or created inline
                        itemLink1,
                        new BodyMenuItem(ID_LINK2).withTitle("Link 2").withDescription("This is a description for Link 2"),
                        new BodyMenuItem(ID_LINK3).withTitle("Link 3").withDescription("This is a description for Link 3")
                )
                .build();

        // Compile all pages into MMMenuBuilder and then build()
        // Only one page is used in this example
        return new MMMenuBuilder(BasicMenuActivity.this)
                .withFrameLayout(R.id.frame)                        // FrameLayout to display menu
                .withPages(pageMain)                                // Insert created pages
                .withInitialPageID(PAGE_MAIN)                       // Sets page to be displayed first
                .withOnMenuItemClickListener(buildClickListener())  // Insert click listener
                .build();                                           // Prepares and creates MMMenu object
    }

    private MMMenu.OnMenuItemClickListener buildClickListener(){
        return new MMMenu.OnMenuItemClickListener() {
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
        };
    }
}
