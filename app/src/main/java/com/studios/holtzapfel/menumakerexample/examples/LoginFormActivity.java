package com.studios.holtzapfel.menumakerexample.examples;

import android.os.Bundle;
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

public class LoginFormActivity extends MMActivity {

    private static final String USERNAME = "menumaker";
    private static final String PASSWORD = "password";

    private static final int PAGE_MAIN = 100;
    private static final int ID_USERNAME = 101;
    private static final int ID_PASSWORD = 102;
    private static final int ID_LOGIN = 103;

    private static final int PAGE_LOGGED_IN = 200;

    private MMMenu mMenu;
    private String mUsername = "";
    private String mPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        final BodyMenuItem usernameItem = new BodyMenuItem(ID_USERNAME).withTitle("Username").withDescription("Hint: \"menumaker\"").withValueEditable(null, null, false, true, "Username");
        final BodyMenuItem passwordItem = new BodyMenuItem(ID_PASSWORD).withTitle("Password").withDescription("Hint: \"password\"").withValueEditable(null, null, false, true, "Password", InputType.TYPE_TEXT_VARIATION_PASSWORD);
        final BodyMenuItem loginItem = new BodyMenuItem(ID_LOGIN).withValue("Login").withEnabled(false);

        MMPage pageMain = new MMPageBuilder(PAGE_MAIN)
                .withPageTitle("Login Form")
                .withMenuItems(
                        usernameItem,
                        passwordItem,
                        new FooterMenuItem(),
                        loginItem
                )
                .build();

        MMPage pageLoggedIn = new MMPageBuilder(PAGE_LOGGED_IN)
                .withPageTitle("Login Successful")
                .withMenuItems(
                        new BodyMenuItem(0).withTitle("Congratulations on logging in!")
                )
                .build();

        return new MMMenuBuilder(this)
                .withFrameLayout(R.id.frame)
                .withUseSlidingAnimationFragmentTransitions(false)
                .withPages(pageMain, pageLoggedIn)
                .withOnMenuItemClickListener(new MMMenu.OnMenuItemClickListener() {
                    @Override
                    public void onBodyItemClick(BodyMenuItem bodyMenuItem) {
                        switch (bodyMenuItem.getID()){
                            case ID_USERNAME:
                                mUsername = bodyMenuItem.getValue();
                                loginItem.withEnabled(shouldLoginBeEnabled());
                                mMenu.updateItem(loginItem, true);
                                break;
                            case ID_PASSWORD:
                                mPassword = bodyMenuItem.getValue();
                                loginItem.withEnabled(shouldLoginBeEnabled());
                                mMenu.updateItem(loginItem, true);
                                break;
                            case ID_LOGIN:
                                if (isLoginSuccess()){
                                    mUsername = "";
                                    usernameItem.withValue(mUsername);
                                    mMenu.updateItem(usernameItem, false);

                                    mPassword = "";
                                    passwordItem.withValue(mPassword);
                                    mMenu.updateItem(passwordItem, false);

                                    mMenu.showPage(PAGE_LOGGED_IN);
                                } else {
                                    mPassword = "";
                                    passwordItem.withValue(mPassword);
                                    mMenu.updateItem(passwordItem, true);
                                    Toast.makeText(LoginFormActivity.this, "Invalid login", Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }
                    }

                    @Override
                    public void onHeaderItemClick(HeaderMenuItem headerMenuItem) {

                    }
                })
                .build();
    }

    private boolean shouldLoginBeEnabled(){
        return !(mUsername.length() == 0 || mPassword.length() == 0);
    }

    private boolean isLoginSuccess(){
        return mUsername.equals(USERNAME) && mPassword.equals(PASSWORD);
    }
}
