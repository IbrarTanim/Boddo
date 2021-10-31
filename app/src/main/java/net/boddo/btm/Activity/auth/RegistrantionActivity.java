package net.boddo.btm.Activity.auth;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.badoualy.stepperindicator.StepperIndicator;

import net.boddo.btm.Activity.auth.adapter.StepperAdapter;
import net.boddo.btm.Activity.auth.stepperfragments.UserNameFragment;
import net.boddo.btm.Activity.auth.viewpager.CustomRegistrationViewPager;
import net.boddo.btm.Adepter.ViewPageAdepter;
import net.boddo.btm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrantionActivity extends AppCompatActivity implements BaseCommunicator {

    @BindView(R.id.custom_back_button)
    ImageView backArrow;


    @BindView(R.id.registration_viewpager)
    CustomRegistrationViewPager viewPager;

    @BindView(R.id.stepper)
    StepperIndicator indicator;

    StepperAdapter stepperAdapter;
    ViewPageAdepter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            UserNameFragment userNameFragment = new UserNameFragment();
            stepForward(userNameFragment);
        }
        stepperAdapter = new StepperAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
    }

    private void stepForward(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit);
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @OnClick(R.id.custom_back_button)
    public void backPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }

    }
}
