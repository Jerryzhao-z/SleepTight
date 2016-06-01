package fr.sleeptight.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
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
    private Drawer result = null;

    public void Slide_Bar(Toolbar toolbar){
        new DrawerBuilder().withActivity(this).build();
        PrimaryDrawerItem item0 = new PrimaryDrawerItem().withName(R.string.drawer_item_0).withIdentifier(0).withIcon(FontAwesome.Icon.faw_home);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(R.string.drawer_item_1).withIdentifier(1).withIcon(FontAwesome.Icon.faw_bed);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName(R.string.drawer_item_2).withIdentifier(2).withIcon(FontAwesome.Icon.faw_heartbeat);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName(R.string.drawer_item_3).withIdentifier(3).withIcon(FontAwesome.Icon.faw_clock_o);

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item0,
                        new DividerDrawerItem(),
                        item1,
                        item2,
                        item3
                        )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
						
						if (drawerItem != null){
							Intent intent = null;
							switch((int) drawerItem.getIdentifier()){
								case 0:
									intent  = new Intent(getApplicationContext(), HomePage.class);
									break;
								case 1:
									intent  = new Intent(getApplicationContext(), Page1.class);
									break;
								case 2:
									intent  = new Intent(getApplicationContext(), Page2.class);
									break;
								default: break;
							}
							if (intent != null) {
                                BasicPage.this.startActivity(intent);
                            }
						}
							
                        return false;
                    }
                })
                .withShowDrawerOnFirstLaunch(true)
                .build();

    }

	@Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }


}
