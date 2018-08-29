package omabralimited.voterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class FragmentData extends Fragment {
  private View myFragmentView;
  private Button btnVoter;
  private Button btnDeath;

  public static FragmentData newInstance() {
    FragmentData fragment = new FragmentData();

    return fragment;
  }

  public FragmentData() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    myFragmentView = inflater.inflate(R.layout.fragment_data, container, false);
    btnVoter = (Button) myFragmentView.findViewById(R.id.btnVoterData);
    btnDeath = (Button) myFragmentView.findViewById(R.id.btnDeathData);


    btnVoter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        FragmentVoterList fragment = new FragmentVoterList();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

      }
    });
    btnDeath.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        FragmentDeathList fragment = new FragmentDeathList();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

      }
    });

    return myFragmentView;
  }

}
