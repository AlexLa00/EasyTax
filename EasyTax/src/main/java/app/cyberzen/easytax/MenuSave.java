package app.cyberzen.easytax;

import android.view.MenuItem;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class MenuSave extends Fragment {


    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.ButtonSettingsSave:
                Toast.makeText(getActivity(),"Save", Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }}