package segev.gimfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by LENOVO on 21/12/2017.
 */

public class Tab2activity extends android.support.v4.app.Fragment{
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2activity, container, false);
        btn=(Button) rootView.findViewById(R.id.create_work_out);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Tab2activity.this.getActivity(),Create_workout.class);
                startActivity(intent);


            }
        });
        return rootView;
    }






}

