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
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import omabralimited.voterapp.databases.VoterDbHelper;

public class FragmentDeathList extends Fragment {
  public static FragmentDeathList newInstance() {
    FragmentDeathList fragment = new FragmentDeathList();
    return fragment;
  }

  public FragmentDeathList() {
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
    final View view = inflater.inflate(R.layout.fragment_deathlist, container, false);
    context = getActivity().getApplicationContext();
    final ListView listview = (ListView) view.findViewById(R.id.LIST_VIEW_DEATHS);
    listview.setVisibility(View.VISIBLE);
    dbHelper = new VoterDbHelper(context);
    final StableArrayAdapter adapter2 = new StableArrayAdapter(getActivity(),
            android.R.layout.simple_list_item_1, dbHelper.getAllDeaths());
    listview.setAdapter(adapter2);
    return view;
  }
  public interface OnItemSelectedListener{
    void deathSelected(DeadInfo deadInfo);
  }
  private class StableArrayAdapter extends ArrayAdapter<DeadInfo> {

    private HashMap<DeadInfo, Integer> mIdMap = new HashMap<DeadInfo, Integer>();
    private final Context context;

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<DeadInfo> objects) {
      super(context, textViewResourceId, objects);
      this.context = context;
      for (int i = 0; i < objects.size(); ++i) {
        mIdMap.put(objects.get(i), i);
      }
    }

    @Override
    public long getItemId(int position) {
      DeadInfo dead = getItem(position);
      return mIdMap.get(dead);
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      LayoutInflater inflater = (LayoutInflater) context
              .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View rowView = inflater.inflate(R.layout.death_row_layout, parent, false);
      ImageView image = (ImageView) rowView.findViewById(R.id.icon12);
      TextView textView = (TextView) rowView.findViewById(R.id.label12);
      TextView cause = (TextView) rowView.findViewById(R.id.txt_cause);
      TextView dob = (TextView) rowView.findViewById(R.id.tvdob);
      TextView idnum = (TextView) rowView.findViewById(R.id.tvidNum);
      final DeadInfo dead = getItem(position);
      textView.setText(dead.getDead_name());
      dob.setText(dead.getdob());
      cause.setText(dead.getDeathCause());
      idnum.setText(dead.getIdNum());
      image.setImageResource(R.drawable.user_avatar);


      return rowView;
    }

  }
}
