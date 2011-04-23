package Codemeditation.DroidBoard;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import Codemeditation.AgilezenApi.IKanbanApi;
import Codemeditation.Domain.Phase;
import Codemeditation.Domain.Story;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.ysamlan.horizontalpager.HorizontalPager;
import com.google.inject.Inject;

public class PhasesListActivity extends RoboActivity {
	@Inject IKanbanApi kanbanApi;
	private String projectName;
	private int projectId;
	private List<Phase> phases;
	private Handler handler;
	private Runnable runnable;
	private List<View> storyListViews;
	private List<List<Story>> stories;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phases_list_view);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		projectId = bundle.getInt("PROJECT_ID");
		projectName = bundle.getString("PROJECT_NAME");
		
		stories = new ArrayList<List<Story>>();
        storyListViews= new ArrayList<View>();
        
		final HorizontalPager.OnScreenSwitchListener onScreenSwitchListener =
	            new HorizontalPager.OnScreenSwitchListener() {
	                @Override
	                public void onScreenSwitched(final int screen) {

	                	List<Story> stories_array = stories.get(screen);
	                	if (stories_array == null)
	                	{
	                	   new	LoadStoriesTask(screen).execute(null);
	                	}
	                }
	            };
		
		 // Create the view switcher
        final HorizontalPager realViewSwitcher = new HorizontalPager(getApplicationContext());

        realViewSwitcher.setOnScreenSwitchListener(onScreenSwitchListener);

		handler = new Handler();
		
		runnable = new Runnable() {

									@Override
									public void run() {
							
										        // set as content view
										        setContentView(realViewSwitcher);
										        
										       for (int i = 0; i < phases.size(); i++) {
										    	   
										    	   Context context = getApplicationContext();
										    	   LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
										    	   View view =  inflater.inflate(R.layout.phase_page_view, null);
										    	   TextView title =  (TextView)view.findViewById(R.id.phase_name);
										    	   
										    	   Phase phase = phases.get(i);
										    	   String text = phase.limit > 0 ? String.format("%s(%d)",phase.name, phase.limit) : String.format("%s",phase.name);
										    	   title.setText(text);
										    	   
										    	   realViewSwitcher.addView(view);
										    	   
										    	   storyListViews.add(i, view);
										           stories.add(i, null);									       
										        }
										       
										       new	LoadStoriesTask(0).execute(null);
										}	
		};
		
		new LoadPhasesTask().execute(null);	
	}
	
    private class StoryAdapter extends ArrayAdapter<Story> {
    	
		private Story[] stories;

		public StoryAdapter(Story[] stories) {
			super(PhasesListActivity.this, R.layout.story_item, R.id.name, stories);
			this.stories = stories;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View item_row = inflater.inflate(R.layout.story_item, parent, false);
			
			TextView name = (TextView)item_row.findViewById(R.id.title);
			name.setText(String.format("%d", stories[position].id));
		
			TextView text = (TextView)item_row.findViewById(R.id.text);
			text.setText(stories[position].text );
			return item_row;
		}
    }

    private class LoadPhasesTask extends AsyncTask<Void, Void, Void>{
    	private ProgressDialog dialog;

		@Override
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(PhasesListActivity.this, "", "Loading phases ...", true);
    	}
		
		@Override
		protected Void doInBackground(Void... params) {
			phases = kanbanApi.GetPhases(projectId);
    		return null;
		}
    	
		@Override
		protected void onPostExecute(Void result) {
			if (dialog != null) dialog.dismiss();
			handler.post(runnable);
			super.onPostExecute(result);
		}
    }
    
    private class LoadStoriesTask extends AsyncTask<Void, Void, Void>{
    	private ProgressDialog dialog;
    	private int screen;
    	
    	public LoadStoriesTask(int screen) {
    		this.screen = screen;
    	}

		@Override
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(PhasesListActivity.this, "", "Loading stories ...", true);
    	}
		
		@Override
		protected Void doInBackground(Void... params) {
      		List<Story> stories_array = kanbanApi.GetStories(projectId, phases.get(screen).id);
    		stories.set(screen, stories_array);
    		return null;
		}
    	
		@Override
		protected void onPostExecute(Void result) {
			if (dialog != null) dialog.dismiss();
			View view = storyListViews.get(screen);
			ListView list = (ListView)view.findViewById(R.id.story_list);
			StoryAdapter adapter = new StoryAdapter(stories.get(screen).toArray(new Story[0]));
			list.setAdapter(adapter);

			super.onPostExecute(result);
		}
    }
}
