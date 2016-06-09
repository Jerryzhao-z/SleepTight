package fr.sleeptight.ui.chart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fr.sleeptight.R;
import fr.sleeptight.ui.BasicPage;

public class BasicChartPage extends BasicPage {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.chartPie: {
                Intent intent  = new Intent(getApplicationContext(), PieChartActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.charBar: {
                Intent intent  = new Intent(getApplicationContext(), BarChartActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.chartLine: {
                break;
            }
        }
        return true;
    }
}
