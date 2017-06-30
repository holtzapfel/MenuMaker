# MenuMaker

Tired of creating and recreating a settings menu?  Quit spending so much time on a menu that (if done correctly) nobody notices!  Use **Menu Maker** in your app today!

### Features
- Easy to use API
- Create a deep structured menu with multiple pages
- Generate true/false menu items with switches
- Customizable text options
- Add icons for a unique look
- Modify menu items while in use
- Compatible with API level 15 and above

## Installation and Setup Instructions
Follow the instructions below to get started.
### 1. Insert gradle dependency into module build.gradle file
```gradle
compile 'com.github.holtzapfel:MenuMaker:cb6fb6a'
```
### 2. Create new activity that extends **MMActivity**
```java
public class SettingsActivity extends MMActivity{
...
}
```
### 3. Create and return MMMenu object in onRequestMenu()
```java
private MMMenu mMenu;

...

@Override
    public MMMenu onRequestMenu() {
        // Do not create MMMenu object here if you wish for the items to be editable!!
        // Instead, create a different function that builds the object if not already created
        if (mMenu == null){
            buildMenu();
        }
        return mMenu;
    }
```
### 4. Create MMPage objects for MMMenu
```java
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
...
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
        ...
```
### 5. Add pages to MMMenu object

```java
// Used to build MMMenu object
private void buildMenu(){
        ...
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
```
### 6. Customize menu item clicks
```java
@Override
public void onBodyItemClick(final BodyMenuItem bodyItem) {
    switch (bodyItem.getID()) {
        case ID_CREDITS:
            mMenu.showPage(PAGE_CREDITS);
            break;
        case ID_CREDIT_MATERIAL_DIALOGS:
            Intent intentMaterialDialogs = new Intent(Intent.ACTION_VIEW);
            intentMaterialDialogs.setData(Uri.parse("https://github.com/afollestad/material-dialogs"));
            startActivity(intentMaterialDialogs);
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
        case ID_FORM_RECEIVE_EMAILS:
            receiveEmails = bodyItem.getBooleanValue();
            break;
        ...
    }
}
```
That is it!  Enjoy!
