package com.studios.holtzapfel.menumakerexample;

import android.content.Intent;
import android.net.Uri;
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
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MMActivity{

    private static final int PAGE_ROOT = 100;
    private static final int ID_EXAMPLE_SWITCHES = 101;
    private static final int ID_EXAMPLE_FORM = 102;
    private static final int ID_EXAMPLE_DISPLAY_INFORMATION = 103;
    private static final int ID_EXAMPLE_CUSTOM_ITEMS = 104;
    private static final int ID_GITHUB = 105;
    private static final int ID_CREDITS = 106;
    private static final int ID_DEVELOPMENT = 107;

    private static final int PAGE_SWITCHES = 200;

    private static final int PAGE_FORMS = 300;
    private static final int ID_FORM_FIRST_NAME = 301;
    private static final int ID_FORM_LAST_NAME = 302;
    private static final int ID_FORM_EMAIL_ADDRESS = 303;
    private static final int ID_FORM_RECEIVE_EMAILS = 304;

    private static final int PAGE_DISPLAY_INFORMATION = 400;
    private static final int ID_TOPIC = 401;

    private static final int PAGE_CUSTOM_ITEMS = 500;

    private static final int PAGE_CREDITS = 600;
    private static final int ID_CREDIT_ICONS8 = 601;
    private static final int ID_CREDIT_MATERIAL_DIALOGS = 602;

    private static final int PAGE_DEVELOPMENT = 700;

    private MMMenu mMenu;
    private String mFirstName = "Drew";
    private String mLastName = "Holtzapfel";
    private String mEmailAddress = "holtzapfel.studios@gmail.com";
    private boolean receiveEmails = true;

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
        // Do not create MMMenu object here if you wish for the items to be editable!!
        // Instead, create a different function that builds the object if not already created
        if (mMenu == null){
            buildMenu();
        }
        return mMenu;
    }

    // Used to build MMMenu object
    private void buildMenu(){
        MMPage pageRoot = new MMPageBuilder(PAGE_ROOT)
                .withPageTitle("Holtzapfel\'s Menu Maker")
                .withMenuItems(
                        new HeaderMenuItem("Examples"),
                        new BodyMenuItem(ID_EXAMPLE_CUSTOM_ITEMS).withTitle("Custom Menu Items").withDescription("Need each item to appear differently?  No problem.").withIconLeft(R.drawable.ic_star),
                        new BodyMenuItem(ID_EXAMPLE_FORM).withTitle("Forms").withDescription("Allow users to update and edit information").withIconLeft(R.drawable.ic_form),
                        new BodyMenuItem(ID_EXAMPLE_DISPLAY_INFORMATION).withTitle("Display Information").withDescription("Menus do not have to be just about selecting preferences.  Sometimes, like in a Help section, information just needs to be displayed!").withIconLeft(R.drawable.ic_news),
                        new BodyMenuItem(ID_EXAMPLE_SWITCHES).withTitle("Switches").withDescription("Create menus with switches for true/false properties").withIconLeft(R.drawable.ic_switch),
                        new FooterMenuItem(),

                        new HeaderMenuItem("About"),
                        new BodyMenuItem(ID_GITHUB).withTitle("GitHub").withDescription("Visit the GitHub page to integrate MenuMaker into your app").withIconLeft(R.drawable.ic_github),
                        new BodyMenuItem(ID_CREDITS).withTitle("Credits").withIconLeft(R.drawable.ic_credits),
                        new BodyMenuItem(ID_DEVELOPMENT).withTitle("Development").withIconLeft(R.drawable.ic_developer_board),
                        new FooterMenuItem()
                )
                .withHeaderTitleTextColor(0)
                .build();

        MMPage pageSwitches = new MMPageBuilder(PAGE_SWITCHES)
                .withPageTitle("Switches")
                .withMenuItems(
                        new HeaderMenuItem("Group 1"),
                        new BodyMenuItem(0).withTitle("Switch 1").withDescription("Switch 1 description").withBooleanValue(true),
                        new BodyMenuItem(0).withTitle("Switch 2").withDescription("Switch 2 description").withBooleanValue(false),
                        new BodyMenuItem(0).withTitle("Switch 3").withDescription("Switch 3 description").withBooleanValue(true),
                        new FooterMenuItem(),

                        new HeaderMenuItem("Group 2"),
                        new BodyMenuItem(0).withTitle("Switch 4").withBooleanValue(false),
                        new FooterMenuItem()
                )
                .withFABOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Do you see this?", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        MMPage pageForms = new MMPageBuilder(PAGE_FORMS)
                .withPageTitle("Forms")
                .withMenuItems(
                        new HeaderMenuItem("User Information"),
                        new BodyMenuItem(ID_FORM_FIRST_NAME).withTitle("First Name").withValue(mFirstName),
                        new BodyMenuItem(ID_FORM_LAST_NAME).withTitle("Last Name").withValue(mLastName),
                        new BodyMenuItem(ID_FORM_EMAIL_ADDRESS).withTitle("Email Address").withValue(mEmailAddress),
                        new FooterMenuItem(),

                        new HeaderMenuItem("Preferences"),
                        new BodyMenuItem(ID_FORM_RECEIVE_EMAILS).withTitle("Receive emails").withBooleanValue(receiveEmails),
                        new FooterMenuItem()
                )
                .build();

        List<IMenuItem> sampleItems = new ArrayList<>();
        sampleItems.add(new HeaderMenuItem("FAB Capable"));
        sampleItems.add(new BodyMenuItem(0).withTitle("Floating Action Buttons").withDescription("Can be enabled and used easily!"));
        sampleItems.add(new FooterMenuItem());
        sampleItems.add(new HeaderMenuItem("Topics List"));
        for (int x = 1; x < 11; x++){
            sampleItems.add(new BodyMenuItem(ID_TOPIC).withTitle("Topic " + String.valueOf(x)).withContent("Here is some content surrounding topic " + String.valueOf(x) + ".  Hopefully this information does something really important for you"));
        }
        sampleItems.add(new FooterMenuItem());
        sampleItems.add(new HeaderMenuItem("Did you notice that?"));
        sampleItems.add(new BodyMenuItem(0).withTitle("The FAB disappeared when scrolling down!"));
        sampleItems.add(new FooterMenuItem());


        MMPage pageDisplayInformation = new MMPageBuilder(PAGE_DISPLAY_INFORMATION)
                .withPageTitle("Display Information")
                .withMenuItems(sampleItems)
                .withFABEnabled(true)
                .withFABOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Customize me!", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        MMPage pageCustomItems = new MMPageBuilder(PAGE_CUSTOM_ITEMS)
                .withPageTitle("Custom Menu Items")
                .withMenuItems(
                        new HeaderMenuItem("Example Items"),
                        new BodyMenuItem(0).withTitle("Simple Item").withTitleTextColor(android.R.color.holo_green_dark).withDescription("What would the world be like if we could divide by zero?").withValue("3.14"),
                        new BodyMenuItem(0).withTitle("Divide by Zero").withDescription("Enabled dividing by zero").withBooleanValue(false),
                        new BodyMenuItem(0).withTitle("Warning!").withTitleTextColor(android.R.color.holo_red_light).withContent("Dividing by zero is not a good thing.  Use only when necessary"),
                        new FooterMenuItem(),

                        new HeaderMenuItem("Individual Items").withTitleTextColor(android.R.color.holo_red_dark),
                        new BodyMenuItem(0).withTitle("Title Only"),
                        new BodyMenuItem(0).withDescription("Description Only"),
                        new BodyMenuItem(0).withValue("Value Only"),
                        new BodyMenuItem(0).withContent("Content Only"),
                        new BodyMenuItem(0).withBooleanValue(true),
                        new FooterMenuItem(),

                        new HeaderMenuItem("Icon Customization").withTitleTextColor(R.color.colorAccent),
                        new BodyMenuItem(0).withTitle("Left Icon").withIconLeft(R.drawable.ic_face),
                        new BodyMenuItem(0).withTitle("Right Icon").withTitleTextColor(android.R.color.holo_blue_bright).withIconRight(R.drawable.ic_face),
                        new BodyMenuItem(0).withTitle("Bilateral Icons").withDescription("Customize icon colors too!").withTitleTextColor(android.R.color.holo_orange_dark).withIconLeft(R.drawable.ic_face).withIconLeftColor(android.R.color.holo_purple).withIconRight(R.drawable.ic_face).withIconRightColor(android.R.color.holo_green_light),
                        new FooterMenuItem()
                )
                .withFABEnabled(true)
                .withFABOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "FAB!", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        MMPage pageCredits = new MMPageBuilder(PAGE_CREDITS)
                .withPageTitle("Credits")
                .withMenuItems(
                        new BodyMenuItem(ID_CREDIT_ICONS8).withTitle("Icons8").withDescription("All the icons you need.  Guaranteed.").withIconLeft(R.drawable.ic_icons8),
                        new BodyMenuItem(ID_CREDIT_MATERIAL_DIALOGS).withTitle("Material Dialogs").withDescription("A beautiful, fluid, and customizable dialogs API.").withIconLeft(R.drawable.ic_favorite)
                )
                .build();

        MMPage pageDevelopment = new MMPageBuilder(PAGE_DEVELOPMENT)
                .withPageTitle("Development")
                .withMenuItems(
                        new HeaderMenuItem("Versions"),
                        new BodyMenuItem(0).withTitle("App Release").withValue("v1.0"),
                        new BodyMenuItem(0).withTitle("MenuMaker API").withValue("v1.0-beta1"),
                        new FooterMenuItem(),

                        new HeaderMenuItem("Misc"),
                        new BodyMenuItem(0).withTitle("Author").withValue("Drew Holtzapfel"),
                        new FooterMenuItem()
                )
                .build();

        mMenu = new MMMenuBuilder(this, R.id.frame)
                .withPages(
                        pageRoot,
                        pageSwitches,
                        pageForms,
                        pageDisplayInformation,
                        pageCustomItems,
                        pageCredits,
                        pageDevelopment
                )
                .withInitialPageID(PAGE_ROOT)
                .withIconColor(R.color.colorPrimary)
                .withHeaderTitleTextColor(R.color.colorPrimary)
                .build();
    }

    @Override
    public void onBodyItemClick(final BodyMenuItem bodyItem) {
        switch (bodyItem.getID()) {
            case ID_CREDITS:
                mMenu.showPage(PAGE_CREDITS);
                break;
            case ID_CREDIT_ICONS8:
                Intent intentIcons8 = new Intent(Intent.ACTION_VIEW);
                intentIcons8.setData(Uri.parse("https://icons8.com/"));
                startActivity(intentIcons8);
                break;
            case ID_CREDIT_MATERIAL_DIALOGS:
                Intent intentMaterialDialogs = new Intent(Intent.ACTION_VIEW);
                intentMaterialDialogs.setData(Uri.parse("https://github.com/afollestad/material-dialogs"));
                startActivity(intentMaterialDialogs);
                break;
            case ID_DEVELOPMENT:
                mMenu.showPage(PAGE_DEVELOPMENT);
                break;
            case ID_EXAMPLE_CUSTOM_ITEMS:
                mMenu.showPage(PAGE_CUSTOM_ITEMS);
                break;
            case ID_EXAMPLE_DISPLAY_INFORMATION:
                mMenu.showPage(PAGE_DISPLAY_INFORMATION);
                break;
            case ID_EXAMPLE_FORM:
                mMenu.showPage(PAGE_FORMS);
                break;
            case ID_EXAMPLE_SWITCHES:
                mMenu.showPage(PAGE_SWITCHES);
                break;
            case ID_FORM_EMAIL_ADDRESS:
                new MaterialDialog.Builder(this)
                        .input("Email Address", mEmailAddress, true, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                mEmailAddress = input.toString();
                                bodyItem.withValue(mEmailAddress);
                                mMenu.updateItem(bodyItem, true);
                            }
                        })
                        .show();
                break;
            case ID_FORM_FIRST_NAME:
                new MaterialDialog.Builder(this)
                        .input("First Name", mFirstName, true, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                mFirstName = input.toString();
                                bodyItem.withValue(mFirstName);
                                mMenu.updateItem(bodyItem, true);
                            }
                        })
                        .show();
                break;
            case ID_FORM_LAST_NAME:
                new MaterialDialog.Builder(this)
                        .input("Last Name", mLastName, true, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                mLastName = input.toString();
                                bodyItem.withValue(mLastName);
                                mMenu.updateItem(bodyItem, true);
                            }
                        })
                        .show();
                break;
            case ID_FORM_RECEIVE_EMAILS:
                receiveEmails = bodyItem.getBooleanValue();
                break;
            case ID_GITHUB:
                String url = "https://github.com/holtzapfel/MenuMaker";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case ID_TOPIC:
                Toast.makeText(this, bodyItem.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
