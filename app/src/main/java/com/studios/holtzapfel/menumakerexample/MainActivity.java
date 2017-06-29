package com.studios.holtzapfel.menumakerexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.studios.holtzapfel.menumaker.MMActivity;
import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.MMMenuBuilder;
import com.studios.holtzapfel.menumaker.MMPage;
import com.studios.holtzapfel.menumaker.MMPageBuilder;
import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.FooterMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;

public class MainActivity extends MMActivity{

    private static final int PAGE_ROOT = 100;
    private static final int ID_ITEM1 = 101;
    private static final int ID_ITEM2 = 102;
    private static final int ID_ITEM3 = 103;
    private static final int ID_ITEM4 = 104;
    private static final int ID_SWITCH1 = 105;
    private static final int ID_SWITCH2 = 106;

    private static final int PAGE_ITEM1 = 200;
    private static final int ID_SWITCH3 = 201;
    private static final int ID_SWITCH4 = 202;
    private static final int ID_SWITCH5 = 203;

    private static final int PAGE_ITEM2 = 300;
    private static final int ID_TEXT_INPUT1 = 301;

    private MMMenu mMenu;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initiateMenu();
    }

    @Override
    public MMMenu onRequestMenu() {
        if (mMenu == null){
            buildMenu();
        }
        return mMenu;
    }

    private void buildMenu(){
        MMPage pageRoot = new MMPageBuilder(PAGE_ROOT)
                .withPageTitle("Menu Maker Example App")
                .withMenuItems(
                        new HeaderMenuItem("General Options"),
                        new BodyMenuItem(ID_ITEM1).withTitle("Item 1").withDescription("This is a sample description").withIconLeft(android.R.mipmap.sym_def_app_icon),
                        new BodyMenuItem(ID_ITEM2).withTitle("Title 2").withDescription("This is a sample description").withIconLeft(android.R.mipmap.sym_def_app_icon),
                        new BodyMenuItem(ID_ITEM3).withTitle("Title 3").withDescription("This is a sample description").withIconLeft(android.R.mipmap.sym_def_app_icon),
                        new BodyMenuItem(ID_ITEM4).withTitle("Title 4").withDescription("This is a sample description").withIconLeft(android.R.mipmap.sym_def_app_icon),
                        new FooterMenuItem(),

                        new HeaderMenuItem("General Options"),
                        new BodyMenuItem(0).withTitle("Title 1").withDescription("This is a sample description"),
                        new BodyMenuItem(0).withTitle("Title 1").withContent("This is the best example of a long value that I can create on the spot.  I am very tired as I have worked 14 twelve hour shifts in the past 16 days."),
                        new BodyMenuItem(ID_SWITCH1).withTitle("Switch 1").withDescription("This is a sample description").withBooleanValue(true),
                        new FooterMenuItem()
                )
                .build();

        MMPage pageItem1 = new MMPageBuilder(PAGE_ITEM1)
                .withPageTitle("Page 1 Menu")
                .withMenuItems(
                        new HeaderMenuItem("Page 1"),
                        new BodyMenuItem(ID_SWITCH2).withTitle("Switch 2").withDescription("This is a sample description").withBooleanValue(true).withIconLeft(android.R.drawable.ic_menu_directions),
                        new BodyMenuItem(ID_SWITCH3).withTitle("Switch 3").withDescription("This is a sample description").withBooleanValue(true).withIconLeft(android.R.drawable.ic_menu_crop),
                        new BodyMenuItem(ID_SWITCH4).withTitle("Switch 4").withDescription("This is a sample description").withBooleanValue(false).withIconLeft(android.R.drawable.ic_menu_camera),
                        new BodyMenuItem(ID_SWITCH5).withTitle("Switch 5").withDescription("This is a sample description").withBooleanValue(true).withIconLeft(android.R.drawable.ic_menu_day),
                        new FooterMenuItem()
                )
                .withFABOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Do you see this?", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        MMPage pageItem2 = new MMPageBuilder(PAGE_ITEM2)
                .withPageTitle("Page 2 Menu")
                .withMenuItems(
                        new HeaderMenuItem("Page 2"),
                        new BodyMenuItem(ID_TEXT_INPUT1).withTitle("Name").withValue(mName),
                        new FooterMenuItem()
                )
                .build();

        mMenu = new MMMenuBuilder(this, R.id.frame)
                .withPages(
                        pageRoot,
                        pageItem1,
                        pageItem2
                )
                .withInitialPageID(PAGE_ROOT)
                .withHeaderTitleTextColor(R.color.colorPrimary)
                .build();
    }

    @Override
    public void onBodyItemClick(final BodyMenuItem bodyItem) {
        switch (bodyItem.getID()) {
            case ID_ITEM1:
                mMenu.showPage(PAGE_ITEM1);
                break;
            case ID_ITEM2:
                bodyItem.withDescription("Did this get updated?");
                mMenu.updateItem(bodyItem, true);
                break;
            case ID_ITEM3:
                mMenu.showPage(PAGE_ITEM2);
                break;
            case ID_TEXT_INPUT1:
                new MaterialDialog.Builder(this)
                        .input("Name", mName, true, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                mName = input.toString();
                                bodyItem.withValue(mName);
                                mMenu.updateItem(bodyItem, true);
                            }
                        })
                        .show();
                break;
        }
    }
}
