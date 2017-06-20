package com.studios.holtzapfel.menumakerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.studios.holtzapfel.menumaker.MenuFragment;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, MenuFragment.newInstance(1))
                .commit();
    }

    @Override
    public String getTitle(int rootID) {
        return "Test";
    }

    @Override
    public boolean isFABEnabled(int rootID) {
        return true;
    }

    @Override
    public void onFABClick(int rootID) {
        Toast.makeText(this, "Yay!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<IMenuItem> onRequestMenuItems(int rootID) {
        List<IMenuItem> list = new ArrayList<>();
        list.add(new HeaderMenuItem().setTitle("Did this work?"));
        return list;
    }

    @Override
    public IMenuItem onMenuItemClick(IMenuItem menuItem) {
        Toast.makeText(this, "Click!!", Toast.LENGTH_SHORT).show();
        return null;
    }
}
