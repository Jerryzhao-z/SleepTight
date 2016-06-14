package fr.sleeptight.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import fr.sleeptight.R;
import fr.sleeptight.data.traitement.User;
import fr.sleeptight.ui.chart.PieChartActivity;
import fr.sleeptight.ui.light.LightAuthActivity;
import fr.sleeptight.ui.listener.ChromeTabListener;
import fr.sleeptight.ui.page.Page1;
import fr.sleeptight.ui.page.SleepPlanActivity;
import fr.sleeptight.ui.page.SleepPlanSimulation;
import fr.sleeptight.ui.user_branch.LoginActivity;

public class BasicPage extends AppCompatActivity {
    private Drawer result = null;
    private AccountHeader headerResult = null;
    private static final int PROFILE_SETTING = 100000;
    private static final int PROFILE_USER_ID = 666;

    public void Slide_Bar(Toolbar toolbar){
        new DrawerBuilder().withActivity(this).build();





        IProfile user = setAccount();

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
               .addProfiles(
                        user,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, FontAwesome.Icon.faw_plus_square).actionBar().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(PROFILE_SETTING+1)
                )
               /* .withOnAccountHeaderSelectionViewClickListener(new AccountHeader.OnAccountHeaderSelectionViewClickListener() {

                        @Override
                        public boolean onClick(View view, IProfile profile){
                            User curUser = User.getInstance();

                            if(curUser.getId().equals("unset")){
                                Intent intent  = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Intent intent  = new Intent(getApplicationContext(), HomePage.class);
                                startActivity(intent);
                            }
                            return false;
                        }
                    })*/
                .withOnAccountHeaderProfileImageListener(new AccountHeader.OnAccountHeaderProfileImageListener() {
                    @Override
                    public boolean onProfileImageClick(View view, IProfile profile, boolean current) {
                        User curUser = User.getInstance();

                        if(curUser.getId().equals("unset")){
                            Intent intent  = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Intent intent  = new Intent(getApplicationContext(), HomePage.class);
                            startActivity(intent);
                        }

                        return false;
                    }

                    @Override
                    public boolean onProfileImageLongClick(View view, IProfile profile, boolean current) {
                       /* Intent intent  = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);*/
                        return false;
                    }
                })
                .build();



        PrimaryDrawerItem item0 = new PrimaryDrawerItem().withName(R.string.drawer_item_0).withIdentifier(0).withIcon(FontAwesome.Icon.faw_home);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(R.string.drawer_item_1).withIdentifier(1).withIcon(FontAwesome.Icon.faw_bed);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName(R.string.drawer_item_2).withIdentifier(2).withIcon(FontAwesome.Icon.faw_heartbeat);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName(R.string.drawer_item_3).withIdentifier(3).withIcon(FontAwesome.Icon.faw_bell_o);

        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName(R.string.drawer_item_5).withIdentifier(5).withIcon(FontAwesome.Icon.faw_github_alt);
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withName(R.string.drawer_item_6).withIdentifier(6).withIcon(FontAwesome.Icon.faw_cogs);
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withName(R.string.drawer_item_7).withIdentifier(7).withIcon(FontAwesome.Icon.faw_bullhorn);

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withItemAnimator(new AlphaCrossFadeAnimator())
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item0,
                        new DividerDrawerItem(),
                        item2,
                        item1,
                        item3,
                        new ExpandableDrawerItem().withName("Device Connection").withIcon(FontAwesome.Icon.faw_random).withIdentifier(112).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("Bracelet").withIdentifier(4).withIcon(FontAwesome.Icon.faw_openid),
                                new SecondaryDrawerItem().withName("Light").withIdentifier(44).withIcon(FontAwesome.Icon.faw_lightbulb_o)
                        ),
                        new DividerDrawerItem(),
                        item5,
                        item6,
                        item7,
                        new DividerDrawerItem(),
                        new SwitchDrawerItem().withName("Switch").withIcon(FontAwesome.Icon.faw_steam).withChecked(true).withSelectable(false)
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
									intent  = new Intent(getApplicationContext(), PieChartActivity.class);
									break;
                                case 3:
                                    intent  = new Intent(getApplicationContext(), SleepPlanActivity.class);
                                    break;
                                case 44:
                                    intent  = new Intent(getApplicationContext(), LightAuthActivity.class);
                                    break;
                                case 4:
                                    toBracelet();
                                    break;
                                case 7:
                                    User.getInstance().setId("unset");
                                    intent  = new Intent(getApplicationContext(), LoginActivity.class);
                                    break;
								default: break;
							}
							if (intent != null) {
                                startActivity(intent);
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


    private IProfile setAccount(){
        User curUser = User.getInstance();
        String username ="";
        String email="";
        int icon= R.drawable.account;;
        if(curUser.getId().equals("unset")){
            username = "Please Sign up";
            email= "Please Sign up";
            icon=R.drawable.nouser;
        }else{
            username = curUser.getUsername();
            email = curUser.getEmail();
        }
        //username = curUser.getUsername();
        //email = curUser.getId();

        IProfile res = new ProfileDrawerItem().withName(username).withEmail(email).withIcon(icon).withIdentifier(PROFILE_USER_ID);
        return res;
    }

    private void toBracelet(){
        ChromeTabListener cm= new ChromeTabListener(this, Boolean.TRUE);
        cm.openCustomTab();
    }



}
