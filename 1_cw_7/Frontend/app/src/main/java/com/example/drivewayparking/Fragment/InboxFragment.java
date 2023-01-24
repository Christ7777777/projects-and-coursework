package com.example.drivewayparking.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drivewayparking.R;
import com.google.android.material.tabs.TabLayout;

/**
 * The type Inbox fragment.
 * @author: Varun Advani
 */
public class InboxFragment extends Fragment{

    /**
     * The Tab layout.
     */
    TabLayout tabLayout;
    /**
     * The Fragment.
     */
    Fragment fragment = null;

    /**
     * Instantiates a new Inbox fragment.
     */
    public InboxFragment() {
        // Required empty public constructor
    }

    /**
     * New instance inbox fragment.
     *
     * @param param1 the param 1
     * @param param2 the param 2
     * @return the inbox fragment
     */
    public static InboxFragment newInstance(String param1, String param2) {
        InboxFragment fragment = new InboxFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);

        tabLayout = view.findViewById((R.id.inboxTab));

        fragment = new InboxMessagesFragment();
        getFragmentManager().beginTransaction().replace(R.id.inboxSubFragment, fragment).commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0:
                        fragment = new InboxFragment();
                        break;
                    case 1:
                        fragment = new InboxNotificationsFragment();
                        break;
                }
                getFragmentManager().beginTransaction().replace(R.id.inboxSubFragment, fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        return view;
    }
}