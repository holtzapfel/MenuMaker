package com.studios.holtzapfel.menumakerexample;

import android.os.Bundle;
import android.widget.Toast;

import com.studios.holtzapfel.menumaker.MMActivity;
import com.studios.holtzapfel.menumaker.MMPageBuilder;
import com.studios.holtzapfel.menumaker.model.BaseBodyMenuItem;
import com.studios.holtzapfel.menumaker.model.BodyDefaultMenuItem;
import com.studios.holtzapfel.menumaker.model.BodySwitchMenuItem;
import com.studios.holtzapfel.menumaker.model.FooterMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public int onRequestFragmentContainerResource() {
        return R.id.frame;
    }

    @Override
    public int getInitialMenuPageID() {
        return PAGE_ROOT;
    }

    @Override
    public String onRequestTitle(int pageID) {
        switch (pageID){
            case PAGE_ROOT:
                return "Menu Maker Example App";
            case PAGE_ITEM1:
                return "Item 1";
        }
        return "Test";
    }

    @Override
    public boolean isFloatingActionButtonEnabled(int pageID) {
        switch (pageID){
            case PAGE_ROOT:
                return false;
            case PAGE_ITEM1:
                return true;
        }
        return true;
    }

    @Override
    public void onFloatingActionButtonClick(int pageID) {
        Toast.makeText(this, "Yay!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<IMenuItem> onRequestMenuItems(int pageID) {
        MMPageBuilder pageRoot = new MMPageBuilder(this)
                .addMenuItems(
                        new HeaderMenuItem("General Options"),
                        new BodyDefaultMenuItem(ID_ITEM1).withTitle("Item 1").withDescription("This is a sample description").withIcon(android.R.mipmap.sym_def_app_icon),
                        new BodyDefaultMenuItem(ID_ITEM2).withTitle("Title 2").withDescription("This is a sample description").withIcon(android.R.mipmap.sym_def_app_icon),
                        new BodyDefaultMenuItem(ID_ITEM3).withTitle("Title 3").withDescription("This is a sample description").withIcon(android.R.mipmap.sym_def_app_icon),
                        new BodyDefaultMenuItem(ID_ITEM4).withTitle("Title 4").withDescription("This is a sample description").withIcon(android.R.mipmap.sym_def_app_icon).withLastItem(true),
                        new FooterMenuItem(),

                        new HeaderMenuItem("General Options"),
                        new BodyDefaultMenuItem(0).withTitle("Title 1").withDescription("This is a sample description"),
                        new BodySwitchMenuItem(ID_SWITCH1, "Switch 1", "This is a sample description", true).withLastItem(true),
                        new FooterMenuItem()

                )
                .setHeaderTitleTextColor(R.color.colorPrimary);

        MMPageBuilder pageItem1 = new MMPageBuilder(this)
                .addMenuItems(
                        new HeaderMenuItem("Page 1"),
                        new BodySwitchMenuItem(ID_SWITCH2, "Switch 2", "This is a sample description", true).withIcon(android.R.drawable.ic_menu_directions),
                        new BodySwitchMenuItem(ID_SWITCH3, "Switch 3", "This is a sample description", true).withIcon(android.R.drawable.ic_menu_crop),
                        new BodySwitchMenuItem(ID_SWITCH4, "Switch 4", "This is a sample description", false).withIcon(android.R.drawable.ic_menu_camera),
                        new BodySwitchMenuItem(ID_SWITCH5, "Switch 5", "This is a sample description", true).withIcon(android.R.drawable.ic_menu_day).withLastItem(true),
                        new FooterMenuItem()
                );

        switch (pageID){
            case PAGE_ROOT:
                return pageRoot.build();
            case PAGE_ITEM1:
                return pageItem1.build();
        }

        return null;
    }

    @Override
    public IMenuItem onMenuItemClick(IMenuItem menuItem) {
        if (menuItem.getID() == ID_ITEM1){
            showPage(PAGE_ITEM1);
        } else {
            if (menuItem instanceof BaseBodyMenuItem) {
                if (menuItem.getMenuType() != IMenuItem.MENU_ITEM_TYPE_BODY_SWITCH) {
                    Toast.makeText(this, ((BaseBodyMenuItem) menuItem).getTitle(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, String.valueOf(((BodySwitchMenuItem) menuItem).getBooleanValue()), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }
}
