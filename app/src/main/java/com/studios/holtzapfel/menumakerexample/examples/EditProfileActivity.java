package com.studios.holtzapfel.menumakerexample.examples;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.text.InputType;
import android.widget.Toast;

import com.studios.holtzapfel.menumaker.MMActivity;
import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.MMMenuBuilder;
import com.studios.holtzapfel.menumaker.MMPage;
import com.studios.holtzapfel.menumaker.MMPageBuilder;
import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.FooterMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumakerexample.R;

/**
 * Created by holtzapfel on 7/7/17.
 */

public class EditProfileActivity extends MMActivity{

    private static final String PREF_FIRST_NAME = "PREF_FIRST_NAME";
    private static final String PREF_LAST_NAME = "PREF_LAST_NAME";
    private static final String PREF_EMAIL_ADDRESS = "PREF_EMAIL_ADDRESS";
    private static final String PREF_PHONE_NUMBER = "PREF_PHONE_NUMBER";

    private static final int PAGE_MAIN = 100;
    private static final int ID_FIRST_NAME = 101;
    private static final int ID_LAST_NAME = 102;
    private static final int ID_EMAIL_ADDRESS = 103;
    private static final int ID_PHONE_NUMBER = 104;

    private MMMenu mMenu;

    private String mFirstName;
    private String mLastName;
    private String mEmailAddress;
    private String mPhoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMenu();
    }

    @Override
    public MMMenu onRequestMenu() {
        if (mMenu == null){
            mMenu = buildMenu();
        }
        return mMenu;
    }

    private MMMenu buildMenu(){
        retrieveProfileValues();

        MMPage pageMain = new MMPageBuilder(PAGE_MAIN)
                .withPageTitle("Edit Profile")
                .withMenuItems(
                        new HeaderMenuItem(0).withTitle("Basic Info"),
                        new BodyMenuItem(ID_FIRST_NAME).withTitle("First Name").withValueEditable(mFirstName, "John", true, true, "Edit First Name", InputType.TYPE_TEXT_FLAG_CAP_WORDS, new BodyMenuItem.OnVerifyInputListener() {
                            @Override
                            public boolean onVerifyInput(CharSequence input) {
                                return input.length() < 10;
                            }

                            @Override
                            public void afterVerification(boolean inputVerified) {
                                if (!inputVerified){
                                    Toast.makeText(EditProfileActivity.this, "Max length is 10!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }),
                        new BodyMenuItem(ID_LAST_NAME).withTitle("Last Name").withValueEditable(mLastName, "Doe", true, true, "Edit Last Name", InputType.TYPE_TEXT_FLAG_CAP_WORDS),
                        new FooterMenuItem(),

                        new HeaderMenuItem(0).withTitle("Contact Info"),
                        new BodyMenuItem(ID_EMAIL_ADDRESS).withTitle("Email Address").withValueEditable(mEmailAddress, "johndoe@gmail.com", true, true, "Edit Email Address", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, true),
                        new BodyMenuItem(ID_PHONE_NUMBER).withTitle("Phone Number").withValueEditable(mPhoneNumber, "18001234567", true, true, "Edit Phone Number", InputType.TYPE_CLASS_PHONE, true),
                        new FooterMenuItem()
                )
                .withHeaderTitleTextColor(R.color.colorPrimary)
                .build();

        return new MMMenuBuilder(this)
                .withFrameLayout(R.id.frame)
                .withPages(pageMain)
                .withOnMenuItemClickListener(new MMMenu.OnMenuItemClickListener() {
                    @Override
                    public void onBodyItemClick(BodyMenuItem bodyMenuItem) {
                        switch (bodyMenuItem.getID()){
                            case ID_FIRST_NAME:
                                storeValue(PREF_FIRST_NAME, bodyMenuItem.getValue());
                                break;
                            case ID_LAST_NAME:
                                storeValue(PREF_LAST_NAME, bodyMenuItem.getValue());
                                break;
                            case ID_EMAIL_ADDRESS:
                                storeValue(PREF_EMAIL_ADDRESS, bodyMenuItem.getValue());
                                break;
                            case ID_PHONE_NUMBER:
                                storeValue(PREF_PHONE_NUMBER, bodyMenuItem.getValue());
                                break;
                        }
                    }

                    @Override
                    public void onHeaderItemClick(HeaderMenuItem headerMenuItem) {

                    }
                })
                .build();
    }

    private void retrieveProfileValues(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        mFirstName = prefs.getString(PREF_FIRST_NAME, "John");
        mLastName = prefs.getString(PREF_LAST_NAME, "Doe");
        mEmailAddress = prefs.getString(PREF_EMAIL_ADDRESS, "johndoe@gmail.com");
        mPhoneNumber = prefs.getString(PREF_PHONE_NUMBER, "18001234567");
    }

    private void storeValue(String key, String value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString(key, value).apply();
    }
}
