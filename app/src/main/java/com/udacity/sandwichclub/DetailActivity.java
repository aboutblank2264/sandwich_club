package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.ui.TabPageAdapter;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Initialize Toolbar
        Toolbar toolbar = findViewById(R.id.detail_toolbar);

        setSupportActionBar(toolbar);

        //adds the up arrow
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
            return;
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        //Create the ViewPager and TabLayout
        TabPageAdapter tabPageAdapter = new TabPageAdapter(getSupportFragmentManager(),
                createSandwichBundle(sandwich),
                makeTabNameList());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabPageAdapter);
        viewPager.setCurrentItem(0);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Creates a list of the names of the tabs.
     * @return A list of tabs for the ViewPager
     */
    private List<String> makeTabNameList() {
        List<String> names = new ArrayList<>();
        names.add(getString(R.string.description_tab));
        names.add(getString(R.string.additional_info_tab));

        return names;
    }

    /**
     * Creates a Bundle with all the Sandwich fields for the ViewPager fragments.
     */
    private Bundle createSandwichBundle(Sandwich sandwich) {
        Bundle bundle = new Bundle();

        bundle.putString(getString(R.string.description), sandwich.getDescription());

        bundle.putStringArrayList(getString(R.string.alsoKnownAs), (ArrayList<String>)sandwich.getAlsoKnownAs());

        bundle.putString(getString(R.string.placeOfOrigin), sandwich.getPlaceOfOrigin());

        bundle.putStringArrayList(getString(R.string.ingredients), (ArrayList<String>)sandwich.getIngredients());

        return bundle;
    }
}
