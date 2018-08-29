package omabralimited.voterapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import omabralimited.voterapp.databases.VoterDbHelper;

public class FragmentVoterList extends Fragment{
  public static FragmentVoterList newInstance() {
    FragmentVoterList fragment = new FragmentVoterList();
    return fragment;
  }

  public FragmentVoterList() {
    // Required empty public constructor
  }
  public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_ITEM_ID";
  //    private ProgressBar pbLoading;
  Context context;
  VoterDbHelper dbHelper;
  ListView listView;
  private OnItemSelectedListener onItemSelectedListener;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_voterlist, container, false);
    context = getActivity().getApplicationContext();
    final ListView listview = (ListView) view.findViewById(R.id.LIST_VIEW_VOTERS);
    listview.setVisibility(View.VISIBLE);
    dbHelper = new VoterDbHelper(context);
    final StableArrayAdapter adapter2 = new StableArrayAdapter(getActivity(),
            android.R.layout.simple_list_item_1, dbHelper.getAllVoters());
    listview.setAdapter(adapter2);
    return view;
  }
  public interface OnItemSelectedListener{
    void voterSelected(VoterInfo voterInfo);
  }
  private class StableArrayAdapter extends ArrayAdapter<VoterInfo> {

    private HashMap<VoterInfo, Integer> mIdMap = new HashMap<VoterInfo, Integer>();
    private final Context context;

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<VoterInfo> objects) {
      super(context, textViewResourceId, objects);
      this.context = context;
      for (int i = 0; i < objects.size(); ++i) {
        mIdMap.put(objects.get(i), i);
      }
    }

    @Override
    public long getItemId(int position) {
      VoterInfo voter = getItem(position);
      return mIdMap.get(voter);
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      LayoutInflater inflater = (LayoutInflater) context
              .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View rowView = inflater.inflate(R.layout.voter_row_layout, parent, false);
      ImageView image = (ImageView) rowView.findViewById(R.id.icon12);
      TextView textView = (TextView) rowView.findViewById(R.id.label12);
      TextView dob = (TextView) rowView.findViewById(R.id.tvdob);
      TextView idnum = (TextView) rowView.findViewById(R.id.tvidNum);
      RelativeLayout mainLayout1 = (RelativeLayout) rowView.findViewById(R.id.mainLayout1);
      final VoterInfo voter = getItem(position);
      textView.setText(voter.getVoter_name());
      dob.setText(voter.getdob());
      idnum.setText(voter.getStatus());
      image.setImageResource(R.drawable.user_avatar);
      if(voter.getStatus()=="Not Eligible"){
        mainLayout1.setBackgroundColor(getResources().getColor(R.color.app_redish));
      }


      return rowView;
    }

  }
  /*private void regVoter(ItemInfo itemInfo){
    Intent intent = new Intent(getActivity(), BidActivity.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("bundle", itemInfo);
    intent.putExtras(bundle);
    startActivity(intent);
  }*/
}
