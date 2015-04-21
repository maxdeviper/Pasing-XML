package com.example.simpumind.fragment2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public  class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static ArrayList<Category> categories;
    public static ListViewAdapter listViewAdapter;
    final static String URL = "http://www.goalserve.com/samples/soccer_livescore.xml";
    public static ListView listView;

    //XML Nodes names:
    static final String ATTRB_ID = "id";
    static final String NODE_CAT = "category";
    static final String NODE_NAME = "name";
    static final String NODE_TYPE = "type";
    static final String NODE_LOCAL = "localteam";
    static final String NODE_VISITOR = "visitorteam";
    static final String NODE_GOALS = "goals";
    static final String NODE_FILE_GROUP = "file_group";
    static final String NODE_FIXD_ID = "fix_id";
    static final String NODE_FORMATTED_DATE = "formatted_date";
    static final String NODE_TIME = "time";
    static final String NODE_DATE = "date";
    static final String NODE_STATUS = "status";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        Button click;


        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            click = (Button) rootView.findViewById(R.id.click);
            listView = (ListView) rootView.findViewById(R.id.list);
            final Context ctx = null;
            click.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    new DOAsyncTask().execute();
                }
            });


            return rootView;
        }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }


        public class DOAsyncTask extends AsyncTask<Void, Void, Void> {

            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {

                XMLDOMParser parser = new XMLDOMParser();
                InputStream stream;

                String xml = parser.getXmlFromUrl(URL);
                Document doc = parser.getDocument(xml);

                //Get Elements by name Categories
                NodeList nodeList = doc.getElementsByTagName(NODE_CAT);
                //categories = new ArrayList<Category>();

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element e = (Element) nodeList.item(i);

                    Category category = new Category();
                    category.setId(Integer.parseInt(e.getAttribute(ATTRB_ID)));
                    category.setFileGroup(e.getAttribute(NODE_FILE_GROUP));
                    category.setName(e.getAttribute(NODE_NAME));
                    Match match = new Match();
                    match.setId(Integer.parseInt(e.getAttribute(ATTRB_ID)));
                    match.setFixId(e.getAttribute(NODE_FIXD_ID));
                    match.setFormatted_date(e.getAttribute(NODE_FORMATTED_DATE));
                    match.setTime(e.getAttribute(NODE_TIME));
                    match.setStatus(e.getAttribute(NODE_STATUS));
                    category.setMatches(match);

                    Team teamlocal = new Team();
                    teamlocal.setId(Integer.parseInt(e.getAttribute(ATTRB_ID)));
                    teamlocal.setName(e.getAttribute(NODE_NAME));
                    teamlocal.setGoal(e.getAttribute(NODE_GOALS));
                    match.setLocalTeam(teamlocal);
                    match.setVistorTeam(teamlocal);

                    categories.add(category);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressDialog.dismiss();
                Toast.makeText(getActivity(),"Value got " + categories.get(0),Toast.LENGTH_LONG).show();
               // listViewAdapter = new ListViewAdapter(getActivity(), R.layout.list_item, categories);
               // listView.setAdapter(listViewAdapter);
            }
        }
    }
}
