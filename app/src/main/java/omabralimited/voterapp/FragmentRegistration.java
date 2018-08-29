package omabralimited.voterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentRegistration extends Fragment{
  private View myFragmentView;
  private Button btnVoter;
  private Button btnDeath;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment WithdrawFragment.
   */
  public static FragmentRegistration newInstance() {
    FragmentRegistration fragment = new FragmentRegistration();
    return fragment;
  }

  public FragmentRegistration() {
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
    myFragmentView = inflater.inflate(R.layout.fragment_registration, container, false);
    btnVoter = (Button) myFragmentView.findViewById(R.id.btnRegVoter);
    btnDeath = (Button) myFragmentView.findViewById(R.id.btnRegDeath);

    btnVoter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getActivity().getApplicationContext(), RegisterVoterActivity.class);
        startActivity(intent);
      }
    });
    btnDeath.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getActivity().getApplicationContext(), RegisterDeathActivity.class);
        startActivity(intent);
      }
    });

    return myFragmentView;
  }
}
