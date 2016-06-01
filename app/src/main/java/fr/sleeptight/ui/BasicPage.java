package fr.sleeptight.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import fr.sleeptight.R;
import fr.sleeptight.ui.page.Page1;
import fr.sleeptight.ui.page.Page2;

public class BasicPage extends AppCompatActivity {


    public void Slide_Bar(Toolbar toolbar){
        new DrawerBuilder().withActivity(this).build();
        PrimaryDrawerItem item0 = new PrimaryDrawerItem().withName(R.string.drawer_item_0).withIdentifier(0);
        PrimaryDrawerItem item1 = new SecondaryDrawerItem().withName(R.string.drawer_item_1).withIdentifier(1);;
        PrimaryDrawerItem item2 = new SecondaryDrawerItem().withName(R.string.drawer_item_2).withIdentifier(2);;

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item0,
                        new DividerDrawerItem(),
                        item1,
                        item2
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch((int) drawerItem.getIdentifier()){
                            case 0:
                                Intent newAct0  = new Intent(getApplicationContext(), HomePage.class);
                                startActivity(newAct0);break;
                            case 1:
                                Intent newAct1  = new Intent(getApplicationContext(), Page1.class);
                                startActivity(newAct1);break;
                            case 2:
                                Intent newAct2  = new Intent(getApplicationContext(), Page2.class);
                                startActivity(newAct2);break;
                            default: break;
                        }
                        return true;
                    }
                })
                .build();


    }


}
