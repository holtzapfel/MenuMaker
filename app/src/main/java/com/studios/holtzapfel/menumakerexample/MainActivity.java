package com.studios.holtzapfel.menumakerexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.studios.holtzapfel.menumaker.MMActivity;
import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.MMMenuBuilder;
import com.studios.holtzapfel.menumaker.MMPage;
import com.studios.holtzapfel.menumaker.MMPageBuilder;
import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.FooterMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;
import com.studios.holtzapfel.menumakerexample.examples.BasicMenuActivity;
import com.studios.holtzapfel.menumakerexample.examples.EditProfileActivity;
import com.studios.holtzapfel.menumakerexample.examples.LoginFormActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MMActivity{

    private static final int PAGE_ROOT = 100;
    private static final int ID_FEAT_SWITCHES = 101;
    private static final int ID_FEAT_FORM = 102;
    private static final int ID_FEAT_DISPLAY_INFORMATION = 103;
    private static final int ID_FEAT_CUSTOM_ITEMS = 104;
    private static final int ID_GITHUB = 105;
    private static final int ID_CREDITS = 106;
    private static final int ID_DEVELOPMENT = 107;
    private static final int ID_EXAMPLE_BASIC_MENU = 108;
    private static final int ID_EXAMPLE_EDIT_PROFILE = 109;
    private static final int ID_EXAMPLE_LOGIN_FORM = 110;

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
        setContentView(R.layout.activity_menu);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public MMMenu onRequestMenu() {
        if (mMenu == null){
            mMenu = new MMMenuBuilder(this)
                    .withFrameLayout(R.id.frame)
                    .withPages(buildPages())
                    .withInitialPageID(PAGE_ROOT)
                    .withUseSlidingAnimationFragmentTransitions(true)
                    .withIconLeftColor(R.color.colorPrimary)
                    .withUseUpArrowOnInitialPage(false)
                    .withOnMenuItemClickListener(new MMMenu.OnMenuItemClickListener() {
                        @Override
                        public void onBodyItemClick(BodyMenuItem bodyMenuItem) {
                            switch (bodyMenuItem.getID()) {
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
                                case ID_EXAMPLE_BASIC_MENU:
                                    Intent intentBasicMenu = new Intent(MainActivity.this, BasicMenuActivity.class);
                                    startActivity(intentBasicMenu);
                                    break;
                                case ID_EXAMPLE_EDIT_PROFILE:
                                    Intent intentEditProfile = new Intent(MainActivity.this, EditProfileActivity.class);
                                    startActivity(intentEditProfile);
                                    break;
                                case ID_EXAMPLE_LOGIN_FORM:
                                    Intent intentLoginForm = new Intent(MainActivity.this, LoginFormActivity.class);
                                    startActivity(intentLoginForm);
                                    break;
                                case ID_FEAT_CUSTOM_ITEMS:
                                    mMenu.showPage(PAGE_CUSTOM_ITEMS);
                                    break;
                                case ID_FEAT_DISPLAY_INFORMATION:
                                    mMenu.showPage(PAGE_DISPLAY_INFORMATION);
                                    break;
                                case ID_FEAT_FORM:
                                    mMenu.showPage(PAGE_FORMS);
                                    break;
                                case ID_FEAT_SWITCHES:
                                    mMenu.showPage(PAGE_SWITCHES);
                                    break;
                                case ID_FORM_EMAIL_ADDRESS:
                                    mEmailAddress = bodyMenuItem.getValue();
                                    bodyMenuItem.withValue("test");
                                    mMenu.refreshPage();
                                    break;
                                case ID_FORM_FIRST_NAME:
                                    mFirstName = bodyMenuItem.getValue();
                                    break;
                                case ID_FORM_LAST_NAME:
                                    mLastName = bodyMenuItem.getValue();
                                    break;
                                case ID_FORM_RECEIVE_EMAILS:
                                    receiveEmails = bodyMenuItem.getBooleanValue();
                                    Intent myIntent = new Intent(MainActivity.this, BasicMenuActivity.class);
                                    startActivity(myIntent);
                                    break;
                                case ID_GITHUB:
                                    String url = "https://github.com/holtzapfel/MenuMaker";
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url));
                                    startActivity(i);
                                    break;
                                case ID_TOPIC:
                                    Toast.makeText(MainActivity.this, bodyMenuItem.getTitle(), Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }

                        @Override
                        public void onHeaderItemClick(HeaderMenuItem headerMenuItem) {

                        }
                    })
                    .build();
        }
        return mMenu;
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMenu();
    }

    // Used to build MMMenu object
    private List<MMPage> buildPages(){
        List<MMPage> pages = new ArrayList<>();
        pages.add(new MMPageBuilder(PAGE_ROOT)
                .withPageTitle("Holtzapfel\'s Menu Maker")
                .withMenuItems(
                        new HeaderMenuItem("Example Menus"),
                        new BodyMenuItem(ID_EXAMPLE_BASIC_MENU).withTitle("Basic Menu").withIconLeft(BodyMenuItem.ICON_ARROW_RIGHT).withIconLeftSize(100, 100),
                        new BodyMenuItem(ID_EXAMPLE_EDIT_PROFILE).withTitle("Edit Profile").withIconLeft(BodyMenuItem.ICON_ARROW_RIGHT).withIconLeftSize(100,100),
                        new BodyMenuItem(ID_EXAMPLE_LOGIN_FORM).withTitle("Login Form").withIconLeft(BodyMenuItem.ICON_ARROW_RIGHT).withIconLeftSize(100, 100),
                        new FooterMenuItem(),

                        new HeaderMenuItem("Features"),
                        new BodyMenuItem(ID_FEAT_CUSTOM_ITEMS).withTitle("Custom Menu Items").withDescription("Need each item to appear differently?  No problem.").withIconLeft(R.drawable.ic_star).withIconRight(BodyMenuItem.ICON_ARROW_RIGHT),
                        new BodyMenuItem(ID_FEAT_FORM).withTitle("Forms").withDescription("Allow users to update and edit information").withIconLeft(R.drawable.ic_form).withIconRight(BodyMenuItem.ICON_ARROW_RIGHT),
                        new BodyMenuItem(ID_FEAT_DISPLAY_INFORMATION).withTitle("Display Information").withDescription("Menus do not have to be just about selecting preferences.  Sometimes, like in a Help section, information just needs to be displayed!").withIconLeft(R.drawable.ic_news).withIconRight(BodyMenuItem.ICON_ARROW_RIGHT),
                        new BodyMenuItem(ID_FEAT_SWITCHES).withTitle("Switches").withDescription("Create menus with switches for true/false properties").withIconLeft(R.drawable.ic_switch).withIconRight(BodyMenuItem.ICON_ARROW_RIGHT),
                        new FooterMenuItem(),

                        new HeaderMenuItem("About"),
                        new BodyMenuItem(ID_GITHUB).withTitle("GitHub").withDescription("Visit the GitHub page to integrate MenuMaker into your app").withIconLeft(R.drawable.ic_github).withIconRight(BodyMenuItem.ICON_OPEN_IN_BROWSER),
                        new BodyMenuItem(ID_CREDITS).withTitle("Credits").withIconLeft(R.drawable.ic_credits).withIconRight(BodyMenuItem.ICON_ARROW_RIGHT),
                        new BodyMenuItem(ID_DEVELOPMENT).withTitle("Development").withIconLeft(R.drawable.ic_developer_board).withIconRight(BodyMenuItem.ICON_ARROW_RIGHT),
                        new FooterMenuItem()
                )
                .build()
        );

        pages.add(new MMPageBuilder(PAGE_SWITCHES)
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
                .withFAB(R.drawable.ic_action_edit, R.color.colorPrimary, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Do you see this?", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
        );

        pages.add(new MMPageBuilder(PAGE_FORMS)
                .withPageTitle("Forms")
                .withMenuItems(
                        new HeaderMenuItem("User Information"),
                        new BodyMenuItem(ID_FORM_FIRST_NAME).withTitle("First Name").withValueEditable(mFirstName, "First Name", true, true, "Edit", InputType.TYPE_TEXT_FLAG_CAP_WORDS),
                        new BodyMenuItem(ID_FORM_LAST_NAME).withTitle("Last Name").withValueEditable(mLastName, "Last Name", true, true, "Edit", InputType.TYPE_TEXT_FLAG_CAP_WORDS),
                        new BodyMenuItem(ID_FORM_EMAIL_ADDRESS).withTitle("Email Address").withValueEditable(mEmailAddress, "Email Address", true, true, "Edit", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, true),
                        new FooterMenuItem(),

                        new HeaderMenuItem("Preferences"),
                        new BodyMenuItem(ID_FORM_RECEIVE_EMAILS).withTitle("Receive emails").withBooleanValue(receiveEmails),
                        new FooterMenuItem()
                )
                .build()
        );

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


        pages.add(new MMPageBuilder(PAGE_DISPLAY_INFORMATION)
                .withPageTitle("Display Information")
                .withMenuItems(sampleItems)
                .withFAB(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Customize me!", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
        );

        pages.add(new MMPageBuilder(PAGE_CUSTOM_ITEMS)
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
                .withFAB(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "FAB!", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
        );

        pages.add(new MMPageBuilder(PAGE_CREDITS)
                .withPageTitle("Credits")
                .withMenuItems(
                        new BodyMenuItem(ID_CREDIT_ICONS8).withTitle("Icons8").withDescription("All the icons you need.  Guaranteed.").withIconLeft(R.drawable.ic_icons8),
                        new BodyMenuItem(ID_CREDIT_MATERIAL_DIALOGS).withTitle("Material Dialogs").withDescription("A beautiful, fluid, and customizable dialogs API.").withIconLeft(R.drawable.ic_favorite)
                )
                .build()
        );

        pages.add(new MMPageBuilder(PAGE_DEVELOPMENT)
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
                .build()
        );

        return pages;
    }
}
