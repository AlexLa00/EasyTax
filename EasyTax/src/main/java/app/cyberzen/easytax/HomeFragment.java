package app.cyberzen.easytax;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment
        implements View.OnClickListener {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button cam = (Button) getView().findViewById(R.id.nav_camera);

        cam.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.camera_activity, container, false);

    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.nav_camera:
                fragment = new Fragment();
                break;

        }
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_camera, fragment)
                .addToBackStack(null)
                .commit();
    }
}
