package in.ac.igdtuw.mindtheapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nishtha on 7/10/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder> {

    @Override
    public ScheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ScheduleHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ScheduleHolder extends RecyclerView.ViewHolder{
        public ScheduleHolder(View itemView) {
            super(itemView);
        }
    }
}
