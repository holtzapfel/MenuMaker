package com.studios.holtzapfel.menumakerexample;

import android.os.Bundle;
import android.widget.Toast;

import com.studios.holtzapfel.menumaker.MMActivity;
import com.studios.holtzapfel.menumaker.model.BaseBodyMenuItem;
import com.studios.holtzapfel.menumaker.model.BodyDefaultMenuItem;
import com.studios.holtzapfel.menumaker.model.BodySwitchMenuItem;
import com.studios.holtzapfel.menumaker.model.FooterMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.ArrayList;
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
        List<IMenuItem> menuItems = new ArrayList<>();
        switch (pageID){
            case PAGE_ROOT:
                menuItems.add(new HeaderMenuItem("General Options"));
                menuItems.add(new BodyDefaultMenuItem(ID_ITEM1, "Item 1", "This is a sample description").setIcon(android.R.drawable.ic_menu_add));
                menuItems.add(new BodyDefaultMenuItem(ID_ITEM2, "Item 2", "This is a sample description").setIcon(android.R.drawable.ic_menu_directions));
                menuItems.add(new BodyDefaultMenuItem(ID_ITEM3, "Item 3", "This is a sample description").setIcon(android.R.drawable.ic_menu_help));
                menuItems.add(new BodyDefaultMenuItem(ID_ITEM4, "Item 4", "This is a sample description").setIcon(android.R.drawable.ic_menu_agenda).setLastItem(true));
                menuItems.add(new FooterMenuItem());

                menuItems.add(new HeaderMenuItem("More"));
                menuItems.add(new BodySwitchMenuItem(ID_SWITCH1, "Switch 1", false));
                menuItems.add(new BodySwitchMenuItem(ID_SWITCH2, "Switch 2", "This is a sample description", true).setLastItem(true));
                menuItems.add(new FooterMenuItem());
                break;
            case PAGE_ITEM1:
                menuItems.add(new HeaderMenuItem("Page 1"));
                menuItems.add(new BodySwitchMenuItem(ID_SWITCH3, "Switch 3", "This is a sample description", true).setIcon(android.R.drawable.ic_menu_crop));
                menuItems.add(new BodySwitchMenuItem(ID_SWITCH4, "Switch 4", "This is a sample description", false).setIcon(android.R.drawable.ic_menu_camera));
                menuItems.add(new BodySwitchMenuItem(ID_SWITCH5, "Switch 5", "This is a sample description", true).setIcon(android.R.drawable.ic_menu_day).setLastItem(true));
                menuItems.add(new FooterMenuItem());
                break;
        }

        return menuItems;
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
