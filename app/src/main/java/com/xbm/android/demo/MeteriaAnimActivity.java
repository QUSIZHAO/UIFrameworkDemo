package com.xbm.android.demo;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import com.xbm.android.demo.adapter.SamplesRecyclerAdapter;
import com.xbm.android.demo.entity.Sample;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaobaima on 17-10-12.
 */

public class MeteriaAnimActivity extends AppCompatActivity{
	private List<Sample> samples;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meteria_anim_layout);
		setupWindowAnimations();
		setupSamples();
		setupToolbar();
		setupLayout();
	}

	private void setupWindowAnimations() {
		// Re-enter transition is executed when returning to this activity
		Slide slideTransition = new Slide();
		slideTransition.setSlideEdge(Gravity.LEFT);
		slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
		getWindow().setReenterTransition(slideTransition);
		getWindow().setExitTransition(slideTransition);
	}

	private void setupSamples() {
		samples = Arrays.asList(
			new Sample(ContextCompat.getColor(this, R.color.sample_red), "Transitions"),
			new Sample(ContextCompat.getColor(this, R.color.sample_blue), "Shared Elements"),
			new Sample(ContextCompat.getColor(this, R.color.sample_green), "View animations"),
			new Sample(ContextCompat.getColor(this, R.color.sample_yellow), "Circular Reveal Animation")
		);
	}

	private void setupToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
	}

	private void setupLayout() {
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		SamplesRecyclerAdapter samplesRecyclerAdapter = new SamplesRecyclerAdapter(this, samples);
		recyclerView.setAdapter(samplesRecyclerAdapter);
	}
}
